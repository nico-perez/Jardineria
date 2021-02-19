package dev.el_nico.jardineria.gui.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainController {

    private static Stage stage;

    private @FXML Button btnAgregarEntidad;
    private @FXML Button btnEliminarEntidad;
    private @FXML Button btnEditarEntidad;

    private @FXML TabPane contenedorTablas;

    public @FXML void consultarBBDD() {
        Tab tab = new Tab();
        tab.setText("CODGIEO");
        contenedorTablas.getTabs().add(tab);
        System.out.println("clicado");
    }

	/*pkg*/ static void setStage(Stage stage) {
        MainController.stage = stage;
	}
}
