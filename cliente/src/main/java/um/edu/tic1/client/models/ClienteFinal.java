package um.edu.tic1.client.models;

public class ClienteFinal {

    private String userName;
    private Long name;
    private Long password;


    public ClienteFinal() {
    }


    public ClienteFinal(ClienteFinalDTO dto) {
        this.name=dto.getName();
        this.userName=dto.getUserName();
        this.password=dto.getPassword();
    }

    public ClienteFinalDTO toDTO() {
        ClienteFinalDTO usuarioFinalDTO = new ClienteFinal();
        usuarioFinalDTO.setName(this.getName());
        usuarioFinalDTO.setUsername(this.getUserName());
        usuarioFinalDTO.setPassword(this.getPassword());
        return usuarioFinalDTO;

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
