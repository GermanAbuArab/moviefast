package um.edu.tic1.entities;


import um.edu.tic1.commons.DTO.TicketDTO;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public TicketDTO toDTO() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setClienteId(this.getCliente().getName());
        ticketDTO.setId(this.getId());
        ticketDTO.setFuncionId(this.getFuncion().getId());
        return ticketDTO;

    }



}
