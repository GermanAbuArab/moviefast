package um.edu.tic1.client.models;

import um.edu.tic1.commons.DTO.TicketDTO;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private Long id;
    private Long funcionId;
    private String clienteId;
    private List<Integer> asientos= new ArrayList<>(150);
    private Integer asientoCol,asientosFila;

    private String movie;
    private String sala;

    public Ticket() {
    }

    public Integer getAsientoCol() {
        return asientoCol;
    }

    public Integer getAsientosFila() {
        return asientosFila;
    }

    public void setAsientosFila(Integer asientosFila) {
        this.asientosFila = asientosFila;
    }

    public Ticket(TicketDTO dto) {
        this.clienteId=dto.getClienteId();
        this.id=dto.getId();
        this.funcionId=dto.getFuncionId();
    }

    public TicketDTO toDTO() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setClienteId(this.getClienteId());
        ticketDTO.setAsientos(this.asientos);
        if (this.id != null) ticketDTO.setId(this.getId());
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

    public void setAsientoCol(Integer asientoCol) {
        this.asientoCol = asientoCol;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
}
