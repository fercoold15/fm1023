package jad.farmacy.Repository;

import jad.farmacy.Entity.Selling;
import jad.farmacy.Repository.Proyections.ITotalSellings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SellingRepository extends CrudRepository<Selling, Long> {
    List<Selling> findAllByUser_Store_Id(long storeID);

    @Query(value ="SELECT SUM(TOTAL) AS totalSellings\n" +
            "FROM SELLING_DETAILS\n" +
            "join SELLINGS S on S.SELLING_ID = SELLING_DETAILS.SELLING_ID\n" +
            "join PRODUCTS P on P.PRODUCT_ID = SELLING_DETAILS.PRODUCT_ID\n" +
            "join STORES S2 on S2.STORE_ID = P.STORE_ID\n" +
            "WHERE SELLING_DATE = :date\n" +
            "and S2.STORE_ID=:store",nativeQuery = true)
    ITotalSellings totalSellings(@Param("date") String date,@Param("store") long storeID);
}
