package um.edu.tic1.entities;


import javax.persistence.*;


@Entity
@Table(name="salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
