package um.edu.tic1.commons.DTO;

public class CineDTO {

    private Long id;
    private  String name;

    public CineDTO(String name){
        this.name =name;
    }

    public CineDTO() {
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
}
