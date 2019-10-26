package um.edu.tic1.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sala_id")
    private Long id;

    private String name;

    private int capacidad;

    @ManyToOne
            //(cascade = CascadeType.ALL)
    @JoinColumn(name = "cine_id")
    private Cine cine;

    public Sala(){

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
