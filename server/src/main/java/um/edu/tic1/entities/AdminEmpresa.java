package um.edu.tic1.entities;

import um.edu.tic1.commons.DTO.UsuarioDTO;

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

    public UsuarioDTO toDTO() {
        UsuarioDTO adminEmpresaDTO = new UsuarioDTO();
        adminEmpresaDTO.setName(this.getName());
        adminEmpresaDTO.setUserName(this.getUserName());
        adminEmpresaDTO.setPassword(this.getPassword());
        return adminEmpresaDTO ;

    }
}
