package jad.farmacy.Repository;

import jad.farmacy.Entity.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query(value = "SELECT * FROM PRODUCTS WHERE QR_CODE = :CODE",nativeQuery = true)
    List<Product> findByBarcode(@Param("CODE") String code);

    @Query(value = "SELECT * FROM PRODUCTS WHERE STORE_ID = :STOREID",nativeQuery = true)
    List<Product> findAllByStore(@Param("STOREID") long storeID);

    @Query("SELECT p FROM Product p WHERE p.expirationDate BETWEEN :today AND :twoMonthsLater")
    List<Product> findProductsExpiringInNextTwoMonths(@Param("today") LocalDate today, @Param("twoMonthsLater") LocalDate twoMonthsLater);

}
