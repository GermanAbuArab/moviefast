package um.edu.tic1.client.models;


import um.edu.tic1.commons.DTO.FuncionDTO;

public class Funcion {
    private Long id;
    private String name = "nombreGenerico";
    private Long movieId;
    private String dimension;
    private String horaInicio;
    private String hora;
    private String horaFin;
    private Long salaId;
    private boolean[][] butacas;
    private Long cineId;

    private int duracion;


    public Funcion() {
    }

    public Funcion(FuncionDTO dto) {
        this.id=dto.getId();
        this.hora=dto.getHora();
        this.name=dto.getName();
        this.dimension=dto.getDimension();
        this.salaId=dto.getSalaId();
        this.movieId=dto.getMovieId();
        this.horaFin=dto.getHoraFin();
        this.horaInicio=dto.getHoraInicio();
        this.duracion=dto.getDuracion();
        this.butacas=dto.getButacas();
        this.cineId=dto.getCineId();
    }



    public FuncionDTO toDTO() {
        FuncionDTO funcionDTO = new FuncionDTO();
        //funcionDTO.setId(this.id);
        funcionDTO.setHora(this.hora);
        funcionDTO.setName(this.name);
        funcionDTO.setDimension(this.dimension);
        funcionDTO.setSalaId(this.salaId);
        funcionDTO.setMovieId(this.movieId);
        funcionDTO.setHoraInicio(this.horaInicio);
        funcionDTO.setHoraFin(this.horaFin);
        funcionDTO.setDuracion(this.duracion);
        funcionDTO.setButacas(this.butacas);
        funcionDTO.setCineId(this.cineId);
        return funcionDTO;
    }


    public void clearAll(){
        this.movieId = null;
        this.salaId = null;
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

    public Long getMovieid() {
        return movieId;
    }

    public void setMovieid(Long movieid) {
        this.movieId = movieid;
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
        return horaFin;
    }

    public void setHorafin(String horafin) {
        this.horaFin = horafin;
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

    public void setButacasConSala(Sala sala) {
        int x = sala.getX();
        int y = sala.getY();
        boolean[][] aux = new boolean[x][y];
        this.butacas = aux;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }


    public void reservaButaca(int y, int x) {
        butacas[x][y]=false;
    }
}
