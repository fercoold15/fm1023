package jad.farmacy.Repository;

import jad.farmacy.Entity.Outgoing;
import jad.farmacy.Repository.Proyections.ITotalOutgoings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OutgoingRepository extends CrudRepository<Outgoing, Long> {
    List<Outgoing> findAllByStoreId(long id);
    @Query(value = "SELECT SUM(TOTAL) AS totalOutgoing\n" +
            "FROM OUTGOINGS WHERE STORE_ID=:store AND BILL_DATE BETWEEN :startOfDay AND :endOfDay",nativeQuery = true)
    ITotalOutgoings totalOutgoing(@Param("startOfDay") String startOfDay,@Param("endOfDay") String endOfDay,@Param("store") long store);
}
