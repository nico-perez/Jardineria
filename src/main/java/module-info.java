module nico.jardineria {
    // gui
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    opens dev.el_nico.jardineria.gui.javafx to javafx.fxml;
    exports dev.el_nico.jardineria.gui.javafx;

    // orm
    requires java.persistence;
    requires transitive org.hibernate.orm.core;
    opens dev.el_nico.jardineria.modelo to org.hibernate.orm.core;
    exports dev.el_nico.jardineria.util.hibernate to org.hibernate.orm.core;
    
    // otros
    requires org.checkerframework.checker.qual;
    requires com.google.gson; // TODO a lo mejor gson tambien requiere opens pero no lo he comprobado
}
