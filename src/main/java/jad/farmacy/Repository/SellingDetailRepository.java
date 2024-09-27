package jad.farmacy.Repository;

import jad.farmacy.Entity.SellingDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellingDetailRepository extends CrudRepository<SellingDetail,Long> {
    List<SellingDetail> findAllBySelling_Id(long id);
}
