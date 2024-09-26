package jad.farmacy.Repository;

import jad.farmacy.Entity.Outgoing;
import jad.farmacy.Repository.Proyections.ITotalOutgoings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OutgoingRepository extends CrudRepository<Outgoing, Long> {
    List<Outgoing> findAllByStoreId(long id);
    @Query(value = "SELECT SUM(VALUE) AS totalOutgoing\n" +
            "FROM OUTGOINGS WHERE STORE_ID=:store AND BILL_DATE=:date",nativeQuery = true)
    ITotalOutgoings totalOutgoing(@Param("store") long store,@Param("date") String date);
}
