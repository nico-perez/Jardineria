package dev.el_nico.jardineria.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.checkerframework.checker.nullness.qual.NonNull;

@Entity
public class Oficina {
    private @NonNull @Id String codigo_oficina;
    private @NonNull String telefono;
    private @NonNull @Embedded Direccion direccion;

    /*pkg*/ Oficina() {} // hibernate

    public String getCodigo() {
        return codigo_oficina;
    }

    public String getTelefono() {
        return telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
