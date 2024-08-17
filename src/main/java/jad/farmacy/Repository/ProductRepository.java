package jad.farmacy.Repository;

import jad.farmacy.Entity.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
