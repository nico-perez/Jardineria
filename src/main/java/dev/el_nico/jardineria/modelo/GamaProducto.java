package dev.el_nico.jardineria.modelo;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.checkerframework.checker.nullness.qual.NonNull;

@Entity
@Table(name = "gama_producto")
public class GamaProducto {

    private @NonNull @Id String gama;
    private String descripcion_texto;
    private String descripcion_html;
    private String imagen;

    /*pkg*/ GamaProducto() {} // hibneratoe

    public String getNombre() {
        return gama;
    }

    public Optional<String> getDescripcionTexto() {
        return Optional.ofNullable(descripcion_texto);
    }

    public Optional<String> getDescripcionHtml() {
        return Optional.ofNullable(descripcion_html);
    }

    public Optional<String> getImagen() {
        return Optional.ofNullable(imagen);
    }
}
