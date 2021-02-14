package dev.el_nico.jardineria.modelo;

import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;
import org.hibernate.annotations.Parameter;

import dev.el_nico.jardineria.excepciones.ExcepcionDatoNoValido;
import dev.el_nico.jardineria.excepciones.ExcepcionFormatoIncorrecto;
import dev.el_nico.jardineria.util.AbstractBuilder;
import dev.el_nico.jardineria.util.Assert;
import dev.el_nico.jardineria.util.hibernate.NicoNumIdGen;

/**
 * Objeto que representa a uno de los clientes
 * según el script de la BBDD «Jardinería». Este
 * script declara los siguientes campos para la
 * tabla «cliente»:
 * 
 * <ul>
 * <li>codigo_cliente INTEGER NOT NULL</li>
 * <li>nombre_cliente VARCHAR(50) NOT NULL</li>
 * <li>nombre_contacto VARCHAR(30) DEFAULT NULL</li>
 * <li>apellido_contacto VARCHAR(30) DEFAULT NULL</li>
 * <li>telefono VARCHAR(15) NOT NULL</li>
 * <li>fax VARCHAR(15) NOT NULL</li>
 * <li>linea_direccion1 VARCHAR(50) NOT NULL</li>
 * <li>linea_direccion2 VARCHAR(50) DEFAULT NULL</li>
 * <li>ciudad VARCHAR(50) NOT NULL</li>
 * <li>region VARCHAR(50) DEFAULT NULL</li>
 * <li>pais VARCHAR(50) DEFAULT NULL</li>
 * <li>codigo_postal VARCHAR(10) DEFAULT NULL</li>
 * <li>codigo_empleado_rep_ventas INTEGER DEFAULT NULL</li>
 * <li>limite_credito NUMERIC(15,2) DEFAULT NULL</li>
 * </ul>
 */
