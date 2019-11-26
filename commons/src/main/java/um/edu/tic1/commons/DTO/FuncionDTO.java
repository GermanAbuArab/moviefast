package um.edu.tic1.commons.DTO;

public class FuncionDTO {

    private Long id;
    private String name;
    private Long movieId;
    private String dimension;
    private String horaInicio;
    private String hora;
    private String horaFin;
    private Long salaId;
    private boolean[][] butacas;
    private Long cineId;

    private int duracion;

    public FuncionDTO() {
    }

    public FuncionDTO(Long id, String name,Long movieId, String dimension, String horaInicio, String hora, String horaFin, Long salaId, boolean[][] butacas, int duracion) {
        this.id = id;
        this.name = name;
        this.movieId = movieId;
        this.dimension = dimension;
        this.horaInicio = horaInicio;
        this.hora = hora;
        this.horaFin = horaFin;
        this.salaId = salaId;
        this.butacas = butacas;
        this.duracion = duracion;
    }

    public Long getCineId() {
        return cineId;
    }

    public void setCineId(Long cineId) {
        this.cineId = cineId;
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

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
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

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
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
