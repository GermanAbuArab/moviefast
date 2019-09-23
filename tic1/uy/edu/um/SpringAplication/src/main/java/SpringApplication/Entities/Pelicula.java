package SpringApplication.Entities;

import javafx.scene.control.Hyperlink;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "pelicula")
@Data
@Builder
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "duracion", nullable = false, length = 20)
    private String duracion;

    @Column(name = "director", nullable = false, length = 50)
    private String director;

    @Column(name = "descripcion", nullable = true)
    private String descripcion;

    @Column(name = "trailerURL", nullable = true, length = 100)
    private String trailerURL;


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTrailer(String trailer) {
        this.trailerURL = trailer;
    }

    public String getNombre() {
        return this.nombre;
    }


    public Pelicula(int id, String nombre, String duracion, String director, String descripcion, String trailerURL) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.director = director;
        this.descripcion = descripcion;
        this.trailerURL = trailerURL;
    }

    public Pelicula() {
    }
}

