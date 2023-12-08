package jad.fm1023.controller;

import org.springframework.web.bind.annotation.RestController;

import jad.fm1023.Entity.Peliculas;
import jad.fm1023.RequestEntities.RequestPelicula;
import jad.fm1023.Service.PeliculasService;
import jad.fm1023.dto.PeliculaDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/peliculas")

public class PeliculasController {
    @Autowired
    PeliculasService peliculasService;

    @GetMapping()
    public List<PeliculaDTO> getAll() {
        return this.peliculasService.get();
    }

    @PostMapping()
    public Peliculas save(@RequestBody RequestPelicula entity) {
        return this.peliculasService.save(entity);
    }
    
    
    
}
