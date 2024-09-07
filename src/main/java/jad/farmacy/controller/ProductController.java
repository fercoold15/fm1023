package jad.farmacy.controller;

import jad.farmacy.Service.ProductService;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewProduct;
import jad.farmacy.dto.UpdateProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse> getAllProducts() {
        return productService.allProducts();
    }

    @GetMapping("/{storeID}")
    public ResponseEntity<GlobalResponse> getAllProducts(@PathVariable Long storeID) {
        return productService.allProductsByStore(storeID);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse> addProduct(@RequestBody NewProduct newProduct) {
        return productService.addProduct(newProduct);
    }

    @PutMapping("")
    public ResponseEntity<GlobalResponse> updateProduct(@RequestBody UpdateProduct updateProduct) {
        return productService.updateProduct(updateProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    @GetMapping("byCode/{code}")
    public ResponseEntity<GlobalResponse> getProductByCode(@PathVariable String code) {
        return productService.getProductByBarcode(code);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse> deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
}

