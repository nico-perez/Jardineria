package dev.el_nico.jardineria.modelo;

import java.util.Calendar;
import java.util.Optional;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.el_nico.jardineria.excepciones.Assert;
import dev.el_nico.jardineria.excepciones.ExcepcionDatoNoValido;
import dev.el_nico.jardineria.util.AbstractBuilder;

/**
 * Objeto que representa a uno de los pedidos
 * según el script de la BBDD «Jardinería». Este
 * script declara los siguientes campos para la
 * tabla «pedido»:
 * 
 * <ul>
 * <li>codigo_pedido INTEGER NOT NULL</li>
 * <li>fecha_pedido date NOT NULL</li>
 * <li>fecha_esperada date NOT NULL</li>
 * <li>fecha_entrega date DEFAULT NULL</li>
 * <li>estado VARCHAR(15) NOT NULL</li>
 * <li>comentarios TEXT</li>
 * <li>codigo_cliente INTEGER NOT NULL</li>
 * </ul>
 */
public @Entity class Pedido {
    /** 
     * Demora mínima, en días, que puede tomar un
     * pedido en ser enviado.
     */
    private static final int DEMORA_MINIMA = 3;

    private @NonNull @Id Integer codigo_pedido;
    private @NonNull @Embedded Fecha fecha;
    private @NonNull String estado;
    private String comentarios;
    
    @ManyToOne
    private @NonNull Cliente codigo_cliente;

    private Pedido(Integer codigo, Fecha fecha, String estado, Cliente codigo_cliente) {
        this.codigo_pedido = codigo;
        this.fecha = fecha;
        this.estado = estado;
        this.codigo_cliente = codigo_cliente;
    }

    /** Devuelve el código de pedido. */
    public int getCodigo() {
        return codigo_pedido;
    }

    /** Devuelve la fecha del pedido. Nunca es null. */
    public Fecha getFecha() {
        return fecha;
    }

    /** Devuelve el estado del pedido. Nunca es null. */
    public String getEstado() {
        return estado;
    }

    /** Devuelve los comentarios del pedido. */
    public Optional<String> getComentarios() {
        return Optional.ofNullable(comentarios);
    }

    /** Devuelve el código de cliente del pedido. */
    public int getCodigoCliente() {
        return codigo_cliente.getCodigo();
    }

    /** Agrupa las fechas de un pedido. */
    public static @Embeddable class Fecha {

        private @NonNull @Temporal(TemporalType.DATE) Calendar pedido;
        private @NonNull @Temporal(TemporalType.DATE) Calendar esperada;
        private @Temporal(TemporalType.DATE) Calendar entrega;

        /**
         * Construye una nueva Fecha asignando a pedido
         * la fecha actual. Asigna a esperada la fecha
         * actual más demora_esperada días. Si demora_esperada
         * es menor que DEMORA_MINIMA, se asigna a la fecha
         * esperada el valor del pedido + DEMORA_MINIMA días.
         * @param demora_esperada Días que se espera que
         * tarde el pedido en llegar.
         */
        private Fecha(int demora_esperada) {
            this.pedido = Calendar.getInstance();
            (this.esperada = (Calendar)pedido.clone()).add(Calendar.DAY_OF_MONTH, demora_esperada);
        }

        private Fecha(Calendar pedido, Calendar esperada) {
            this.pedido = pedido;
            this.esperada = esperada;
        }

        /** La fecha en que se hizo el pedido. Nunca es null. */
        public Calendar pedido() {
            return pedido;
        }

        /** La fecha esperada de entrega del pedido. Nunca es null. */
        public Calendar esperada() {
            return esperada;
        }

        /** La fecha de entrega real del pedido. */
        public Optional<Calendar> entrega() {
            return Optional.ofNullable(entrega);
        }
    }

    /** Clase para buildear instancias válidas de Pedido. */
    public static class Builder extends AbstractBuilder<Pedido> {

        /** 
         * Para asegurar la validez de un Pedido deserializado,
         * que puede perfectamente contener datos sin sentido.
         * @param otro Un pedido que no se sabe si es válido.
         */
        public Builder(Pedido otro) {
            este = otro;
        }

        /**
         * Toma los datos obligatorios para formar un pedido válido.
         * Crea un builder con esos datos. Notar que los parámetros
         * pueden ser null, y que es en la función build donde se
         * comprueba la validez de estos.
         * @param codigo El código del pedido.
         * @param dias_demora Días que se espera que tarde en llegar el pedido.
         * @param estado No sé qué es esto.
         * @param codigo_cliente El código del cliente que realizó el pedido.
         */
        public Builder(int codigo, int dias_demora, String estado, Cliente codigo_cliente) {
            este = new Pedido(codigo, new Fecha(dias_demora), estado, codigo_cliente);
        }

        public Builder(int codigo, Calendar pedido, Calendar esperada, String estado, Cliente codigo_cliente) {
            este = new Pedido(codigo, new Fecha(pedido, esperada), estado, codigo_cliente);
        }

        /** Añade fecha de entrega al builder. */
        public Builder conFechaDeEntrega(Calendar entrega) {
            este.fecha.entrega = entrega;
            return this;
        }

        /** Añade comentarios al builder. */
        public Builder conComentarios(String comentarios) {
            este.comentarios = comentarios;
            return this;
        }

        /** 
         * Se asegura de que ninguno de los campos «NOT NULL» en
         * la tabla «pedido» tienen aquí su valor por defecto (null,
         * 0 o 0.0). Si alguno de estos es así, lanza una excepción y
         * devuelve null; si no, devuelve un pedido válido con los 
         * datos aportados al builder.
         * @return Un pedido válido o null si alguno de los datos es
         * incorrecto.
         */
        public Pedido buildOrThrow() throws ExcepcionDatoNoValido {
            // notn ull
            Assert.notNull("codigo_pedido", este.codigo_pedido);
            Assert.notNull("fecha_pedido", este.fecha.pedido);
            Assert.notNull("fecha_esperada", este.fecha.esperada);
            Assert.notNull("estado", este.estado);
            Assert.notNull("codigo_cliente", este.codigo_cliente);

            // Se asegura de que la fecha de espera es por lo menos
            // tres días posterior a la fecha del pedido.
            Calendar tres_dias_despues_del_pedido = (Calendar)este.fecha.pedido.clone();
            tres_dias_despues_del_pedido.add(Calendar.DAY_OF_MONTH, DEMORA_MINIMA);
            if (este.fecha.esperada.before(tres_dias_despues_del_pedido)) {
                throw new ExcepcionDatoNoValido("La fecha esperada debe ser, por lo menos, " +
                                                "tres días posterior a la fecha del pedido");
            }
            return este;
        }
    }
}
