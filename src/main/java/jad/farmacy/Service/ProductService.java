package jad.farmacy.Service;

import jad.farmacy.Entity.Product;
import jad.farmacy.Entity.Store;
import jad.farmacy.Exceptions.ProductNotFoundException;
import jad.farmacy.Exceptions.StoreNotFoundException;
import jad.farmacy.Repository.ProductRepository;
import jad.farmacy.Repository.StoreRepository;
import jad.farmacy.dto.NewProduct;
import jad.farmacy.dto.NewStore;
import jad.farmacy.dto.UpdateProduct;
import jad.farmacy.dto.UpdateStore;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }


    public List<Product> allProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public Product addProduct(NewProduct newProduct) {
        Store store = new Store();
        store.setId(newProduct.getStoreID());

        Product product = new Product();
        product.setProductName(newProduct.getProductName());
        product.setComercialName(newProduct.getComercialName());
        product.setSellPrice(newProduct.getSellPrice());
        product.setBuyPrice(newProduct.getBuyPrice());
        product.setPresentation(newProduct.getPresentation());
        product.setQrCode(newProduct.getQrCode());
        product.setQuantity(newProduct.getQuantity());
        product.setDescription(newProduct.getDescription());
        product.setStore(store);


        return productRepository.save(product);
    }

    public Product updateProduct(UpdateProduct updateProduct) {
        if(updateProduct.getProductId() == 0){
            return null;
        }
        Store store = new Store();
        store.setId(updateProduct.getStoreID());

        Product product = new Product();

        product.setId(updateProduct.getProductId());
        product.setProductName(updateProduct.getProductName());
        product.setComercialName(updateProduct.getComercialName());
        product.setSellPrice(updateProduct.getSellPrice());
        product.setBuyPrice(updateProduct.getBuyPrice());
        product.setPresentation(updateProduct.getPresentation());
        product.setQrCode(updateProduct.getQrCode());
        product.setQuantity(updateProduct.getQuantity());
        product.setDescription(updateProduct.getDescription());
        product.setStore(store);

        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

    }
}
