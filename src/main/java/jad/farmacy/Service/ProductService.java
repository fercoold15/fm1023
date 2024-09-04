package jad.farmacy.Service;

import jad.farmacy.Entity.Product;
import jad.farmacy.Entity.Store;
import jad.farmacy.Exceptions.ProductNotFoundException;
import jad.farmacy.Repository.ProductRepository;
import jad.farmacy.Repository.StoreRepository;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewProduct;
import jad.farmacy.dto.UpdateProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public ProductService(ProductRepository productRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }


    public ResponseEntity<GlobalResponse> allProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        GlobalResponse apiResponse = new GlobalResponse(200, "Registros Encontrados", "Registros Encontrados", products);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> addProduct(NewProduct newProduct) {
        Store store = new Store();
        store.setId(newProduct.getStoreID());
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(newProduct.getExpirationDate(), inputFormatter);
        Product product = new Product();
        product.setProductName(newProduct.getProductName());
        product.setComercialName(newProduct.getComercialName());
        product.setSellPrice(newProduct.getSellPrice());
        product.setBuyPrice(newProduct.getBuyPrice());
        product.setPresentation(newProduct.getPresentation());
        product.setQrCode(newProduct.getQrCode());
        product.setQuantity(newProduct.getQuantity());
        product.setLote(newProduct.getLote());
        product.setExpirationDate(date);
        product.setDescription(newProduct.getDescription());
        product.setStore(store);
        product.setBlisterPerBox(newProduct.getBlistersPerBox());
        product.setBillsPerBlister(newProduct.getBillsPerBlister());
        product.setPricePerPill(newProduct.getPricePerPill());
        product.setPricePerBlister(newProduct.getPricePerBlister());
        product.setBrand(newProduct.getBrand());
        Product savedProduct = productRepository.save(product);
        GlobalResponse apiResponse = new GlobalResponse(200, "Producto Agregado", "Producto agregado exitosamente", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> updateProduct(UpdateProduct updateProduct) {
        if (updateProduct.getProductId() == 0) {
            GlobalResponse apiResponse = new GlobalResponse(400, "Error", "ID de producto inválido", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        Store store = new Store();
        store.setId(updateProduct.getStoreID());
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(updateProduct.getExpirationDate(), inputFormatter);
        Product product = new Product();
        product.setId(updateProduct.getProductId());
        product.setProductName(updateProduct.getProductName());
        product.setComercialName(updateProduct.getComercialName());
        product.setSellPrice(updateProduct.getSellPrice());
        product.setBuyPrice(updateProduct.getBuyPrice());
        product.setPresentation(updateProduct.getPresentation());
        product.setQrCode(updateProduct.getQrCode());
        product.setQuantity(updateProduct.getQuantity());
        product.setExpirationDate(date);
        product.setDescription(updateProduct.getDescription());
        product.setLote(updateProduct.getLote());
        product.setStore(store);
        product.setBlisterPerBox(updateProduct.getBlistersPerBox());
        product.setBillsPerBlister(updateProduct.getBillsPerBlister());
        product.setPricePerBlister(updateProduct.getPricePerBlister());
        product.setPricePerPill(updateProduct.getPricePerPill());
        product.setBrand(updateProduct.getBrand());
        Product updatedProduct = productRepository.save(product);
        if(updatedProduct!=null){
            GlobalResponse apiResponse = new GlobalResponse(400, "Error", "Ocurrio un Error al actualizar el Producto", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        GlobalResponse apiResponse = new GlobalResponse(200, "Producto Actualizado", "Producto actualizado exitosamente", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        GlobalResponse apiResponse = new GlobalResponse(200, "Producto Encontrado", "Producto encontrado exitosamente", product);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> getProductByBarcode(String code) {
        Product product = productRepository.findByBarcode(code);

        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + code);
        }

        Optional<Store> storeOptional = storeRepository.findById(product.getStore().getId());

        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            product.setStore(store);
        } else {
            product.setStore(null);
        }

        GlobalResponse apiResponse = new GlobalResponse(200, "Producto Encontrado", "Producto encontrado exitosamente", product);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    public ResponseEntity<GlobalResponse> deleteProductById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            GlobalResponse apiResponse = new GlobalResponse(200, "Producto Eliminado", "Producto eliminado exitosamente", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            GlobalResponse apiResponse = new GlobalResponse(404, "Error", "Producto no encontrado con id: " + id, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
}

