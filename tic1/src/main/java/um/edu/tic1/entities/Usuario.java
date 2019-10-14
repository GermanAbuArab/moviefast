package um.edu.tic1.entities;


import javax.persistence.*;

@Entity
@Table(name="usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
public class Usuario {


    @Id
    String userName;

    String name;
    String password;


    public Usuario(String userName, String name, String password) {
        this.userName = userName;
        this.name = name;
        this.password = password;
    }

    public String getUserName(){
        return userName;
    }


    public Usuario(){
    }
}
