package dev.el_nico.jardineria.gui.javafx;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dev.el_nico.jardineria.util.hibernate.SesionHibernate;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController {

    private static Stage stage;

    private @FXML Button btnSqlIniciarSesion;
    private @FXML TextField txtSqlUsuario;
    private @FXML PasswordField pwdSqlContrasena;
    private @FXML Text txtSqlError;
    private @FXML Circle circuloCargando;

    private @FXML Label lblJsonRuta;
    private @FXML ListView<?> lstJsonListaFicheros;
    private @FXML TextField txtJsonNuevoFichero;
    private @FXML Button btnJsonNuevoFichero;
    private @FXML Button btnJsonCargarFichero;

    private final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    private Timeline tl;

    public void loginMySql() {
        String user = txtSqlUsuario.getText();
        String pass = pwdSqlContrasena.getText();

        txtSqlError.setVisible(false);
        circuloCargando.setVisible(true);

        if (tl == null) {
            tl = new Timeline(
                new KeyFrame(Duration.seconds(1.8), new KeyValue(circuloCargando.rotateProperty(), 360)));
            tl.setCycleCount(Timeline.INDEFINITE);
        }
        tl.play();

        App.worker.submit(() -> {
            final boolean loggedIn = SesionHibernate.login(user, pass);

            Platform.runLater(() -> {
                if (loggedIn) {
                    stage.close();
                } else {
                    tl.stop();
                    circuloCargando.setVisible(false);
                    txtSqlError.setVisible(true);
                    txtSqlError.setText(sdf.format(Calendar.getInstance().getTime()) + " - No se ha podido iniciar sesión");
                }
            });
        });
    }

	/*pkg*/ static void setStage(Stage stage) {
        LoginController.stage = stage;
	}
}
