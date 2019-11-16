package um.edu.tic1.client.models;

public class AdminEmpresa {
    private String userName;
    private Long name;
    private Long password;


    public AdminEmpresa() {
    }


    public AdminEmpresa(AdminEmpresaDTO dto) {
        this.name=dto.getName();
        this.userName=dto.getUserName();
        this.password=dto.getPassword();
    }

    public AdminEmpresaDTO toDTO() {
        AdminEmpresaDTO adminEmpresaDTO = new AdminEmpresa();
        adminEmpresaDTO.setName(this.getName());
        adminEmpresaDTO.setUsername(this.getUserName());
        adminEmpresaDTO.setPassword(this.getPassword());
        return adminEmpresaDTO ;

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
