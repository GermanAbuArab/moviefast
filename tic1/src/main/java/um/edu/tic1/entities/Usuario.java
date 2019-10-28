package um.edu.tic1.entities;


import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
@Table(name="usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {

    @Id
    @Column(name = "usuario_id")
    @NotNull
    private String userName;

    private String name;
    private String password;


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
