package jad.farmacy.Repository;

import jad.farmacy.Entity.Outgoing;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OutgoingRepository extends CrudRepository<Outgoing, Long> {
    List<Outgoing> findAllByStoreId(long id);
}
