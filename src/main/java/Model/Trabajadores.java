package Model;

public class Trabajadores {
    private int id;
    private String nombre;

    public Trabajadores(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Trabajadores(String nombre) {
        this.nombre = nombre;
    }

    public Trabajadores() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Trabajadores{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
