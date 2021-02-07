package dev.el_nico.jardineria.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.checkerframework.checker.nullness.qual.NonNull;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {
    
    private @NonNull @EmbeddedId Id id;
    private @NonNull Integer cantidad;
    private @NonNull Integer numero_linea;
    private @NonNull Double precio_unidad;

    private DetallePedido() {}

    public Integer getCodigoPedido() {
        return id.codigo_pedido;
    }

    public String getCodigoProducto() {
        return id.codigo_producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Integer getNumeroLinea() {
        return numero_linea;
    }

    public Double getPrecioUnidad() {
        return precio_unidad;
    }

    @Override
    public String toString() {
        return numero_linea + " (Cod.Ped: " + id.codigo_pedido + ", Cod.Pro: " + id.codigo_producto + ") -> " + cantidad + " a " + precio_unidad + "/ud.";
    }

    public static @Embeddable class Id implements Serializable {
        
        private static final long serialVersionUID = 9026913262449782076L;
        private @NonNull Integer codigo_pedido;
        private @NonNull String codigo_producto;

        public Id(){}
        public Id(Integer codigo_pedido, String codigo_producto) {
            this.codigo_pedido = codigo_pedido;
            this.codigo_producto = codigo_producto;
        }
    }
}
