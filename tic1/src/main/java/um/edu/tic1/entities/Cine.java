package um.edu.tic1.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cines")
public class Cine{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cine_id")
    private Long id;

    private String name;



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

    public boolean equals(Cine cine){

        boolean resultado = false;

        if(cine.getId().equals(this.id)){
            resultado = true;
        }

        return resultado;
    }




}
