package jad.fm1023.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jad.fm1023.Entity.Autor;
import jad.fm1023.RequestEntities.RequestAutor;
import jad.fm1023.Service.AutorService;
import jad.fm1023.dto.AutorDTO;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    AutorService autorService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return this.autorService.getAll();
    }


     @GetMapping("/{id}")
    public ResponseEntity<?> getOneVariable(@PathVariable Integer id) {
        return this.autorService.get(id);
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody RequestAutor autor) {
        return this.autorService.save(autor);
    }

     @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody RequestAutor autor) {
        return this.autorService.update(id, autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        return this.autorService.delete(id);
    }

    
    
}
