package jad.fm1023.EntityConverter;

import java.util.ArrayList;
import java.util.List;

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

        public List<AutorDTO> toResponseAll(List<Autor> autor){
        List<AutorDTO> autorDTOs = new ArrayList<>();
        for (Autor read : autor) {
            AutorDTO autorDTO = new AutorDTO();
            autorDTO.setNombreAutor(read.getNombreAutor());
            autorDTOs.add(autorDTO);
        }
        return autorDTOs;
    }
}
