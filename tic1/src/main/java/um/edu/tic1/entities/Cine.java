package um.edu.tic1.entities;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="cines")
public class Cine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    //todo manytoone
    private ArrayList<Sala> salas = new ArrayList<>();

    public void agregarSala(Sala sala){
        salas.add(sala);
    }

    public Cine(String nombre) {
        this.name = nombre;
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

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public void setSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }
}
