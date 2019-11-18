package um.edu.tic1.client.models;

import um.edu.tic1.commons.DTO.MovieDTO; //TODO auqnue lo muestra no esta compliandolo :(


public class Movie {

    private long id;
    private String name;
    private String description;
    private String genero;
    private String categoria;

    public Movie() {
        this.name = null;
    }

    public Movie(MovieDTO movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.description = movie.getDescription();
        this.genero = movie.getGenero();
        this.categoria = movie.getCategoria();
    }

    public MovieDTO toDTO() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setGenero(this.genero);
        movieDTO.setDescription(this.description);
        movieDTO.setCategoria(this.categoria);
        movieDTO.setName(this.name);
        movieDTO.setId(this.id);
        return movieDTO;
    }


    public String getGenero() {
        return this.genero;
    }


    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria= categoria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
