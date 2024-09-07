package jad.farmacy.Service;

import jad.farmacy.Entity.Product;
import jad.farmacy.Entity.Selling;
import jad.farmacy.Entity.SellingDetail;
import jad.farmacy.Exceptions.ProductNotFoundException;
import jad.farmacy.Exceptions.SellingNotFoundException;
import jad.farmacy.Repository.ProductRepository;
import jad.farmacy.Repository.SellingDetailRepository;
import jad.farmacy.Repository.SellingRepository;
import jad.farmacy.Repository.UserRepository;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewSelling;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SellingService {
    private final SellingRepository sellingRepository;
    private final SellingDetailRepository sellingDetailRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public SellingService(SellingRepository sellingRepository, SellingDetailRepository sellingDetailRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.sellingRepository = sellingRepository;
        this.sellingDetailRepository = sellingDetailRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    public ResponseEntity<GlobalResponse> addSelling(NewSelling newSelling) {
        Selling selling = new Selling();
        selling.setSellingDate(newSelling.getSellingDate());
        selling.setUser(userRepository.findById(newSelling.getUserId()).orElseThrow());
        selling.setSellingTotal(newSelling.getSellingTotal());
        selling = sellingRepository.save(selling);

        for (NewSelling.NewSellingDetail detail : newSelling.getDetails()) {
            Product product = productRepository.findById(detail.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + detail.getProductId()));

            // Validate the selling unit only if the product is of pill type
            String sellingUnit = detail.getSellingUnit();
            int quantityToReduce = detail.getQuantity(); // The quantity to reduce based on the selling unit

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
                        quantityToReduce = quantityToReduce * product.getBillsPerBlister(); // Convert blisters to pills
                    } else {
                        throw new IllegalArgumentException("Invalid selling unit for a non-pill product.");
                    }
                    break;
                case "box":
                    total = quantityToReduce * product.getSellPrice();
                    if (product.isPillType()) {
                        quantityToReduce = quantityToReduce * product.getBlisterPerBox() * product.getBillsPerBlister(); // Convert boxes to pills
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
    public void deleteSellingById(Long id) {
        if (sellingRepository.existsById(id)) {
            sellingRepository.deleteById(id);
        } else {
            throw new SellingNotFoundException("Sale not found with id: " + id);
        }
    }

}

