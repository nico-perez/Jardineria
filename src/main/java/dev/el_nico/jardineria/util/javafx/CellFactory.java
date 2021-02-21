package dev.el_nico.jardineria.util.javafx;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public class CellFactory {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

    private CellFactory() {}

    @SuppressWarnings("unchecked")
    public static final <E, V> Callback<E, V> get() { // TODO esto hace un new cada vez cuando podria ser un singleton
        return o -> new TableCell<>() {
            protected void updateItem(Object item, boolean empty) {
                super.updateItem((V) item, empty);
                    
                Object val = item;
                if (item != null) {
                    if (item instanceof Optional) {
                        val = ((Optional<?>) item).orElse(null);
                    }

                    if (val instanceof Calendar) {
                        setText(sdf.format(((Calendar) val).getTime()));
                    } else {
                        setText("" + val);
                    }
                } else {
                    setText("");
                }
            };     
        };
                
    }

    public static interface Callback<E, V> extends javafx.util.Callback<TableColumn<E, V>, TableCell<E, V>> {}
}
