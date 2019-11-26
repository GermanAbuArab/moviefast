package um.edu.tic1.client.models;

import um.edu.tic1.commons.DTO.UsuarioDTO;

public class UsuarioCine {

    private String userName;
    private String name;
    private String password;


    public UsuarioCine() {
    }


    public UsuarioCine(UsuarioDTO dto) {
        this.name=dto.getName();
        this.userName=dto.getUserName();
        this.password=dto.getPassword();
    }

    public UsuarioDTO toDTO() {
        UsuarioDTO usuarioCineDTO = new UsuarioDTO();
        usuarioCineDTO .setName(this.getName());
        usuarioCineDTO .setUserName(this.getUserName());
        usuarioCineDTO .setPassword(this.getPassword());
        usuarioCineDTO.setTipo("CINE");
        return usuarioCineDTO ;

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
