package dev.el_nico.jardineria.modelo;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.hibernate.annotations.GenericGenerator;

import dev.el_nico.jardineria.excepciones.NicoExcepcion;
import dev.el_nico.jardineria.util.AbstractBuilder;
import dev.el_nico.jardineria.util.hibernate.NicoNumIdGen;

@Entity
public class Empleado {

    @GeneratedValue(generator = "empleado_id_gen")
    @GenericGenerator(name = "empleado_id_gen", strategy = NicoNumIdGen.STRAT)
    private @Id Integer codigo_empleado;

    private @NonNull String nombre;
    private @NonNull String apellido1;
    private String apellido2;
    private @NonNull String extension;
    private @NonNull String email;
    private @NonNull String codigo_oficina;
    
    @OneToMany(mappedBy = "empleado_rep_ventas") // el campo Empleado (objeto) de la clase Cliente
    private @NonNull List<Cliente> lista_clientes;

    @ManyToOne
    @JoinColumn(name = "codigo_empleado", insertable = false, updatable = false)
    private Empleado codigo_jefe;

    private String puesto;

    /*pkg*/ Empleado() {} // hibnerate

    public Integer getCodigo() {
        return codigo_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public Optional<String> getApellido2() {
        return Optional.ofNullable(apellido2);
    }

    public String getExtension() {
        return extension;
    }

    public String getEmail() {
        return email;
    }
    
    public String getCodigoOficina() {
        return codigo_oficina;
    }

    public List<Cliente> getListaClientes() {
        return lista_clientes;
    }

    public Optional<Integer> getCodigoJefe() {
        return Optional.ofNullable(codigo_jefe == null ? null : codigo_jefe.getCodigo());
    }

    public Optional<String> getPuesto() {
        return Optional.ofNullable(puesto);
    }

    public static class Builder extends AbstractBuilder<Empleado> {

        // TODO :I

        public Builder(Integer codigo, String nombre, String apellido1, String apellido2, String extension,
        String email, String codigo_oficina, Empleado codigo_jefe, String puesto) {
            este = new Empleado();
            este.codigo_empleado = codigo;
            este.nombre = nombre;
            este.apellido1 = apellido1;
            este.apellido2 = apellido2;
            este.extension = extension;
            este.email = email;
            este.codigo_oficina = codigo_oficina;
            este.codigo_jefe = codigo_jefe;
            este.puesto = puesto;
        }

        @Override
        public Empleado buildOrThrow() throws NicoExcepcion {
            return este;
        }
        
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(1024).append("\n============[ Empleado ").append(codigo_empleado)
                .append(" ]============\nNombre: ").append(nombre).append("\nApellidos: ").append(apellido1).append(" ")
                .append(getApellido2().orElse("------")).append("\nExtension: ").append(extension).append("\nEmail: ").append(email)
                .append("\nCodOficina: ").append(codigo_oficina).append("\nClientes:\n");
                for (Cliente c : lista_clientes) {
                    sb.append(c.infoResumen()).append("\n");
                }
        sb.trimToSize();
        return sb.toString();
    }
}
