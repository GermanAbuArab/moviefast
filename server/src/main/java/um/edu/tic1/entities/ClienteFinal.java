package um.edu.tic1.entities;


import um.edu.tic1.commons.DTO.UsuarioDTO;

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

    public ClienteFinal(UsuarioDTO dto){
        super(dto.getName(),dto.getUserName(),dto.getPassword());

    }
    public UsuarioDTO toDTO() {
        UsuarioDTO usuarioFinalDTO = new UsuarioDTO();
        usuarioFinalDTO.setName(this.getName());
        usuarioFinalDTO.setUserName(this.getUserName());
        usuarioFinalDTO.setPassword(this.getPassword());
        return usuarioFinalDTO;

    }

}

