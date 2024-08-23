package jad.farmacy.Repository;

import jad.farmacy.Entity.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query(value = "SELECT * FROM PRODUCTS WHERE QR_CODE = :CODE",nativeQuery = true)
    Product findByBarcode(@Param("CODE") String code);

}
