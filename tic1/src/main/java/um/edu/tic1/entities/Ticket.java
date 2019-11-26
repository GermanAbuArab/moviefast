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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id ")
    private Usuario cliente;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "funcion_id ")
    private Funcion funcion;

    private ArrayList<Integer> asientos = new ArrayList<>(150);
    //private Date fecha; es necesario? se puede sacar de funcion
    private String name,sala,clienteName,horaInicio ;
    private Integer asientoCol,asientosFila;

    public Funcion getFuncion() {
        return funcion;
    }

    public String getName() {
        return name;
    }

    public String getSala() {
        return funcion.getSala().getName();
    }

    public Integer getAsientoCol() {
        return asientoCol;
    }

    public void setAsientoCol(Integer asientoCol) {
        this.asientoCol = asientoCol;
    }

    public Integer getAsientosFila() {
        return asientosFila;
    }

    public void setAsientosFila(Integer asientosFila) {
        this.asientosFila = asientosFila;
    }

    public String getClienteName() {
        return cliente.getName();
    }

    public String getHoraInicio() {
        return funcion.getHoraInicio();
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

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }


    public void setAsientos(ArrayList<Integer> asientos) {
        this.asientos = asientos;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
        name = funcion.getMovie().getName();
    }
}
