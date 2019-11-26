package um.edu.tic1.entities;

import um.edu.tic1.commons.DTO.FuncionDTO;

import javax.persistence.*;

@Entity
@Table(name = "funciones")
public class Funcion {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "funcion_id")
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id ")
    private Movie movie;

    private String dimension;
    private String horaInicio;
    private String hora;
    private String horaFin;
    private boolean[][] butacas;
    private Long cineId;

    private int duracion;

    //private String idioma;

    //private boolean subs;

    //private Date inicio;

    //private Date cierre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sala_id ")
    private Sala sala;

    public Funcion(){

    }
    public void clearAll(){
        this.movie = null;
        this.sala = null;
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

    public Movie getMovie() {
        return movie;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }


    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
        this.cineId = sala.getCine().getId();
        this.butacas = new boolean [sala.getX()][sala.getY()];
        initButacas(sala.getX(),sala.getY());
    }
    public void initButacas(int x ,int y){
        for (int i=0;i<x;i++ ){
            for (int j =0;j<y;j++){
                butacas[i][j]= true;
            }
        }
    }
    public void reservaButaca(int x, int y){
                butacas[x][y]= false;


    }


    public int getDuracion() {
        return duracion;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean[][] getButacas() {
        return butacas;
    }

    public FuncionDTO toDTO() {
        FuncionDTO funcionDTO = new FuncionDTO();
        funcionDTO.setId(this.id);
        funcionDTO.setHora(this.hora);
        funcionDTO.setName(this.name);
        funcionDTO.setDimension(this.dimension);
        funcionDTO.setSalaId(this.sala.getId());
        funcionDTO.setMovieId(this.movie.getId());
        funcionDTO.setHoraInicio(this.horaInicio);
        funcionDTO.setHoraFin(this.horaFin);
        funcionDTO.setDuracion(this.duracion);
        funcionDTO.setButacas(this.butacas);
        return funcionDTO;
    }

}