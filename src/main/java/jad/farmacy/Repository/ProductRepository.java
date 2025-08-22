package jad.farmacy.Repository;

import jad.farmacy.Entity.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query(value = "SELECT * FROM PRODUCTS WHERE QR_CODE = :CODE",nativeQuery = true)
    List<Product> findByBarcode(@Param("CODE") String code);

    @Query(value = "SELECT * FROM PRODUCTS WHERE STORE_ID = :id",nativeQuery = true)
    List<Product> findByBarcode(@Param("id") long id);
    @Query(value = "SELECT * FROM PRODUCTS LEFT JOIN STORES S on S.STORE_ID = PRODUCTS.STORE_ID LEFT JOIN SUPPLIERS S2 on S2.SUPPLIER_ID = PRODUCTS.SUPPLIER_ID WHERE S.STORE_ID=:STOREID",nativeQuery = true)
    List<Map<String,Object>> findAllByStore(@Param("STOREID") long storeID);

    @Query(value = "SELECT * FROM PRODUCTS LEFT JOIN STORES S on S.STORE_ID = PRODUCTS.STORE_ID LEFT JOIN SUPPLIERS S2 on S2.SUPPLIER_ID = PRODUCTS.SUPPLIER_ID",nativeQuery = true)
    List<Map<String,Object>> findAllProducts();

    @Query(value = "SELECT * FROM PRODUCTS LEFT JOIN STORES S on S.STORE_ID = PRODUCTS.STORE_ID LEFT JOIN SUPPLIERS S2 on S2.SUPPLIER_ID = PRODUCTS.SUPPLIER_ID WHERE QUANTITY <=200",nativeQuery = true)
    List<Map<String,Object>> findLowStock();

    @Query("SELECT p FROM Product p WHERE p.expirationDate BETWEEN :today AND :twoMonthsLater")
    List<Product> findProductsExpiringInNextTwoMonths(@Param("today") LocalDate today, @Param("twoMonthsLater") LocalDate twoMonthsLater);

}
