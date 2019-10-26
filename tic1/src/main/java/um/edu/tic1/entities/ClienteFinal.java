package um.edu.tic1.entities;

import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("FINAL")
public class ClienteFinal extends Usuario {

    public ClienteFinal(String userName, String name, String password) {
        super(userName, name, password);
    }

}
