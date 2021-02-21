package dev.el_nico.jardineria.gui.javafx;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import dev.el_nico.jardineria.modelo.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarClienteController implements Initializable {
    
    private Stage stage;

    private MainController mainController;

    private @FXML TextField codigo;
    private @FXML TextField nombre;
    private @FXML TextField nom_cto;
    private @FXML TextField ape_cto;
    private @FXML TextField telefono;
    private @FXML TextField fax;
    private @FXML TextField dir1;
    private @FXML TextField dir2;
    private @FXML TextField ciudad;
    private @FXML TextField region;
    private @FXML TextField pais;
    private @FXML TextField cp;
    private @FXML TextField cod_rep;
    private @FXML TextField lim_cred;

    private List<TextField> todos;

    private @FXML Button confirmar;
    private @FXML Button resetear;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        todos = Arrays.asList(codigo, nombre, nom_cto, ape_cto, telefono, fax, dir1, dir2, ciudad, region, pais, cp,
                cod_rep, lim_cred);
    }

    public void setDatos(Cliente c) {
        codigo.setText(c.getCodigo().toString());
        nombre.setText(c.getNombre());
        nom_cto.setText(c.getNombreContacto().orElse(""));
        ape_cto.setText(c.getApellidoContacto().orElse(""));
        telefono.setText(c.getTelefono());
        fax.setText(c.getFax());
        dir1.setText(c.getLineaDireccion1());
        dir2.setText(c.getLineaDireccion2().orElse(""));
        ciudad.setText(c.getCiudad());
        region.setText(c.getRegion().orElse(""));
        pais.setText(c.getPais().orElse(""));
        cp.setText(c.getCodigoPostal().orElse(""));
        cod_rep.setText(c.getCodigoEmpleadoRepVentas().orElse(-1).toString());
        lim_cred.setText(c.getLimiteCredito().orElse(-1d).toString());
    }

    public void actualizarCliente() {
        try {
            Cliente nuevo = new Cliente.Builder(nombre.getText(), telefono.getText(), fax.getText(), dir1.getText(), ciudad.getText()).conCodigo(Integer.parseInt(codigo.getText())).buildOrThrow();
            mainController.actualizarCliente(nuevo);
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainController(MainController m) {
        mainController = m;
    }

    public void resetearCampos() {
        for (TextField t : todos) {
            t.setText("");
        }
    }
}
