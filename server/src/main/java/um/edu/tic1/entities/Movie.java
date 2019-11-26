package um.edu.tic1.entities;




import um.edu.tic1.commons.DTO.MovieDTO;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name="movies")
public class Movie {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @CollectionTable(name = "movie_id")
    private Long id;
    private String name;
    private String description;
    private String genero;
    private String categoria;

    @Lob
    @Column(nullable = false,length = 45)

    private byte[] movieImage;

    public Movie(String name,String description,String genero,String categoria){
        this.description=description;
        this.name=name;
        this.categoria=categoria;
        this.genero=genero;
    }

    public Movie() {
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMovieImage(byte[] movieImage) {
        this.movieImage = movieImage;
    }

    public String getName() {
        return name;
    }

    public byte[] getMovieImage() {
        return movieImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public MovieDTO toDTO() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setGenero(this.genero);
        movieDTO.setDescription(this.description);
        movieDTO.setCategoria(this.categoria);
        movieDTO.setName(this.name);
        movieDTO.setId(this.id);
        movieDTO.setImagen(this.movieImage);
        return movieDTO;
    }

    public Movie(MovieDTO movieDTO){
        this.description=movieDTO.getDescription();
        this.name=movieDTO.getDescription();
        this.categoria=movieDTO.getCategoria();
        this.genero=movieDTO.getGenero();
        this.id=movieDTO.getId();
        this.movieImage=movieDTO.getImagen();
    }


}
