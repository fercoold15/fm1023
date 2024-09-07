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

            SellingDetail sellingDetail = new SellingDetail();
            sellingDetail.setSelling(selling);
            sellingDetail.setProduct(product);
            sellingDetail.setQuantity(detail.getQuantity());
            sellingDetail.setTotal(detail.getTotal());

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

