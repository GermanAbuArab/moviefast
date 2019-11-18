package um.edu.tic1.client.models;

import um.edu.tic1.commons.DTO.UsuarioCineDTO;

public class UsuarioCine {

    private String userName;
    private String name;
    private String password;


    public UsuarioCine() {
    }


    public UsuarioCine(UsuarioCineDTO dto) {
        this.name=dto.getName();
        this.userName=dto.getUserName();
        this.password=dto.getPassword();
    }

    public UsuarioCineDTO toDTO() {
        UsuarioCineDTO usuarioCineDTO = new UsuarioCineDTO();
        usuarioCineDTO .setName(this.getName());
        usuarioCineDTO .setUserName(this.getUserName());
        usuarioCineDTO .setPassword(this.getPassword());
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
