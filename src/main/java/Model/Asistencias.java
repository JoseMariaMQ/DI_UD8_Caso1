package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Asistencias {
    private int id;
    private LocalDate fecha;
    private LocalTime hora_entrada;
    private LocalTime hora_salida;
    private int id_trabajador;

    public Asistencias(int id, LocalDate fecha, LocalTime hora_entrada, LocalTime hora_salida, int id_trabajador) {
        this.id = id;
        this.fecha = fecha;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.id_trabajador = id_trabajador;
    }

    public Asistencias(LocalDate fecha, LocalTime hora_entrada, LocalTime hora_salida, int id_trabajador) {
        this.fecha = fecha;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.id_trabajador = id_trabajador;
    }

    public Asistencias() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(LocalTime horaEntrada) {
        this.hora_entrada = horaEntrada;
    }

    public LocalTime getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(LocalTime horaSalida) {
        this.hora_salida = horaSalida;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Asistencias{");
        sb.append("id=").append(id);
        sb.append(", fecha=").append(fecha);
        sb.append(", horaEntrada=").append(hora_entrada);
        sb.append(", horaSalida=").append(hora_salida);
        sb.append(", idTrabajador=").append(id_trabajador);
        sb.append('}');
        return sb.toString();
    }
}
