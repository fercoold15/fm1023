package jad.fm1023.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jad.fm1023.Entity.Autor;
import jad.fm1023.EntityConverter.AutorEntityConverter;
import jad.fm1023.Repository.AutorRepository;
import jad.fm1023.RequestEntities.RequestAutor;
import jad.fm1023.dto.AutorDTO;

@Service
public class AutorService{

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    AutorEntityConverter autorEntityConverter;

    public ResponseEntity<?> getAll(){
        List<Autor> autores = this.autorRepository.findAll();
        return new ResponseEntity<>(autorEntityConverter.toResponseAll(autores),HttpStatus.OK);
    }

    public ResponseEntity<?> get(int idAutor){
        Autor autor = this.autorRepository.getOne(idAutor);
         return new ResponseEntity<>(this.autorEntityConverter.toResponse(autor),HttpStatus.OK);
    }

    public ResponseEntity<?> save(RequestAutor newAutor){
       Autor autor = new Autor();
       autor.setNombreAutor(newAutor.getNombreAutor());
       this.autorRepository.saveAndFlush(autor);
      return new ResponseEntity<>(this.autorEntityConverter.toResponse(autor),HttpStatus.OK);
    }

    public ResponseEntity<?> update(int id,RequestAutor autor){
            Autor oldAutor = null;
        try{
            oldAutor=this.autorRepository.getReferenceById(id);
            oldAutor.setNombreAutor(autor.getNombreAutor());
            this.autorRepository.saveAndFlush(oldAutor);
            return new ResponseEntity<>(this.autorEntityConverter.toResponse(oldAutor),HttpStatus.ACCEPTED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
       
    public ResponseEntity<?> delete(int id){
        Autor autor = null;
        try {
            autor = this.autorRepository.getReferenceById(id);
            System.out.println(autor);
            if(autor!=null){
                this.autorRepository.deleteById(id);    
            }
            return new ResponseEntity<>("Registro eliminado",HttpStatus.ACCEPTED);
        } catch (Exception e) {
             return new ResponseEntity<>("No se encontro el recurso",HttpStatus.BAD_REQUEST);
        }
        
    }

}
