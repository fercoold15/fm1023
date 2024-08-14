package jad.farmacy.Repository;

import jad.farmacy.Entity.Product;
import jad.farmacy.Entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
