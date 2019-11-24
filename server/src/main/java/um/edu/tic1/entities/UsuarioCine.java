package um.edu.tic1.entities;

import um.edu.tic1.commons.DTO.UsuarioCineDTO;

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

    public UsuarioCineDTO toDTO() {
        UsuarioCineDTO usuarioCineDTO = new UsuarioCineDTO();
        usuarioCineDTO .setName(this.getName());
        usuarioCineDTO .setUserName(this.getUserName());
        usuarioCineDTO .setPassword(this.getPassword());
        return usuarioCineDTO ;

    }


}
