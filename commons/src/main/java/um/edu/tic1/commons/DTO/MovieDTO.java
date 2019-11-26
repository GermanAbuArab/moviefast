package um.edu.tic1.commons.DTO;

public class MovieDTO {
    private Long id;
    private String name;
    private String description;
    private String genero;
    private String categoria;
    private byte[] imagen;

    public MovieDTO() {
    }

    public MovieDTO(Long id, String name, String description, String genero, String categoria) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genero = genero;
        this.categoria = categoria;

    }





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
