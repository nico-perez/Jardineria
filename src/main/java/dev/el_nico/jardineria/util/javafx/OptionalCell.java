package dev.el_nico.jardineria.util.javafx;

import java.util.HashMap;
import java.util.Optional;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class OptionalCell<E, V> extends TableCell<E, Optional<V>> {

    private static final HashMap<Integer, Callback<?, ?>> uniqueFactories = new HashMap<>();

    /**
     * he hecho esto pero despues he caido en que no tengo ni idea de 
     * si es mas rapido o no que hacer un callback nuevo cada vez
     */
    @SuppressWarnings("unchecked")
    public static final <E, V> Callback<TableColumn<E, Optional<V>>, TableCell<E, Optional<V>>> getFactory() {
        int hash = ((V) new Object()).getClass().hashCode();
        return (Callback<TableColumn<E, Optional<V>>, TableCell<E, Optional<V>>>) 
            uniqueFactories.computeIfAbsent(hash, o -> new Callback<TableColumn<E, Optional<V>>, TableCell<E, Optional<V>>>() {
                @Override
                public TableCell<E, Optional<V>> call(TableColumn<E, Optional<V>> arg0) {
                    return new OptionalCell<E, V>();
                }
            });
    };

    @Override
    protected void updateItem(Optional<V> item, boolean empty) {
        super.updateItem(item, empty);
        setText(item == null ? "" : "" + item.orElse(null));
    }
}
