package jad.fm1023.EntityConverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jad.fm1023.Entity.Peliculas;
import jad.fm1023.dto.PeliculaDTO;
@Component
public class PeliculaEntityConverter {

    public List<PeliculaDTO> toResponseAll(List<Peliculas> peliculas){
        List<PeliculaDTO> peliculaDTOs = new ArrayList<>();
        for (Peliculas read : peliculas) {
            PeliculaDTO peliculaDTO = new PeliculaDTO();
            peliculaDTO.setNombrePelicula(read.getNombrePelicula());
            peliculaDTO.setCategoria(read.getCategoria().getNombreCategoria());
            peliculaDTOs.add(peliculaDTO);
        }
        return peliculaDTOs;
    }
    
}
