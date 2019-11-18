package um.edu.tic1.commons.DTO;

public class AdminEmpresaDTO {
    private String userName;
    private String name;
    private String password;


    public AdminEmpresaDTO() {
    }

    public AdminEmpresaDTO(String userName, String name, String password) {
        this.userName = userName;
        this.name = name;
        this.password = password;
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
