package dev.el_nico.jardineria.modelo;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.checkerframework.checker.nullness.qual.NonNull;

@Entity
@Table(name = "detalle_pedido")
@IdClass(DetallePedido.Clave.class)
public class DetallePedido {

    @ManyToOne(fetch = FetchType.LAZY, cascade = { MERGE, PERSIST, REFRESH })
    @JoinColumn(name = "codigo_pedido", nullable = false)
    private @NonNull @Id Pedido pedido;

    @ManyToOne(cascade = { MERGE, PERSIST, REFRESH })
    @JoinColumn(name = "codigo_producto", nullable = false)
    private @NonNull @Id Producto producto;
    
    //private @NonNull @EmbeddedId Id id;
    private @NonNull Integer cantidad;
    private @NonNull Integer numero_linea;
    private @NonNull Double precio_unidad;

    /*pkg*/ DetallePedido() {} // hibernate

    public Integer getCodigoPedido() {
        return pedido.getCodigo();
    }

    public String getCodigo() {
        return producto.getCodigo();
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
        return numero_linea + " (Cod.Ped: " + pedido.getCodigo() + ", Cod.Pro: " + producto.getCodigo() + ") -> " + cantidad + " a " + precio_unidad + "/ud.";
    }

    @SuppressWarnings("unused") // es que no se usan :/
    protected static class Clave implements Serializable {
        
        private static final long serialVersionUID = 9026913262449782076L;

        private Pedido pedido;
        private Producto producto;

        /*pkg*/ Clave() {} // hibeernate?
        public Clave(Pedido pedido, Producto producto) {
            this.pedido = pedido;
            this.producto = producto;
        }
    }
}