public @Entity class Cliente {

    @GeneratedValue(generator = "cliente_id_gen")
    @GenericGenerator(name = "cliente_id_gen", strategy = NicoNumIdGen.STRAT)
    private @Id Integer codigo_cliente; 
    private @NonNull String nombre_cliente;
    private @NonNull @Embedded Contacto contacto;
    private @NonNull @Embedded Domicilio domicilio;
    private @NonNull Double limite_credito;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_empleado_rep_ventas") // el nombre de la columna de la tabla cliente (integer)
    private Empleado empleado_rep_ventas;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    private @Transient TipoDocumento tipo_documento;
    private @Transient String documento;
    private @Transient String email;
    private @Transient String contrasena;

    /*pkg*/ Cliente() {} // hivernate
    private Cliente(/*int codigo,*/ String nombre, Contacto contacto,  Domicilio domicilio) {
        //this.codigo_cliente = codigo;
        this.nombre_cliente = nombre;
        this.contacto = contacto;
        this.domicilio = domicilio;

        empleado_rep_ventas = null;
        limite_credito = null;
        tipo_documento = null;
        documento = null;
        email = null;
        contrasena = null;
    }

    /** Devuelve el código de cliente. */
    public int getCodigo() {
        return codigo_cliente;
    }

    /** 
     * Devuelve el código del empleado representante de 
     * ventas.
     */
    //@Column(name = "cod_empleado_rep_ventas")
    public Optional<Integer> getCodigoEmpleadoRepVentas() {
        if (empleado_rep_ventas != null) {
            return Optional.of(empleado_rep_ventas.getCodigo());
        } else {
            return Optional.empty();
        }
    }

    /**
     * Devuelve el límite de crédito.
     */
    public Optional<Double> getLimiteCredito() {
        return Optional.ofNullable(limite_credito);
    }

    /** Devuelve el nombre. Nunca es null. */
    public String getNombre() {
        return nombre_cliente;
    }

    /** Devuelve los datos de contacto. Nunca es null. */
    public Contacto getContacto() {
        return contacto;
    }

    /** Devuelve los datos de domicilio. Nunca es null. */
    public Domicilio getDomicilio() {
        return domicilio;
    }

    /** 
     * Retorna el tipo de documento, que puede ser
     * DNI o NIE.
     */
    public Optional<TipoDocumento> getTipoDocumento() {
        return Optional.ofNullable(tipo_documento);
    }

    /** Devuelve el DNI o NIE */
    public Optional<String> getDocumento() {
        return Optional.ofNullable(documento);
    }

    /** Devuelve el email */
    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    /** Devuelve la contraseña */
    public Optional<String> getContrasena() {
        return Optional.ofNullable(contrasena);
    }

    /**
     * Representa los datos de contacto de un cliente.
     * Opcionalmente contendrá nombre y apellido de contacto,
     * pero siempre teléfono y fax.
     */
    public static @Embeddable class Contacto {
        private String nombre_contacto;
        private String apellido_contacto;
        private @NonNull String telefono;
        private @NonNull String fax;

        /*pkg*/ Contacto() {} // hibenrate
        private Contacto(String telefono, String fax) {
            this.telefono = telefono;
            this.fax = fax;
            nombre_contacto = null;
            apellido_contacto = null;
        }

        /** Nombre del contacto del cliente. */
        public Optional<String> nombre() {
            return Optional.ofNullable(nombre_contacto);
        }

        /** Apellido del contacto del cliente. */
        public Optional<String> apellido() {
            return Optional.ofNullable(apellido_contacto);
        }

        /** Teléfono del cliente. Nunca es null. */
        public String telefono() {
            return telefono;
        }

        /** Fax del cliente. Nunca es null. */
        public String fax() {
            return fax;
        }

        @Override
        public String toString() {
            return "Contacto [apellido=" + apellido_contacto + ", fax=" + fax + ", nombre=" + nombre_contacto + ", telefono=" + telefono
                    + "]";
        }
    }

    /**
     * Representa los datos de domicilio de un cliente.
     * Contienen, obligatoriamente, una línea de dirección
     * y la ciudad. Además, pueden tener una segunda línea
     * de dirección, un código postal (cp), una región y
     * un país.
     */
    public static @Embeddable class Domicilio {
        private @NonNull String linea_direccion1;
        private String linea_direccion2;
        private @NonNull String ciudad;
        private String region;
        private String pais;
        private String codigo_postal;

        /*pkg*/ Domicilio() {} // hibernatoe
        private Domicilio(String direccion1, String ciudad) {
            this.linea_direccion1 = direccion1;
            this.ciudad = ciudad;
            
            linea_direccion2 = null;
            region = null;
            pais = null;
            codigo_postal = null;
        }

        /** 
         * Devuelve la primera línea de dirección del domicilio 
         * del cliente. Nunca es null.
         */
        public String lineaDireccion1() {
            return linea_direccion1;
        }

        /** Devuelve la ciudad del cliente. Nunca es null. */
        public String ciudad() {
            return ciudad;
        }

        /** 
         * Devuelve la segunda línea de dirección del domicilio
         * del cliente.
         */
        public Optional<String> lineaDireccion2() {
            return Optional.ofNullable(linea_direccion2);
        }

        /** Devuelve la región del domicilio del cliente. */
        public Optional<String> region() {
            return Optional.ofNullable(region);
        }

        /** Devuelve el país del domicilio del cliente. */
        public Optional<String> pais() {
            return Optional.ofNullable(pais);
        }

        /** Devuelve el código postal del domicilio del cliente */
        public Optional<String> codigoPostal() {
            return Optional.ofNullable(codigo_postal);
        }

        @Override
        public String toString() {
            return "Domicilio [ciudad=" + ciudad + ", cp=" + codigo_postal + ", direccion1=" + linea_direccion1 + ", direccion2="
                    + linea_direccion2 + ", pais=" + pais + ", region=" + region + "]";
        }
    }

    /** Clase para buildear instancias válidas de Cliente. */
    public static class Builder extends AbstractBuilder<Cliente> {
      
        /**
         * Toma los datos obligatorios para formar un cliente válido.
         * Crea un Builder con esos datos. Notar que los parámetros aquí
         * aportados pueden ser null, y será la función «build» quien
         * lance la excepción.
         */
        public Builder(/*int codigo,*/ String nombre, String telefono, 
                       String fax, String direccion1, String ciudad) {
            este = new Cliente(//codigo, 
                               nombre, 
                               new Contacto(telefono, fax), 
                               new Domicilio(direccion1, ciudad));
        }

        public Builder(Cliente otro) {
            este = otro;
        }

        public Builder conCodigo(Integer codigo) {
            este.codigo_cliente = codigo;
            return this;
        }

        public Builder conNombre(String nombre) {
            este.nombre_cliente = nombre;
            return this;
        }

        public Builder conTelefono(String telefono) {
            este.contacto.telefono = telefono;
            return this;
        }

        public Builder conFax(String fax) {
            este.contacto.fax = fax;
            return this;
        }

        public Builder conLineaDireccion1(String dir1) {
            este.domicilio.linea_direccion1 = dir1;
            return this;
        }

        public Builder conCiudad(String ciudad) {
            este.domicilio.ciudad = ciudad;
            return this;
        }

        /** Para aportar un nombre de contacto al builder. */
        public Builder conNombreDeContacto(String nombre) {
            este.contacto.nombre_contacto = nombre;
            return this;
        }

        /** Para aportar un apellido de contacto al builder. */
        public Builder conApellidoDeContacto(String apellido) {
            este.contacto.apellido_contacto = apellido;
            return this;
        }

        /** Para aportar un límite de crédito al builder. */
        public Builder conLimiteCredito(Double limite_credito) {
            este.limite_credito = limite_credito;
            return this;
        }

        /** Para aportar un empleado rep. ventas al builder. */
        public Builder conEmpleadoRepVentas(Empleado cod_empl_rep_ventas) {
            este.empleado_rep_ventas = cod_empl_rep_ventas;
            return this;
        }

        /** 
         * Para aportar una segunda línea de dirección al domicilio
         * del builder.
         */
        public Builder conLineaDireccion2(String direccion2) {
            este.domicilio.linea_direccion2 = direccion2;
            return this;
        }

        /** Para aportar una región al domicilio del builder. */
        public Builder conRegion(String region) {
            este.domicilio.region = region;
            return this;
        }

        /** Para aportar un país al domicilio del builder. */
        public Builder conPais(String pais) {
            este.domicilio.pais = pais;
            return this;
        }

        /** Para aportar un código postal al domicilio del builder. */
        public Builder conCodigoPostal(String cp) {
            este.domicilio.codigo_postal = cp;
            return this;
        }

        /** Para aportar DNI o NIE al builder */
        public Builder conDocumento(TipoDocumento tipo, String documento) {
            este.tipo_documento = tipo;
            este.documento = documento;
            return this;
        }

        /** Para aportar email y contraseña al builder */
        public Builder conEmail(String email, String contrasena) {
            este.email = email;
            este.contrasena = contrasena;
            return this;
        }

        /**
         * Devuelve una instancia válida de cliente o lanza una excepción si hay
         * algún error.
         * @return Un cliente válido
         * @throws ExcepcionDatoNoValido Si algún campo obligatorio es igual a null.
         * @throws ExcepcionFormatoIncorrecto Si email o documento están mal escritos.
         */
        @Override
        public Cliente buildOrThrow() throws ExcepcionDatoNoValido,
                                             ExcepcionFormatoIncorrecto {
            /* QUE LOS DATOS NOT NULL NO SEAN NULL */
           // Assert.notNull("codigo_cliente", este.codigo_cliente);
            Assert.notNull("nombre_cliente", este.nombre_cliente);
            Assert.notNull("telefono", este.contacto.telefono);
            Assert.notNull("fax", este.contacto.fax);
            Assert.notNull("linea_direccion1", este.domicilio.linea_direccion1);
            Assert.notNull("ciudad", este.domicilio.ciudad);

            /* QUE LAS LONGITUDES DE LOS VARCHAR SEAN VÁLIDAS */
            final Cliente.Contacto cto = este.contacto;
            final Cliente.Domicilio dom = este.domicilio;
            Assert.varcharLength("nombre_cliente", este.getNombre(), 50);
            Assert.varcharLength("nombre_contacto", cto.nombre().orElse(null), 30);
            Assert.varcharLength("apellido_contacto", cto.apellido().orElse(null), 30);
            Assert.varcharLength("telefono", cto.telefono(), 15);
            Assert.varcharLength("fax", cto.fax(), 15);
            Assert.varcharLength("linea_direccion1", dom.lineaDireccion1(), 50);
            Assert.varcharLength("linea_direccion2", dom.lineaDireccion2().orElse(null), 50);
            Assert.varcharLength("ciudad", dom.ciudad(), 50);
            Assert.varcharLength("region", dom.region().orElse(null), 50);
            Assert.varcharLength("pais", dom.pais().orElse(null), 50);
            Assert.varcharLength("codigo_postal", dom.codigoPostal().orElse(null), 10);

            /* COMPROBAR E-MAIL Y CONTRASEÑA */
            if (este.email != null) {
                if (este.contrasena == null) {
                    throw new ExcepcionDatoNoValido("Hay e-mail, pero la contraseña es null");
                }
                // Comprobar que el email es en forma tal @ tal . tal
                if (!este.email.matches("\\w+@\\w+[.]\\w+")) {
                    throw new ExcepcionFormatoIncorrecto("El email debería matchear \"\\w+@\\w+[.]\\w+\", " +
                                                         "pero es " + este.email);
                }
            } else if (este.contrasena != null) {
                throw new ExcepcionDatoNoValido("Hay contraseña, pero el e-mail es null");
            }

            /* COMPROBAR DOCUMENTO IDENTIFICATIVO */
            if (este.tipo_documento != null) {
                if (este.documento == null) {
                    throw new ExcepcionDatoNoValido("Debería haber documento!!!");
                } else {
                    switch (este.tipo_documento) {
                    case DNI: // Comprobar que el documento es 8 dígitos + letra
                        if (!este.documento.matches("\\d{8}[a-zA-Z]")) {
                            throw new ExcepcionFormatoIncorrecto("El formato DNI debería cumplir \"[0-9]{8}[a-zA-Z]\", " +
                                                                 "pero es " + este.documento);
                        }
                        break;
                    case NIE: // Comprobar que el documento es letra + 7 dígitos + letra
                        if (!este.documento.matches("[a-zA-Z]\\d{7}[a-zA-Z]")) {
                            throw new ExcepcionFormatoIncorrecto("El formato NIE debería cumplir \"[a-zA-Z][0-9]{7}[a-zA-Z]\", " +
                                                                 "pero es " + este.documento);
                        }
                        break;
                    }
                }
            }
            
            return este;
        }
    }

    /** Utilidad para las tablas de la interfaz de usuario */
    public Object[] objArray() {
        return new String[] {
            codigo_cliente.toString(),
            nombre_cliente,
            getContacto().nombre().orElse(""),
            getContacto().apellido().orElse(""),
            contacto.telefono,
            contacto.fax,
            domicilio.linea_direccion1,
            getDomicilio().lineaDireccion2().orElse(""),
            domicilio.ciudad,
            getDomicilio().region().orElse(""),
            getDomicilio().pais().orElse(""),
            getDomicilio().codigoPostal().orElse(""),
            getCodigoEmpleadoRepVentas().isPresent() ? getCodigoEmpleadoRepVentas().get().toString() : "",
            getLimiteCredito().isPresent() ? limite_credito.toString() : "",
            getTipoDocumento().isPresent() ? tipo_documento.toString() : "",
            getDocumento().orElse(""),
            getEmail().orElse(""),
            getContrasena().orElse("")
        };
    }

    /** 
     * Un resumen del cliente para la práctica 1 de AD.
     * @return La siguiente información, separada con '|': codigo_cliente, 
     *         nombre_cliente, nombre_contacto y apellido_contacto.
     */
    public String infoResumen() {
        return "ID: " + codigo_cliente + " | Nombre: " + nombre_cliente + " | Contacto: " + getContacto().nombre().orElse("-----") + " " + getContacto().apellido().orElse("-----");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(1024).append("\n============[ Cliente ").append(codigo_cliente)
                .append(" ]============\nNombre: ").append(nombre_cliente).append("\nContacto {\n  Nom: ")
                .append(getContacto().nombre().orElse("------")).append("\n  Ape: ")
                .append(getContacto().apellido().orElse("------")).append("\n  Tlf: ").append(contacto.telefono)
                .append("\n  Fax: ").append(contacto.fax).append("\n}\nDomicilio {\n  Ln1: ")
                .append(domicilio.linea_direccion1).append("\n  Ln2: ")
                .append(getDomicilio().lineaDireccion2().orElse("------")).append("\n  Ciu: ").append(domicilio.ciudad)
                .append("\n  Reg: ").append(getDomicilio().region().orElse("------")).append("\n  Pai: ")
                .append(getDomicilio().pais().orElse("------")).append("\n   CP: ")
                .append(getDomicilio().codigoPostal().orElse("------")).append("\n}\nRpVtas: ")
                .append((getCodigoEmpleadoRepVentas().isPresent() ? empleado_rep_ventas : "------"))
                .append("\nLimCrd: ").append((getLimiteCredito().isPresent() ? limite_credito : "------"));
        sb.trimToSize();
        return sb.toString();
    }
}
