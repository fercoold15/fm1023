package jad.farmacy.Repository;

import jad.farmacy.Entity.Selling;
import jad.farmacy.Repository.Proyections.ITotalSellings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SellingRepository extends CrudRepository<Selling, Long> {
    List<Selling> findAllByUser_Store_Id(long storeID);

    @Query(value ="SELECT SUM(TOTAL) AS totalSellings\n" +
            "FROM SELLING_DETAILS\n" +
            "JOIN SELLINGS S ON S.SELLING_ID = SELLING_DETAILS.SELLING_ID\n" +
            "JOIN PRODUCTS P ON P.PRODUCT_ID = SELLING_DETAILS.PRODUCT_ID\n" +
            "JOIN STORES S2 ON S2.STORE_ID = P.STORE_ID\n" +
            "WHERE S.SELLING_DATE BETWEEN :startOfDay AND :endOfDay\n" +
            "AND S2.STORE_ID = :store\n",nativeQuery = true)
    ITotalSellings totalSellings(@Param("startOfDay") String startOfDay,@Param("endOfDay") String endOfDay, @Param("store") long storeID);


    @Query(value ="SELECT *\n" +
            "FROM SELLINGS\n" +
            "WHERE SELLING_DATE BETWEEN :startOfDay AND :endOfDay\n"
           ,nativeQuery = true)
    List<Selling> getSellings(@Param("startOfDay") String startOfDay,@Param("endOfDay") String endOfDay);


    @Query(value ="SELECT SUM(TOTAL) AS totalSellings\n" +
            "FROM SELLING_DETAILS\n" +
            "JOIN SELLINGS S ON S.SELLING_ID = SELLING_DETAILS.SELLING_ID\n" +
            "JOIN PRODUCTS P ON P.PRODUCT_ID = SELLING_DETAILS.PRODUCT_ID\n" +
            "JOIN STORES S2 ON S2.STORE_ID = P.STORE_ID\n" +
            "WHERE S.SELLING_DATE BETWEEN :startOfDay AND :endOfDay\n" +
            "AND S2.STORE_ID = :store\n",nativeQuery = true)
    List<Selling> getSellingsByStore(@Param("startOfDay") String startOfDay,@Param("endOfDay") String endOfDay, @Param("store") long storeID);
}
