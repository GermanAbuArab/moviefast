package um.edu.tic1.commons.DTO;

public class SalaDTO {

    private long id;
    private String name;
    private int x;
    private int y;
    private Long cineId;
    private int capacidad;
    private boolean tresD;
    private boolean cuatroD;


    public SalaDTO() {
    }

    public SalaDTO(long id, String name, int x, int y, Long cineId, int capacidad, boolean tresD, boolean cuatroD) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.cineId = cineId;
        this.capacidad = capacidad;
        this.tresD = tresD;
        this.cuatroD = cuatroD;
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
