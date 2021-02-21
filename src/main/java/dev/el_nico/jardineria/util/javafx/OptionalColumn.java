package dev.el_nico.jardineria.util.javafx;

import java.util.Optional;

import javafx.scene.control.TableColumn;

// TODO eliminar esto
@Deprecated
public class OptionalColumn<E, V> extends TableColumn<E, Optional<V>> {

    public OptionalColumn(String name) {
        super(name);
        setCellFactory(CellFactory.get());
    }
}
