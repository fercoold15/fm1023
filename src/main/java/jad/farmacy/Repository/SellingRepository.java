package jad.farmacy.Repository;

import jad.farmacy.Entity.Selling;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellingRepository extends CrudRepository<Selling, Long> {
    List<Selling> findAllByUser_Store_Id(long storeID);
}
