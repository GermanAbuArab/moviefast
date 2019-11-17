package um.edu.tic1.client.models;

public class UsuarioCine {

    private String userName;
    private Long name;
    private Long password;


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
        usuarioCineDTO .setUsername(this.getUserName());
        usuarioCineDTO .setPassword(this.getPassword());
        return usuarioCineDTO ;

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }
}
