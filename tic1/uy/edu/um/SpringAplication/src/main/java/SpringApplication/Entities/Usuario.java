package SpringApplication.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name= "Usuario")
@Data
public class Usuario {
    @Id
    //@Column(name="UserName",nullable=false)
    private String userName;

    @Column(name="pasword",nullable=false)
    private String password;

    @Column(name="cedula", nullable=false)
    private long cedula;






}
