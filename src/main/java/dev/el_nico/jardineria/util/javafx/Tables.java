package dev.el_nico.jardineria.util.javafx;

import java.util.Optional;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Tables {
    private Tables(){}

    /** Columna normal, sin PropertyValueFactory */
    @SafeVarargs // no es safe pero bueno
    public static <E, V> TableColumn<E, V> superCol(String name, TableColumn<E, ?>... children) {
        TableColumn<E, V> parentColumn = new TableColumn<>(name);
        parentColumn.getColumns().addAll(children);
        return parentColumn;
    }

    /** Columna normal, con PropertyValueFactory */
    public static <E, V> TableColumn<E, V> newCol(String name, String getter) {
        TableColumn<E, V> tableColumn = new TableColumn<>(name);
        tableColumn.setCellFactory(CellFactory.get());
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(getter));
        return tableColumn;
    }

    /** Columna normal, con PropertyValueFactory y columna padre */
    public static <E, V> TableColumn<E, V> newCol(String name, String getter, TableColumn<E, ?> parent) {
        TableColumn<E, V> tableColumn = new TableColumn<>(name);
        tableColumn.setCellFactory(CellFactory.get());
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(getter));
        parent.getColumns().add(tableColumn);
        return tableColumn;
    }

    /** Columna para Optionals, con PropertyValueFactory */
    @Deprecated
    public static <E, V> TableColumn<E, Optional<V>> optCol(String name, String getter) {
        OptionalColumn<E, V> tableColumn = new OptionalColumn<>(name);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(getter));
        return tableColumn;
    }

    /** Columna para Optionals, con PropertyValueFactory y columna padre */
    @Deprecated
    public static <E, V> TableColumn<E, Optional<V>> optCol(String name, String getter, TableColumn<E, ?> parent) {
        OptionalColumn<E, V> tableColumn = new OptionalColumn<>(name);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(getter));
        parent.getColumns().add(tableColumn);
        return tableColumn;
    }
}
