package um.edu.tic1.entities;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @CollectionTable(name = "ticket_id")
    private Long id;

    //AGREGAR ASIENTOS
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id ")
    private Usuario cliente;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "funcion_id ")
    private Funcion funcion;
    private ArrayList<Integer> asientos = new ArrayList<>(150);
    //private Date fecha; es necesario? se puede sacar de funcion

    public Funcion getFuncion() {
        return funcion;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addAsiento(int x,int y){
        asientos.add(x);
        asientos.add(y);

    }
    public String imprimirAsientos(){
        String str = "";
        for (int i =0;i<asientos.size();i = i+2){
             str = str + " columna "+ asientos.get(i).toString();
            str = str +" fila "+ asientos.get(i + 1).toString();
        }
        return str;
    }

    public ArrayList<Integer> getAsientos() {
        return asientos;
    }


    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }
}
