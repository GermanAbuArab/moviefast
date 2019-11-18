package um.edu.tic1.client.models;

public class ClienteFinal {

    private String userName;
    private String name;
    private String password;


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
