package dev.el_nico.jardineria.gui.javafx;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dev.el_nico.jardineria.dao.hibernate.Jardineria;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene mainScene, loginScene;

    /*pkg*/ static final ExecutorService worker = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        // main
        FXMLLoader mainLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        mainScene = new Scene(mainLoader.load());
        MainController mainController = mainLoader.getController();
        mainController.setStage(mainStage);
        mainStage.setScene(mainScene);
        mainStage.show();
        
        // login
        Stage loginStage = new Stage();
        FXMLLoader loginLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        loginScene = new Scene(loginLoader.load());
        LoginController loginController = loginLoader.getController();
        loginController.setStage(loginStage);
        loginController.setMainController(mainController);
        loginStage.setScene(loginScene);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setOnCloseRequest(e -> Platform.exit());
        loginStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Jardineria.close();
        worker.shutdown();
    }


    
    // private static <T> Parent loadFXML(String fxml, /*out*/ T controlador) throws IOException {
    //     FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    //     controlador = fxmlLoader.<T>getController();
    //     return fxmlLoader.load();
    // }
}
