package um.edu.tic1.client.models;


import um.edu.tic1.commons.DTO.UsuarioDTO;

public class AdminEmpresa {
    private String userName;
    private String name;
    private String password;


    public AdminEmpresa() {
    }


    public AdminEmpresa(UsuarioDTO dto) {
        this.name=dto.getName();
        this.userName=dto.getUserName();
        this.password=dto.getPassword();
    }

    public UsuarioDTO toDTO() {
        UsuarioDTO adminEmpresaDTO = new UsuarioDTO();
        adminEmpresaDTO.setName(this.getName());
        adminEmpresaDTO.setUserName(this.getUserName());
        adminEmpresaDTO.setPassword(this.getPassword());
        return adminEmpresaDTO ;

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
