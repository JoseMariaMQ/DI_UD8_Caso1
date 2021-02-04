module DI.UD8.Caso1 {
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires com.sun.xml.bind;
    requires net.bytebuddy;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires com.fasterxml.classmate;

    opens Controller to javafx.fxml;
    opens Model to com.fasterxml.classmate, org.hibernate.orm.core;
    exports Controller;
    exports Model;

}