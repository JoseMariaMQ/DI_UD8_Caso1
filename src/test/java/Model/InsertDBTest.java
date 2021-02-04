package Model;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.LocalTime;

public class InsertDBTest extends TestCase {
    private InsertDB insertDB;

    @Override
    public void setUp() throws Exception {
        insertDB = new InsertDB();
    }

    public void testRegistrarEntrada() {
        String nombres[] = {"Oscar", "Francisco", "Cristina", "Alberto", "Pilar", "Helena", "Manuel", "Carlos", "Tania", "Susana"};
        String nombresInventados[] = {"Jesús", "Fernando", "Agustín", "Heliodoro", "Juan"};
        LocalDate localDate = LocalDate.of(2021,2,3);
        LocalTime localTime = LocalTime.of(8, 0);

        //Prueba para inserción
        for (int i = 0; i < 3; i++) {
            assertEquals(1, insertDB.registrarEntrada(nombres[i], localDate, localTime));
        }
        //Prueba para la no inserción porque ya se inserto hora de entrada
        for (int i = 3; i < 10; i++) {
            assertEquals(0, insertDB.registrarEntrada(nombres[i], localDate, localTime));
        }
        //Prueba de no inserción porque no existe trabajador con ese nombre
        for (String nombre : nombresInventados) {
            assertEquals(-1, insertDB.registrarEntrada(nombre, localDate, localTime));
        }
    }

    public void testRegistrarSalida() {
        String nombres[] = {"Oscar", "Francisco", "Cristina", "Alberto", "Pilar", "Helena", "Manuel", "Carlos", "Tania", "Susana"};
        String nombresInventados[] = {"Jesús", "Fernando", "Agustín", "Heliodoro", "Juan"};
        LocalDate localDate = LocalDate.of(2021,2,3);
        LocalTime localTime = LocalTime.of(15, 0);

        //Prueba para la no inserción porque no hay registro de hora de entrada
        for (int i = 0; i < 3; i++) {
            assertEquals(0, insertDB.registrarSalida(nombres[i], localDate, localTime));
        }

        //Prueba para la inserción porque registro de hora de entrada y no de salida
        for (int i = 3; i < 6; i++) {
            assertEquals(1, insertDB.registrarSalida(nombres[i], localDate, localTime));
        }

        //Prueba de la no inserción porque ya hay registro de hora de entrada y de hora de salida
        for (int i = 6; i < 10; i++) {
            assertEquals(-3, insertDB.registrarSalida(nombres[i], localDate, localTime));
        }

        //Prueba de no inserción porque no existe trabajador con ese nombre
        for (String nombre : nombresInventados) {
            assertEquals(-1, insertDB.registrarSalida(nombre, localDate, localTime));
        }
    }
}