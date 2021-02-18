package dev.el_nico.jardineria.modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.checkerframework.checker.nullness.qual.NonNull;

@Entity
@IdClass(Pago.Clave.class)
public class Pago {
    
    @ManyToOne
    @JoinColumn(name = "codigo_cliente", nullable = false)
    private @NonNull @Id Cliente cliente;
    private @NonNull @Id String id_transaccion;

    private @NonNull String forma_pago;
    private @NonNull Calendar fecha_pago;
    private @NonNull Double total;

    /*pkg*/ Pago() {} // hibenrate

    @SuppressWarnings("unused")
    protected static class Clave implements Serializable {

        private static final long serialVersionUID = -2641267158642675892L;

        private Cliente cliente;
        private String id_transaccion;

        /*pkg*/ Clave() {}
        public Clave(Cliente cliente, String id_transaccion) {
            this.cliente = cliente;
            this.id_transaccion = id_transaccion;
        }
    }
}
