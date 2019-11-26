package um.edu.tic1.client.models;

import um.edu.tic1.commons.DTO.TicketDTO;

public class Ticket {

    private Long id;
    private Long funcionId;
    private String clienteId;


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
        ticketDTO.setFuncionId(this.getFuncionId());
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

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public void addAsiento(int y, int x) {
    }
}
