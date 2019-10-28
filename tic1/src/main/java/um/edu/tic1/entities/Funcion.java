package um.edu.tic1.entities;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

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
    private String horaFin;
    private int[][] butacas;

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
        this.butacas = new int [sala.getX()][sala.getY()];
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
}