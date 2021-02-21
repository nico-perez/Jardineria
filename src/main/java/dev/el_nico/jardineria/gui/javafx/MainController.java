package dev.el_nico.jardineria.gui.javafx;

import java.io.IOException;
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
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
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

        clientes.setPlaceholder(new Label("Sin datos"));
        pedidos.setPlaceholder(new Label("Sin datos"));

        clientes.getColumns()
                .addAll(Arrays.asList(Tables.newCol("Código", "codigo"), Tables.newCol("Nombre", "nombre"),
                        Tables.superCol("Contacto", Tables.optCol("Nombre", "nombreContacto"),
                                Tables.optCol("Apellido", "apellidoContacto"), Tables.newCol("Teléfono", "telefono"),
                                Tables.newCol("Fax", "fax")),
                        Tables.superCol("Dirección", Tables.newCol("Línea 1", "lineaDireccion1"),
                                Tables.optCol("Línea 2", "lineaDireccion2"), Tables.newCol("Ciudad", "ciudad"),
                                Tables.optCol("Región", "region"), Tables.optCol("País", "pais"),
                                Tables.optCol("Código Postal", "codigoPostal")),
                        Tables.optCol("Límite Crédito", "limiteCredito"),
                        Tables.optCol("Cód. Rep. Ventas", "codigoEmpleadoRepVentas")));

        pedidos.getColumns().addAll(Arrays.asList(Tables.newCol("Código", "codigo"),
                Tables.superCol("Fecha", Tables.newCol("del pedido", "fechaPedido"),
                        Tables.newCol("esperada", "fechaEsperada"), Tables.optCol("de entrega", "fechaEntrega")),
                Tables.newCol("Estado", "estado"), Tables.optCol("Comentarios", "comentarios"),
                Tables.newCol("Código Cliente", "codigoCliente")));
    }

    public @FXML void editar() throws IOException {
        
        if (!clientes.getSelectionModel().isEmpty()) {
            Stage editar = new Stage();
            editar.setResizable(false);
            FXMLLoader loginLoader = new FXMLLoader(App.class.getResource("editar_cliente.fxml"));
            Scene editScene = new Scene(loginLoader.load());
            EditarClienteController editarconbtroller = loginLoader.getController();
            editarconbtroller.setMainController(this);
            editarconbtroller.setStage(editar);
            editar.setScene(editScene);
            editar.initModality(Modality.APPLICATION_MODAL);
            editar.show();

            editarconbtroller.setDatos(clientes.getSelectionModel().getSelectedItem());
        }
    }

    public void anadir() throws IOException {
        Stage editar = new Stage();
        editar.setResizable(false);
        FXMLLoader loginLoader = new FXMLLoader(App.class.getResource("editar_cliente.fxml"));
        Scene editScene = new Scene(loginLoader.load());
        EditarClienteController editarconbtroller = loginLoader.getController();
        editarconbtroller.setMainController(this);
        editarconbtroller.setStage(editar);
        editar.setScene(editScene);
        editar.initModality(Modality.APPLICATION_MODAL);
        editar.show();
    }

    /* pkg */ void setStage(Stage stage) {
        this.stage = stage;
    }

    public void rellenarTablas() {

        App.worker.execute(() -> {
            List<Cliente> listaC = Jardineria.clientes().todos();
            ObservableList<Cliente> listaClientes = FXCollections.observableArrayList(listaC);
            Platform.runLater(() -> {
                clientes.setItems(listaClientes);
            });
        });

        App.worker.execute(() -> {
            List<Pedido> listaC = Jardineria.pedidos().todos();
            ObservableList<Pedido> listaClientes = FXCollections.observableArrayList(listaC);
            Platform.runLater(() -> {
                pedidos.setItems(listaClientes);
            });
        });
    }

    // TODO elimintar
    public void jsonTablas(List<Cliente> arrayClientes, List<Pedido> arrayPedidos) {
        
        App.worker.execute(() -> {
            ObservableList<Cliente> listaClientes = FXCollections.observableArrayList(arrayClientes);
            Platform.runLater(() -> {
                clientes.setItems(listaClientes);
            });
        });

        App.worker.execute(() -> {
            ObservableList<Pedido> listaClientes = FXCollections.observableArrayList(arrayPedidos);
            Platform.runLater(() -> {
                pedidos.setItems(listaClientes);
            });
        });
    }

    // TODO que horror
    public void actualizarCliente(Cliente c1) {

        FilteredList<Cliente> lif = clientes.getItems().filtered(c2 -> c2.getCodigo() == c1.getCodigo());
        boolean hay = lif.size() > 0;

        if (hay) {
            int i = clientes.getItems().indexOf(lif.get(0));
            clientes.getItems().set(i, c1);
        } else {
            clientes.getItems().add(c1);
        }
    }
}
