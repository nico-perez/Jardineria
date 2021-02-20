package dev.el_nico.jardineria.util.javafx;

import java.util.Optional;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Tables {
    private Tables(){}

    /** Columna normal, con PropertyValueFactory */
    public static <E, V> TableColumn<E, V> newCol(String name, String getter) {
        TableColumn<E, V> tableColumn = new TableColumn<>(name);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(getter));
        return tableColumn;
    }

    /** Columna para Optionals, con PropertyValueFactory */
    public static <E, V> TableColumn<E, Optional<V>> optCol(String name, String getter) {
        OptionalColumn<E, V> tableColumn = new OptionalColumn<>(name);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(getter));
        return tableColumn;
    }
}
