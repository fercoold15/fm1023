package jad.farmacy.Service;

import jad.farmacy.Entity.*;
import jad.farmacy.Exceptions.ProductNotFoundException;
import jad.farmacy.Exceptions.SellingNotFoundException;
import jad.farmacy.Repository.ProductRepository;
import jad.farmacy.Repository.Proyections.ITotalSellings;
import jad.farmacy.Repository.SellingDetailRepository;
import jad.farmacy.Repository.SellingRepository;
import jad.farmacy.Repository.UserRepository;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewSelling;
import jad.farmacy.dto.TotalDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellingService {
    private final SellingRepository sellingRepository;
    private final SellingDetailRepository sellingDetailRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    ZoneId zoneId = ZoneId.of("UTC-6");
    public SellingService(SellingRepository sellingRepository, SellingDetailRepository sellingDetailRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.sellingRepository = sellingRepository;
        this.sellingDetailRepository = sellingDetailRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<GlobalResponse> getSellings(String requestDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(requestDate, inputFormatter);
        LocalDateTime startTime = date.atStartOfDay();
        LocalDateTime endTime = date.atTime(23,59,00);
        List<Selling> sellings =sellingRepository.getSellings(startTime.toString(),endTime.toString());
        sellings.forEach(selling -> {
            List<SellingDetail> sellingDetailList = sellingDetailRepository.findAllBySelling_Id(selling.getId());
            selling.setSellingDetailList(sellingDetailList);
        });
        GlobalResponse response = new GlobalResponse(200, "Sales Found", "Sales found successfully", sellings);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<GlobalResponse> addSelling(NewSelling newSelling) {
        Selling selling = new Selling();
        LocalDateTime date = LocalDateTime.from(LocalDateTime.now(zoneId));
        selling.setSellingDate(date);
        selling.setUser(userRepository.findById(newSelling.getUserId()).orElseThrow());
        selling.setSellingTotal(newSelling.getSellingTotal());
        selling.setXML(newSelling.getXML());
        selling = sellingRepository.save(selling);

        for (NewSelling.NewSellingDetail detail : newSelling.getDetails()) {
            Product product = productRepository.findById(detail.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + detail.getProductId()));

            String sellingUnit = detail.getSellingUnit();
            int quantityToReduce = detail.getQuantity();

            if (product.isPillType()) {
                if (("pill".equalsIgnoreCase(sellingUnit) && !product.isSellByPill()) ||
                        ("blister".equalsIgnoreCase(sellingUnit) && !product.isSellByBlister()) ||
                        ("box".equalsIgnoreCase(sellingUnit) && !product.isSellByBox())) {
                    throw new IllegalArgumentException("Product cannot be sold by the specified unit: " + sellingUnit);
                }
            } else {
                if (!"box".equalsIgnoreCase(sellingUnit) && !product.isSellByBox()) {
                    throw new IllegalArgumentException("Non-pill product can only be sold by box.");
                }
            }

            double total = 0.0;

            switch (sellingUnit.toLowerCase()) {
                case "pill":
                    if (product.isPillType()) {
                        total = quantityToReduce * product.getPricePerPill();
                        quantityToReduce = quantityToReduce; // No conversion needed for pills
                    } else {
                        throw new IllegalArgumentException("Invalid selling unit for a non-pill product.");
                    }
                    break;
                case "blister":
                    if (product.isPillType()) {
                        total = quantityToReduce * product.getPricePerBlister();
                        if(product.getBillsPerBlister() == 0) {
                            quantityToReduce = quantityToReduce;
                        }else{
                            quantityToReduce = quantityToReduce * product.getBillsPerBlister();
                        }

                        System.out.println(quantityToReduce);// Convert blisters to pills
                    } else {
                        throw new IllegalArgumentException("Invalid selling unit for a non-pill product.");
                    }
                    break;
                case "box":
                    total = quantityToReduce * product.getSellPrice();
                    if (product.isPillType()) {
                        if(product.getBillsPerBlister() == 0 && product.getBlisterPerBox()==0) {
                            quantityToReduce = quantityToReduce;
                        }else if(product.getBillsPerBlister() == 0){
                            quantityToReduce = quantityToReduce * product.getBlisterPerBox();
                        }
                        else{
                            quantityToReduce = quantityToReduce * product.getBlisterPerBox() * product.getBillsPerBlister(); // Convert boxes to pills
                        }

                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid selling unit.");
            }

            // Reduce the product quantity in stock
            if (product.getQuantity() < quantityToReduce) {
                throw new IllegalArgumentException("Insufficient stock for the product: " + product.getProductName());
            }
            product.setQuantity(product.getQuantity() - quantityToReduce);
            productRepository.save(product); // Save the updated product quantity

            // Save the selling details
            SellingDetail sellingDetail = new SellingDetail();
            sellingDetail.setSelling(selling);
            sellingDetail.setProduct(product);
            sellingDetail.setQuantity(detail.getQuantity());
            sellingDetail.setTotal(total);
            sellingDetail.setSellingUnit(sellingUnit);
            sellingDetailRepository.save(sellingDetail);
        }

        GlobalResponse response = new GlobalResponse(200, "Sale Added", "Sale added successfully", selling);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    public ResponseEntity<GlobalResponse> getSellingById(Long id) {
        Selling selling = sellingRepository.findById(id)
                .orElseThrow(() -> new SellingNotFoundException("Sale not found with id: " + id));
        GlobalResponse response = new GlobalResponse(200, "Sale Found", "Sale found successfully", selling);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> getSellingPerDay(TotalDTO totalDTO) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(totalDTO.getDate(), inputFormatter);
        LocalDateTime startTime = date.atStartOfDay();
        LocalDateTime endTime = date.atTime(23,59,00);

        ITotalSellings totalSellings = sellingRepository.totalSellings(startTime.toString(),endTime.toString(),totalDTO.getStoreID());
        GlobalResponse response = new GlobalResponse(200, "Sale total Found", "Sale found successfully", totalSellings);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public ResponseEntity<GlobalResponse> cancelSelling(Long sellingId) {
        Selling selling = sellingRepository.findById(sellingId)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found with id: " + sellingId));

        // Retrieve all the selling details associated with the sale
        List<SellingDetail> sellingDetails = sellingDetailRepository.findAllBySelling_Id(sellingId);

        // Loop through each selling detail and restore the product stock
        for (SellingDetail detail : sellingDetails) {
            Product product = detail.getProduct();
            int quantityToRestore = detail.getQuantity();

            // Restore the product stock based on the selling unit used during the sale
            String sellingUnit = detail.getSellingUnit(); // Assuming you store the selling unit in the detail

            switch (sellingUnit.toLowerCase()) {
                case "pill":
                    // No conversion needed, restore pill quantity directly
                    product.setQuantity(product.getQuantity() + quantityToRestore);
                    break;
                case "blister":
                    // Convert blisters back to pills if it's a pill-type product
                    if (product.isPillType()) {
                        if(product.getBillsPerBlister() > 0) {
                            quantityToRestore = quantityToRestore * product.getBillsPerBlister();
                        }
                    }
                    product.setQuantity(product.getQuantity() + quantityToRestore);
                    break;
                case "box":
                    // Convert boxes back to pills or blisters if it's a pill-type product
                    if (product.isPillType()) {
                        if (product.getBillsPerBlister() > 0 && product.getBlisterPerBox() > 0) {
                            quantityToRestore = quantityToRestore * product.getBlisterPerBox() * product.getBillsPerBlister();
                        } else if (product.getBlisterPerBox() > 0) {
                            quantityToRestore = quantityToRestore * product.getBlisterPerBox();
                        }
                    }
                    product.setQuantity(product.getQuantity() + quantityToRestore);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid selling unit.");
            }

            // Save the updated product stock
            productRepository.save(product);

            // Delete the selling detail
            sellingDetailRepository.delete(detail);
        }

        // Once all details are deleted, delete the main selling record
        sellingRepository.deleteById(sellingId);

        GlobalResponse response = new GlobalResponse(200, "Sale Cancelled", "Sale cancelled and stock restored", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

