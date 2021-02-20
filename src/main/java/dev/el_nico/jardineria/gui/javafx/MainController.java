package dev.el_nico.jardineria.gui.javafx;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import dev.el_nico.jardineria.dao.hibernate.Jardineria;
import dev.el_nico.jardineria.modelo.Cliente;
import dev.el_nico.jardineria.modelo.Pedido;
import dev.el_nico.jardineria.util.javafx.Tables;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class MainController implements Initializable {

    private Stage stage;

    private @FXML Button btnAgregarEntidad;
    private @FXML Button btnEliminarEntidad;
    private @FXML Button btnEditarEntidad;

    private @FXML TabPane contenedorTablas;
    private @FXML TableView<Cliente> clientes;
    private @FXML TableView<Pedido> pedidos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clientes.getColumns().addAll(Arrays.asList(
            Tables.newCol("Código", "codigo"),
            Tables.newCol("Nombre", "nombre"),
            Tables.optCol("Nombre", "nombreContacto"),
            Tables.optCol("Apellido", "apellidoContacto"),
            Tables.newCol("Teléfono", "telefono"),
            Tables.newCol("Fax", "fax"),
            Tables.newCol("Línea 1", "lineaDireccion1"),
            Tables.optCol("Línea 2", "lineaDireccion2"),
            Tables.newCol("Ciudad", "ciudad"),
            Tables.optCol("Región", "region"),
            Tables.optCol("País", "pais"),
            Tables.optCol("Código Postal", "codigoPostal"),
            Tables.optCol("Límite Crédito", "limiteCredito"),
            Tables.optCol("Cód. Rep. Ventas", "codigoEmpleadoRepVentas")
        ));
    }

    public @FXML void consultarBBDD() {
        Tab tab = new Tab();
        tab.setText("CODGIEO");
        contenedorTablas.getTabs().add(tab);
        System.out.println("clicado");
    }

    /* pkg */ void setStage(Stage stage) {
        this.stage = stage;
    }

    public void rellenarTablas() {
        System.out.println("HOLA DESDE RELLENAR TABLAS");
        App.worker.execute(() -> {
            List<Cliente> listaC = Jardineria.clientes().todos();
            ObservableList<Cliente> listaClientes = FXCollections.observableArrayList(listaC);
            Platform.runLater(() -> {
                clientes.setItems(listaClientes);
            });
        });
    }

}
