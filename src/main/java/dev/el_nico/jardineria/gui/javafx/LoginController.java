package dev.el_nico.jardineria.gui.javafx;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import dev.el_nico.jardineria.dao.gson.GsonDao;
import dev.el_nico.jardineria.dao.hibernate.Jardineria;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController implements Initializable {

    private MainController mainController;

    private Stage stage;

    private @FXML Button btnSqlIniciarSesion;
    private @FXML TextField txtSqlUsuario;
    private @FXML PasswordField pwdSqlContrasena;
    private @FXML Text txtSqlError;
    private @FXML Circle circuloCargando;

    private @FXML Label lblJsonRuta;
    private @FXML ListView<Path> lstJsonListaFicheros;
    private @FXML TextField txtJsonNuevoFichero;
    private @FXML Button btnJsonNuevoFichero;
    private @FXML Button btnJsonCargarFichero;

    /* pkg */ AtomicBoolean loginOk = new AtomicBoolean(false);

    private SimpleDateFormat sdf;
    private Timeline tl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sdf = new SimpleDateFormat("hh:mm:ss");

        tl = new Timeline(new KeyFrame(Duration.seconds(0), new KeyValue(circuloCargando.rotateProperty(), 0)),
                new KeyFrame(Duration.seconds(1.37), new KeyValue(circuloCargando.rotateProperty(), 360)));
        tl.setCycleCount(Timeline.INDEFINITE);

        try {
            Path p = new File("src/main/resources/dev/el_nico/jardineria/dao/gson").toPath();
            lblJsonRuta.setText(p.toAbsolutePath().toString());
            lstJsonListaFicheros.setCellFactory(o -> new ListCell<>(){
                @Override
                protected void updateItem(Path p, boolean empty) {
                    super.updateItem(p, empty);
                    if (p != null && !empty) {
                        
                        setText(p.getName(p.getNameCount() - 1).toString());
                    }
                }
            });
            Files.list(p).forEach(f -> lstJsonListaFicheros.getItems().add(f));

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    public void loginMySql() {
        String user = txtSqlUsuario.getText();
        String pass = pwdSqlContrasena.getText();

        txtSqlError.setVisible(false); // TODO usar bindings
        circuloCargando.setVisible(true);

        tl.play();

        App.worker.execute(() -> {
            final boolean loggedIn = Jardineria.login(user, pass);

            Platform.runLater(() -> {
                if (loggedIn) {
                    mainController.rellenarTablas();
                    stage.close();
                } else {
                    tl.pause();
                    circuloCargando.setVisible(false);
                    txtSqlError.setVisible(true);
                    txtSqlError.setText(
                            sdf.format(Calendar.getInstance().getTime()) + " - No se ha podido iniciar sesión");
                }
            });
        });
    }

    public void cargarJson() {
        if (!lstJsonListaFicheros.getSelectionModel().isEmpty()) {

            App.worker.execute(() -> {
                GsonDao dao = new GsonDao(lstJsonListaFicheros.getSelectionModel().getSelectedItem().toString());
            
                Platform.runLater(() -> {
                    mainController.jsonTablas(dao.clientes(), dao.pedidos());
                });
            });
            stage.close();

        } else {
            System.out.println("empty");
        }
    }

    /* pkg */ void setStage(Stage stage) {
        this.stage = stage;
    }

    /* pkg */ void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

}
