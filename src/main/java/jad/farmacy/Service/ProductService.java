package jad.farmacy.Service;

import jad.farmacy.Entity.Product;
import jad.farmacy.Entity.Store;
import jad.farmacy.Entity.Supplier;
import jad.farmacy.Exceptions.ProductNotFoundException;
import jad.farmacy.Repository.ProductRepository;
import jad.farmacy.Repository.StoreRepository;
import jad.farmacy.Repository.SupplierRepository;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewProduct;
import jad.farmacy.dto.UpdateProduct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    private final SupplierRepository supplierRepository;
    ZoneId zoneId = ZoneId.of("UTC-6");
    public ProductService(ProductRepository productRepository, StoreRepository storeRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.supplierRepository = supplierRepository;
    }


    public ResponseEntity<GlobalResponse> allProducts(HttpServletRequest request) {
        List<Map<String,Object>> products = new ArrayList<>();
        int rol = (Integer) request.getAttribute("rol");
        long store=(Integer) request.getAttribute("store");
        if(rol==1){
            products.addAll(productRepository.findAllByStore(store));
        }else{
            productRepository.findAllProducts().forEach(products::add);
        }
        GlobalResponse apiResponse = new GlobalResponse(200, "Registros Encontrados", "Registros Encontrados", products);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> getProductsExpiringInNextTwoMonths() {
        LocalDate today = LocalDate.from(LocalDateTime.now(zoneId));
        LocalDate twoMonthsLater = today.plusMonths(2);

        List<Product> expiringProducts = productRepository.findProductsExpiringInNextTwoMonths(today, twoMonthsLater);
        expiringProducts.forEach(product -> {
            Optional<Store> storeOptional = storeRepository.findById(product.getStore().getId());
            product.setStore(storeOptional.orElse(null));
            Optional<Supplier> supplier = supplierRepository.findById(product.getSupplier().getId());
            product.setSupplier(supplier.orElse(null));
        });
        if (expiringProducts.isEmpty()) {
            GlobalResponse apiResponse = new GlobalResponse(404, "No se encontraron productos", "No hay productos que venzan en los próximos 2 meses", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        GlobalResponse apiResponse = new GlobalResponse(200, "Productos encontrados", "Productos que vencen en los próximos 2 meses encontrados exitosamente", expiringProducts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> getProductsLowStock() {
        List<Map<String,Object>> lowStockProducts = productRepository.findLowStock();
        if (lowStockProducts.isEmpty()) {
            GlobalResponse apiResponse = new GlobalResponse(404, "No se encontraron productos", "No hay productos con bajo stock", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        GlobalResponse apiResponse = new GlobalResponse(200, "Productos encontrados", "Productos con bajo stock encontrados exitosamente", lowStockProducts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> allProductsByStore(long storeID) {
        List<Map<String,Object>> products = new ArrayList<>();
        products=productRepository.findAllByStore(storeID);
        GlobalResponse apiResponse = new GlobalResponse(200, "Registros Encontrados", "Registros Encontrados", products);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> addProduct(NewProduct newProduct) {
        Store store = new Store();
        store.setId(newProduct.getStoreID());
        Supplier supplier = new Supplier();
        supplier.setId(newProduct.getSupplierID());
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
        product.setSellByBox(newProduct.isSellByBox());
        product.setSellByBlister(newProduct.isSellByBlister());
        product.setSellByPill(newProduct.isSellByPill());
        product.setPillType(newProduct.isPillType());
        product.setType(newProduct.getType());
        product.setSupplier(supplier);
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
        Supplier supplier = new Supplier();
        supplier.setId(updateProduct.getSupplierID());
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
        product.setSellByBox(updateProduct.isSellByBox());
        product.setSellByBlister(updateProduct.isSellByBlister());
        product.setSellByPill(updateProduct.isSellByPill());
        product.setPillType(updateProduct.isPillType());
        product.setType(updateProduct.getType());
        product.setSupplier(supplier);
        Product updatedProduct = productRepository.save(product);

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
        List<Product> products = productRepository.findByBarcode(code);

        products.forEach(product -> {
            Optional<Store> storeOptional = storeRepository.findById(product.getStore().getId());
            product.setStore(storeOptional.orElse(null));
            Optional<Supplier> supplier = supplierRepository.findById(product.getSupplier().getId());
            product.setSupplier(supplier.orElse(null));
        });
        if (products == null || products.isEmpty()) {
            throw new ProductNotFoundException("Product not found with barcode: " + code);
        }

        products.forEach(product -> {
            Optional<Store> storeOptional = storeRepository.findById(product.getStore().getId());
            product.setStore(storeOptional.orElse(null));
        });

        GlobalResponse apiResponse = new GlobalResponse(200, "Producto Encontrado", "Producto encontrado exitosamente", products);
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

