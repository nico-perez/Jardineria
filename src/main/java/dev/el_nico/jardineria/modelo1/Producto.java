package dev.el_nico.jardineria.modelo1;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.el_nico.jardineria.excepciones1.Assert;
import dev.el_nico.jardineria.excepciones1.ExcepcionDatoNoValido;
import dev.el_nico.jardineria.util.AbstractBuilder;

public @Entity class Producto {
    private @NonNull @Id String codigo_producto;
    private @NonNull String nombre;
    private @NonNull String gama;
    private String dimensiones;
    private String proveedor;
    private String descripcion;
    private @NonNull Integer cantidad_en_stock;
    private @NonNull Double precio_venta;
    private Double precio_proveedor;

    private Producto(String codigo_producto, String nombre, String gama, int cantidad_en_stock, double precio_venta) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.gama = gama;
        this.cantidad_en_stock = cantidad_en_stock;
        this.precio_venta = precio_venta;
    }

    public String getCodigoProducto() {
        return codigo_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGama() {
        return gama;
    }

    public Optional<String> getDimensiones() {
        return Optional.ofNullable(dimensiones);
    }

    public Optional<String> getProveedor() {
        return Optional.ofNullable(proveedor);
    }

    public Optional<String> getDescripcion() {
        return Optional.ofNullable(descripcion);
    }

    public int getCantidadEnStock() {
        return cantidad_en_stock;
    }

    public double getPrecioVenta() {
        return precio_venta;
    }

    public Optional<Double> getPrecioProveedor() {
        return Optional.ofNullable(precio_proveedor);
    }

    public static class Builder extends AbstractBuilder<Producto> {

        public Builder(Producto producto) {
            este = producto;
        }

        public Builder(String codigo_producto, String nombre, String gama, Integer cantidad_en_stock, Double precio_venta) {
            este = new Producto(codigo_producto,
                                nombre,
                                gama,
                                cantidad_en_stock,
                                precio_venta);
        }

        public Builder conCodigo(String codigo) {
            este.codigo_producto = codigo;
            return this;
        }

        public Builder conNombre(String nombre) {
            este.nombre = nombre;
            return this;
        }

        public Builder conGama(String gama) {
            este.gama = gama;
            return this;
        }

        public Builder conCantidadEnStock(Integer cantidad) {
            este.cantidad_en_stock = cantidad;
            return this;
        }

        public Builder conPrecioVenta(Double precio) {
            este.precio_venta = precio;
            return this;
        }

        public Builder conDimensiones(String dimensiones) {
            este.dimensiones = dimensiones;
            return this;
        }

        public Builder conProveedor(String proveedor) {
            este.proveedor = proveedor;
            return this;
        }

        public Builder conDescripcion(String descripcion) {
            este.descripcion = descripcion;
            return this;
        }

        public Builder conPrecioProveedor(double precio_proveedor) {
            este.precio_proveedor = precio_proveedor;
            return this;
        }

        public Producto buildOrThrow() throws ExcepcionDatoNoValido {

            Assert.notNull("codigo_producto", este.codigo_producto);
            Assert.notNull("nombre", este.nombre);
            Assert.notNull("gama", este.gama);                         

            // TODO comprobar varchars

            return este;
        }
    }
}
