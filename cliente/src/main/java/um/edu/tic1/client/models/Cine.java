package um.edu.tic1.client.models;

import um.edu.tic1.commons.DTO.CineDTO;

public class Cine {
    private Long id;
    private  String name;
    public Cine(String name){
        this.name =name;
    }

    public Cine() {
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

    public Cine(CineDTO cine){
        this.id = cine.getId();
        this.name = cine.getName();
    }

    public CineDTO toDTO() {
        CineDTO cineDTO = new CineDTO();
        cineDTO.setName(this.name);
        cineDTO.setId(this.id);
        return cineDTO;
    }


}
