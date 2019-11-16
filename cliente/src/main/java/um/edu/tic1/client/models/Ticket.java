package um.edu.tic1.client.models;

public class Ticket {

    private Long id;
    private Long funcionId;
    private Long clienteId;


    public Ticket() {
    }


    public Ticket(TicketDTO dto) {
        this.clienteId=dto.getClienteId();
        this.id=dto.getId();
        this.funcionId=dto.getFuncionId();
    }

    public TicketDTO toDTO() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setClienteId(this.getClienteId());
        ticketDTO.setId(this.getId());
        ticketDTO.setFuncionID(this.getFuncionId());
        return ticketDTO;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getFuncionId() {
        return funcionId;
    }

    public void setFuncionId(Long funcionId) {
        this.funcionId = funcionId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
