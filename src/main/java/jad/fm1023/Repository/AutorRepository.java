package jad.fm1023.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jad.fm1023.Entity.Autor;
import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Integer> {

    @Query(value="SELECT * FROM AUTOR",nativeQuery=true)
    List<Autor> getAllAutores();

      @Query(value="SELECT * FROM AUTOR WHERE idAutor=?1",nativeQuery=true)
    Autor getOne(int idAutor);
    
}
