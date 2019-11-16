package um.edu.tic1.client.models;

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
}
