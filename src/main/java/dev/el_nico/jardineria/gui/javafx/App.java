package dev.el_nico.jardineria.gui.javafx;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene mainScene, loginScene;

    /*pkg*/ static final ExecutorService worker = Executors.newSingleThreadExecutor();

    @Override
    public void start(Stage mainStage) throws IOException {       
        mainScene = new Scene(loadFXML("main"));
        mainStage.setScene(mainScene);
        mainStage.show();
        MainController.setStage(mainStage);

        Stage loginStage = new Stage();
        loginScene = new Scene(loadFXML("login"));
        loginStage.setScene(loginScene);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.show();
        LoginController.setStage(loginStage);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
