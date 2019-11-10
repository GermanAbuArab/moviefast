package um.edu.tic1.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CINE")
public class UsuarioCine extends Usuario {


    public UsuarioCine(String userName, String name, String password) {
        super(userName, name, password);
    }

    public UsuarioCine() {
    }
}
