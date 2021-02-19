@SuppressWarnings("module") // automatic module name noseque nosecuantas
module nico.jardineria {
    // gui
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    opens dev.el_nico.jardineria.gui.javafx to javafx.fxml;
    exports dev.el_nico.jardineria.gui.javafx to javafx.graphics;

    // orm 
    // jope! un monton de cosas eh
    requires java.persistence;
    requires java.transaction;
    requires java.xml.bind;
    requires com.sun.xml.bind;
    requires com.fasterxml.classmate;
    requires transitive org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires org.jboss.logging;
    requires net.bytebuddy;
    requires dom4j; // ???
    requires mysql.connector.java; // ???????

    opens dev.el_nico.jardineria.modelo to org.hibernate.orm.core;
    exports dev.el_nico.jardineria.util.hibernate to org.hibernate.orm.core;

    // otros
    requires org.checkerframework.checker.qual;
    requires com.google.gson; // TODO a lo mejor gson tambien requiere opens pero no lo he comprobado
}
