package um.edu.tic1.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class AdminEmpresa extends Usuario {

    public AdminEmpresa(String userName, String name, String password) {
        super(userName, name, password);
    }

    public AdminEmpresa() {
    }
}
