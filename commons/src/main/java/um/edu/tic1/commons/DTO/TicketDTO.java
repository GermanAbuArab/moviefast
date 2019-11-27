package um.edu.tic1.commons.DTO;

import java.util.ArrayList;
import java.util.List;

public class TicketDTO {

    private Long id;
    private Long funcionId;
    private String clienteId;
    private ArrayList<Integer> asientos= new ArrayList<>(150);



    public TicketDTO() {
    }

    public TicketDTO(Long id, Long funcionId, String clienteId,ArrayList<Integer> asientos) {
        this.id = id;
        this.funcionId = funcionId;
        this.clienteId = clienteId;
        this.asientos = asientos;
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

    public ArrayList<Integer> getAsientos() {
        return asientos;
    }

    public void setAsientos(ArrayList<Integer> asientos) {
        this.asientos = asientos;
    }
}
