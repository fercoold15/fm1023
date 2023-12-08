package jad.fm1023.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jad.fm1023.Entity.Peliculas;

public interface PeliculasRepository extends JpaRepository<Peliculas,Integer> {
    
}
