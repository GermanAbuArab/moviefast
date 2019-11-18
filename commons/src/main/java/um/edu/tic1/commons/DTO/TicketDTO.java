package um.edu.tic1.commons.DTO;

public class TicketDTO {

    private Long id;
    private Long funcionId;
    private String clienteId;


    public TicketDTO() {
    }

    public TicketDTO(Long id, Long funcionId, String clienteId) {
        this.id = id;
        this.funcionId = funcionId;
        this.clienteId = clienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
