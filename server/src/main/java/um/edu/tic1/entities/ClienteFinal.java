package um.edu.tic1.entities;

import um.edu.tic1.commons.DTO.ClienteFinalDTO;

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

    public ClienteFinalDTO toDTO() {
        ClienteFinalDTO usuarioFinalDTO = new ClienteFinalDTO();
        usuarioFinalDTO.setName(this.getName());
        usuarioFinalDTO.setUserName(this.getUserName());
        usuarioFinalDTO.setPassword(this.getPassword());
        return usuarioFinalDTO;

    }

}

