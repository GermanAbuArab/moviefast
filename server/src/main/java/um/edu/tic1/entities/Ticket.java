package um.edu.tic1.entities;


import javax.persistence.*;

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
    //private Date fecha; es necesario? se puede sacar de funcion



}
