package um.edu.tic1.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FINAL")
public class ClienteFinal extends Usuario {
    public ClienteFinal() {
    }

    public ClienteFinal(String userName, String name, String password) {
        super(userName, name, password);
    }

}
