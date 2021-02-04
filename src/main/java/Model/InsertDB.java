package Model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class InsertDB {

    //Método de consulta de trabajador por nombre
    public int buscarTrabajador(String nombre) {
        try {
            //Creamos y abrimos sesión
            SessionFactory myFactory = SessionManagement.getMyFactory();
            Session mySession = myFactory.openSession();
            //Creamos consulta con parámetro
            Query query = mySession.createQuery("FROM Trabajadores WHERE nombre LIKE :nombre");
            query.setParameter("nombre", nombre);
            ArrayList<Trabajadores> trabajador = (ArrayList<Trabajadores>) query.list();
            //Comprobamos que devuelve datos y retornamos el id
            if (trabajador.size() > 0) {
                return trabajador.get(0).getId();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return -1; //Si no devuelve datos retornamos -1
    }

    //Método de consulta de asistencia de trabajador por id de trabajador y fecha
    public Asistencias buscarAsistencia(int id, LocalDate fecha) {
        Asistencias asistencia = null; //Inicializamos asistencia en null
        try {
            //Creamos y abrimos sesión
            SessionFactory myFactory = SessionManagement.getMyFactory();
            Session mySession = myFactory.openSession();
            //Creamos consulta con parámetros
            Query query = mySession.createQuery("FROM Asistencias WHERE id_trabajador=:id AND fecha=:fecha");
            query.setParameter("id", id);
            query.setParameter("fecha", fecha);
            ArrayList<Asistencias> asistencias = (ArrayList<Asistencias>) query.list();
            //Comprobamos que devuelve datos y retornamos la asistencia que devuelve
            if (asistencias.size() > 0) {
                asistencia = new Asistencias(asistencias.get(0).getId(), asistencias.get(0).getFecha(),
                        asistencias.get(0).getHora_entrada(), asistencias.get(0).getHora_salida(), asistencias.get(0).getId_trabajador());
                return asistencia;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return asistencia; //Si no devuelve nada retornamos asistencia en null
    }

    //Método para el registro de hora de entrada
    public int registrarEntrada(String nombre, LocalDate fecha, LocalTime hora) {
        //Almacenamos el id del trabajador
        int idTrabajador = buscarTrabajador(nombre);
        //Comprobamos que existe trabajador
        if (idTrabajador != -1) {
            Asistencias asistencia = buscarAsistencia(idTrabajador, fecha);
            if (asistencia == null) {
                Asistencias registroEntrada = new Asistencias(fecha, hora, null, idTrabajador);
                try {
                    //Creamos y abrimos sesión
                    SessionFactory myFactory = SessionManagement.getMyFactory();
                    Session mySession = myFactory.openSession();
                    //Insertamos registro de asistencia con entrada
                    mySession.beginTransaction();
                    mySession.save(registroEntrada);
                    mySession.getTransaction().commit();

                    return 1;
                } catch (HibernateException e) {
                    e.printStackTrace();
                    System.out.println("Error de registro de entrada, prueba de nuevo");
                    return -2;
                }
            //Si la asistencia existe ya retornamos 0
            } else if (asistencia != null && asistencia.getHora_entrada() != null) {
                System.out.println("Ya hay registro de hora de entrada para ese trabajador");
                return 0;
            } else { //Si por alguna circunstancia existe asistencia sin hora de entrada la actualizamos
                Asistencias registroEntrada = new Asistencias(fecha, hora, null, idTrabajador);
                try {
                    //Creamos y abrimos sesión
                    SessionFactory myFactory = SessionManagement.getMyFactory();
                    Session mySession = myFactory.openSession();
                    //Insertamos registro de asistencia con entrada
                    mySession.beginTransaction();
                    mySession.update(registroEntrada);
                    mySession.getTransaction().commit();

                    return 1;
                } catch (HibernateException e) {
                    e.printStackTrace();
                    System.out.println("Error de registro de entrada, prueba de nuevo");
                    return -2;
                }
            }
        } else { //Si el trabajador no existe en la base de datos retornamos -1
            System.out.println("No existe un trabajador con ese nombre");
            return -1;
        }
    }

    //Método para el registro de hora de salida
    public int registrarSalida(String nombre, LocalDate fecha, LocalTime hora) {
        //Almacenamos id trabajador
        int idTrabajador = buscarTrabajador(nombre);
        //Comprobamos su existe trabajador
        if (idTrabajador != -1) {
            Asistencias asistencia = buscarAsistencia(idTrabajador, fecha);
            if (asistencia == null) { //Comprobamos que existe asistencia
                System.out.println("Debe registrar primero la hora de entrada");
                return 0;
            //Comprobamos que existe asistencia y hora de entrada
            } else if (asistencia != null && asistencia.getHora_entrada() != null && asistencia.getHora_salida() == null) {
                Asistencias registroSalida = new Asistencias(asistencia.getId(), asistencia.getFecha(), asistencia.getHora_entrada(), hora, asistencia.getId_trabajador());
                try {
                    //Creamos y abrimos sesión
                    SessionFactory myFactory = SessionManagement.getMyFactory();
                    Session mySession = myFactory.openSession();
                    //Actualizamos registro de asistencia con salida
                    mySession.beginTransaction();
                    mySession.update(registroSalida);
                    mySession.getTransaction().commit();

                    return 1;
                } catch (HibernateException e) {
                    e.printStackTrace();
                    System.out.println("Error de registro de salida, prueba de nuevo");
                    return -2;
                }
            //Comprobamos que existe asistencia pero no hora de entrada
            } else if (asistencia != null && asistencia.getHora_entrada() == null) {
                System.out.println("Debe registrar primero la hora de entrada");
                return 0;
            } else {
                System.out.println("Ya hay registro de hora de salida para ese trabajador");
                return -3;
            }
        } else {
            System.out.println("No existe un trabajador con ese nombre");
            return -1;
        }
    }
}
