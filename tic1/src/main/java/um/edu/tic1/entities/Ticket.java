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

    //private Usuario cliente;
    //private Funcion funcion;
    //private Date fecha;


}
