package Model;

import Model.InsertDB;

import java.time.LocalDate;
import java.time.LocalTime;

public class Pruebas {
    public static void main(String[] args) {
        InsertDB insertDB = new InsertDB();
//        insertDB.buscarTrabajador("Fernanda");
//        insertDB.buscarAsistencias(7);
        String nombre = "Susana";
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();

        int info = insertDB.registrarEntrada(nombre, fecha, hora);
        System.out.println(info);
    }
}
