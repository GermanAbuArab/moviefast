package um.edu.tic1.client.models;


import um.edu.tic1.commons.DTO.SalaDTO;

public class Sala {
    private Long id;
    private String name;
    private int x;
    private int y;
    private Long cineId;
    private int capacidad;
    private boolean tresD;
    private boolean cuatroD;

    @Override
    public String toString() {
        return this.name;
    }

    public Sala() {
    }


    public Sala(SalaDTO dto) {
        this.cineId=dto.getCineId();
        this.id=dto.getId();
        this.name=dto.getName();
        this.capacidad=dto.getCapacidad();
        this.cuatroD=dto.isCuatroD();
        this.tresD=dto.isTresD();
        this.x=dto.getX();
        this.y=dto.getY();
    }

    public SalaDTO toDTO() {
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setCineId(this.getCineId());
        salaDTO.setName(this.getName());
        salaDTO.setCapacidad(this.getCapacidad());
        salaDTO.setCuatroD(this.isCuatroD());
        salaDTO.setTresD(this.isTresD());
        salaDTO.setX(this.getX());
        salaDTO.setY(this.getY());
        return salaDTO;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getCineId() {
        return cineId;
    }

    public void setCineId(Long cineId) {
        this.cineId = cineId;
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

}

