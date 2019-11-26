package um.edu.tic1.client.models;

import um.edu.tic1.commons.DTO.UsuarioDTO;

public class ClienteFinal {

    private String userName;
    private String name;
    private String password;


    public ClienteFinal() {
    }


    public ClienteFinal(UsuarioDTO dto) {
        this.name=dto.getName();
        this.userName=dto.getUserName();
        this.password=dto.getPassword();
    }

    public ClienteFinal(String userName, String nombre, String contra) {

    }

    public UsuarioDTO toDTO() {
        UsuarioDTO usuarioFinalDTO = new UsuarioDTO();
        usuarioFinalDTO.setName(this.getName());
        usuarioFinalDTO.setUserName(this.getUserName());
        usuarioFinalDTO.setPassword(this.getPassword());
        return usuarioFinalDTO;

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
