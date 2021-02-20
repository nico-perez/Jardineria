package dev.el_nico.jardineria.modelo;

import java.util.Optional;

import javax.persistence.Embeddable;

/**
 * Representa una dirección (en Cliente y Oficina).
 * Contienen, obligatoriamente, una línea de dirección
 * y la ciudad. Además, pueden tener una segunda línea
 * de dirección, un código postal (cp), una región y
 * un país.
 */
public @Embeddable class Direccion {

    String linea_direccion1;
    String linea_direccion2;
    String ciudad;
    String region;
    String pais;
    String codigo_postal;

    Direccion() {} // hibernatoe

    /** para Cliente */
    Direccion(String direccion1, String ciudad) { 
        this.linea_direccion1 = direccion1;
        this.ciudad = ciudad;
        
        linea_direccion2 = null;
        region = null;
        pais = null;
        codigo_postal = null;
    }

    /** para Oficina */
    Direccion(String direccion1, String ciudad, String pais, String codigo_postal) {
        this.linea_direccion1 = direccion1;
        this.ciudad = ciudad;
        this.pais = pais;
        this.codigo_postal = codigo_postal;
        
        linea_direccion2 = null;
        region = null;
    }

    /** 
     * Devuelve la primera línea de dirección del domicilio 
     * del cliente. Nunca es null.
     */
    public Optional<String> getLineaDireccion1() {
        return Optional.ofNullable(linea_direccion1);
    }

    /** Devuelve la ciudad del cliente. Nunca es null. */
    public Optional<String> getCiudad() {
        return Optional.ofNullable(ciudad);
    }

    /** 
     * Devuelve la segunda línea de dirección del domicilio
     * del cliente.
     */
    public Optional<String> getLineaDireccion2() {
        return Optional.ofNullable(linea_direccion2);
    }

    /** Devuelve la región del domicilio del cliente. */
    public Optional<String> getRegion() {
        return Optional.ofNullable(region);
    }

    /** Devuelve el país del domicilio del cliente. */
    public Optional<String> getPais() {
        return Optional.ofNullable(pais);
    }

    /** Devuelve el código postal del domicilio del cliente */
    public Optional<String> getCodigoPostal() {
        return Optional.ofNullable(codigo_postal);
    }

    @Override
    public String toString() {
        return "Domicilio [ciudad=" + ciudad + ", cp=" + codigo_postal + ", direccion1=" + linea_direccion1 + ", direccion2="
                + linea_direccion2 + ", pais=" + pais + ", region=" + region + "]";
    }
}
