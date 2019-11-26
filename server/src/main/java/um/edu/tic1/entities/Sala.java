package um.edu.tic1.entities;


import um.edu.tic1.commons.DTO.SalaDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sala_id")
    private Long id;
    private int x;
    private int y;
    private long cineId;

    private String name;

    private int capacidad;

    @ManyToOne
            //(cascade = CascadeType.ALL)
    @JoinColumn(name = "cine_id")
    private Cine cine;

    private boolean tresD;
    private boolean cuatroD;

    public Sala(){
        this.tresD = false;
        this.cuatroD = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isTresD() {
        return tresD;
    }

    public void setTresD(boolean tresD) {
        this.tresD = tresD;
    }

    public boolean isCuatroD() {
        return cuatroD;
    }

    public void setCuatroD(boolean cuatroD) {
        this.cuatroD = cuatroD;
    }


    public SalaDTO toDTO() {
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setCineId(this.getCine().getId());
        salaDTO.setId(this.getId());
        salaDTO.setName(this.getName());
        salaDTO.setCapacidad(this.getCapacidad());
        salaDTO.setCuatroD(this.isCuatroD());
        salaDTO.setTresD(this.isTresD());
        salaDTO.setX(this.getX());
        salaDTO.setY(this.getY());
        return salaDTO;
    }


    public Sala(SalaDTO dto){
        this.cineId = dto.getCineId();
        this.id = dto.getId();
        this.name = dto.getName();
        this.capacidad = dto.getCapacidad();
        this.cuatroD = dto.isCuatroD();
        this.tresD = dto.isTresD();
        this.x = dto.getX();
        this.y = dto.getY();

    }

}
