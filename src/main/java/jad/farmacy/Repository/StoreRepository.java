package jad.farmacy.Repository;

import jad.farmacy.Entity.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {
}
