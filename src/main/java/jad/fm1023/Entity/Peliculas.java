package jad.fm1023.Entity;

import java.io.Serializable;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="PELICULAS")
public class Peliculas implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PELICULA_ID")
    private int peliculaID;

    @Column(name="NOMBRE_PELICULA")
    private String nombrePelicula;

    @ManyToOne
    @JoinColumn(name ="CATEGORIA_ID",nullable = false)
    private Categoria categoria;
    
}
