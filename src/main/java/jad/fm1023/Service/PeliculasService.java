package jad.fm1023.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jad.fm1023.Entity.Categoria;
import jad.fm1023.Entity.Peliculas;
import jad.fm1023.EntityConverter.PeliculaEntityConverter;
import jad.fm1023.Repository.PeliculasRepository;
import jad.fm1023.RequestEntities.RequestPelicula;
import jad.fm1023.dto.PeliculaDTO;


@Service
public class PeliculasService {
    @Autowired
    PeliculasRepository peliculasRepository;

    @Autowired
    PeliculaEntityConverter peliculaEntityConverter;

    public Peliculas save(RequestPelicula newPelicula){
        Peliculas pelicula = new Peliculas();
        Categoria categoria = new Categoria();
        categoria.setCategoriaID(newPelicula.getCategoriaId());
        pelicula.setCategoria(categoria);
        pelicula.setNombrePelicula(newPelicula.getNombrePelicula());
        return this.peliculasRepository.saveAndFlush(pelicula);
    }

    public List<PeliculaDTO> get(){
        List<Peliculas> peliculas = this.peliculasRepository.findAll();
        return this.peliculaEntityConverter.toResponseAll(peliculas);
    }
}
