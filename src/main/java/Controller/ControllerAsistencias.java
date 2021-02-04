package Controller;

import Model.InsertDB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Thread.sleep;

public class ControllerAsistencias implements EventHandler<ActionEvent> {

    public TextField textFieldNombre;
    public Button buttonEntrada;
    public Button buttonSalida;
    public Label labelMensaje;
    public Label labelHora;

    public void initialize() {
        buttonEntrada.setOnAction(this::registrarEntrada);
        buttonSalida.setOnAction(this::registrarSalida);

        //Evento que controla el click en el textField
        textFieldNombre.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                labelMensaje.setText(null);
            }
        });

        //Método para actualizar hora e insertar en label
        reloj();
    }

    //Método para registrar hora de entrada
    private void registrarEntrada(ActionEvent actionEvent) {
        if (actionEvent.getSource() == buttonEntrada) {
            if (textFieldNombre.getText().isEmpty()) {
                labelMensaje.setTextFill(Color.web("red"));
                labelMensaje.setText("Introduce tu nombre");
            } else {
                InsertDB insertDB = new InsertDB();
                String nombre = textFieldNombre.getText();
                LocalDate localDate = LocalDate.now();
                LocalTime localTime = LocalTime.now();
                //Formateamos hora para mostrarla en el mensaje
                DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
                //Ejecutamos método de la clase que interactúa con la base de datos
                int result = insertDB.registrarEntrada(nombre, localDate, localTime);
                textFieldNombre.setText("");
                //Comprobamos resultado para mostrar mensajes
                switch (result) {
                    case 1:
                        labelMensaje.setTextFill(Color.web("green"));
                        labelMensaje.setText("Entrada a las " + localTime.format(formateador) + " para " + nombre + " registrada");
                        break;
                    case 0:
                        labelMensaje.setTextFill(Color.web("red"));
                        labelMensaje.setText("Ya hay un registro de entrada para " + nombre);
                        break;
                    case -1:
                        labelMensaje.setTextFill(Color.web("red"));
                        labelMensaje.setText("No existe trabajador con nombre " + nombre);
                        break;
                    case -2:
                        labelMensaje.setTextFill(Color.web("red"));
                        labelMensaje.setText("Error de registro, prueba de nuevo");
                        break;
                }
            }
        }
    }

    //Método para registrar hora de salida
    private void registrarSalida(ActionEvent actionEvent) {
        if (actionEvent.getSource() == buttonSalida) {
            if (textFieldNombre.getText().isEmpty()) {
                labelMensaje.setTextFill(Color.web("red"));
                labelMensaje.setText("Introduce tu nombre");
            } else {
                InsertDB insertDB = new InsertDB();
                String nombre = textFieldNombre.getText();
                LocalDate localDate = LocalDate.now();
                LocalTime localTime = LocalTime.now();
                //Formateamos hora para mostrarla en el mensaje
                DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
                //Ejecutamos método de la clase que interactúa con la base de datos
                int result = insertDB.registrarSalida(nombre, localDate, localTime);
                textFieldNombre.setText("");
                //Comprobamos resultado para mostrar mensajes
                switch (result) {
                    case 1:
                        labelMensaje.setTextFill(Color.web("green"));
                        labelMensaje.setText("Salida a las " + localTime.format(formateador) + " para " + nombre + " registrada");
                        break;
                    case 0:
                        labelMensaje.setTextFill(Color.web("red"));
                        labelMensaje.setText("Primero debe registrar un entrada para " + nombre);
                        break;
                    case -1:
                        labelMensaje.setTextFill(Color.web("red"));
                        labelMensaje.setText("No existe trabajador con nombre " + nombre);
                        break;
                    case -3:
                        labelMensaje.setTextFill(Color.web("red"));
                        labelMensaje.setText("Ya hay un registro de salida para " + nombre);
                        break;
                    case -2:
                        labelMensaje.setTextFill(Color.web("red"));
                        labelMensaje.setText("Error de registro, prueba de nuevo");
                        break;
                }
            }
        }
    }

    //Método que actualiza la hora para mostrarla en la ventana
    public void reloj() {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //Ejecutamos el runnable más tarde para no crear conflicto con JavaFX
                        Platform.runLater(() -> labelHora.setText(String.valueOf(formateador.format(LocalTime.now()))));
                        sleep(500);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
