package um.edu.tic1.entities;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

@Entity
@Table(name = "funciones")
public class Funcion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    //private Movie movie;
    private String dimension;
    private String idioma;
    private boolean subs;
    private Date inicio;
    private Date cierre;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_sala")
    private Sala sala;

}