package um.edu.tic1.client.models;

import tic1.commons.transfers.FunctionDTO;

import javax.xml.soap.SAAJResult;
import java.time.LocalDateTime;

public class Funcion {
    private Long id;
    private String name;
    private String movieid;
    private String dimension;
    private String horaInicio;
    private String hora;
    private String horafin;
    private String salaId;
    private boolean[][] butacas;

    private int duracion;



    public Funcion() {
    }

    public Funcion(FunctionDTO dto) {
        this.setDate(dto.getStartTime());
        this.setMovie(new Movie(dto.getMovie()));
        this.setSecondId(dto.getSala());

    }

}
    public void clearAll(){
        this.movieid = null;
        this.salaid = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieid() {
        return movieid;
    }

    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }

    public boolean[][] getButacas() {
        return butacas;
    }

    public void setButacas(boolean[][] butacas) {
        this.butacas = butacas;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
