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
    private ArrayList<Sala> salas = new ArrayList<>();

    private void agregarSala(Sala sala){
        salas.add(sala);
    }

    public Cine(String nombre) {
        this.name = nombre;
    }
}
