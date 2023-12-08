package jad.fm1023.Entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="CATEGORIAS")
public class Categoria {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CATEGORIA_ID")
    private int categoriaID;

    @Column(name="NOMBRE_CATEGORIA")
    private String nombreCategoria;

    @OneToMany(mappedBy ="categoria")
    private List<Peliculas> peliculas;
    
    
}
