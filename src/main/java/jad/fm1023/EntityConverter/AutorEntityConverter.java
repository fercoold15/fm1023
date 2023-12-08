package jad.fm1023.EntityConverter;

import org.springframework.stereotype.Component;

import jad.fm1023.Entity.Autor;
import jad.fm1023.dto.AutorDTO;

@Component
public class AutorEntityConverter {
    public AutorDTO toResponse(Autor autor){
        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setNombreAutor(autor.getNombreAutor());
        return autorDTO;
    }
}
