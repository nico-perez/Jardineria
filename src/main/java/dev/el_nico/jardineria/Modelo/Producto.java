package dev.el_nico.jardineria.modelo;

import java.util.Optional;

import dev.el_nico.jardineria.excepciones.ExcepcionDatoNoValido;

public class Producto {
    private String codigo_producto;
    private String nombre;
    private String gama;
    private String dimensiones;
    private String proveedor;
    private String descripcion;
    private Integer cantidad_en_stock;
    private Double precio_venta;
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

    public static class Builder {
        private Producto producto;

        public Builder(Producto producto) {
            this.producto = producto;
        }

        public Builder(String codigo_producto, String nombre, String gama, int cantidad_en_stock, double precio_venta) {
            producto = new Producto(codigo_producto,
                                    nombre,
                                    gama,
                                    cantidad_en_stock,
                                    precio_venta);
        }

        public Builder con_dimensiones(String dimensiones) {
            producto.dimensiones = dimensiones;
            return this;
        }

        public Builder con_proveedor(String proveedor) {
            producto.proveedor = proveedor;
            return this;
        }

        public Builder con_descripcion(String descripcion) {
            producto.descripcion = descripcion;
            return this;
        }

        public Builder con_precio_proveedor(double precio_proveedor) {
            producto.precio_proveedor = precio_proveedor;
            return this;
        }

        public Producto build() throws ExcepcionDatoNoValido {
            boolean datos_necesarios_asignados = true;
            datos_necesarios_asignados &= (producto.codigo_producto != null) 
                                       && (producto.nombre != null)
                                       && (producto.gama != null);
            if (!datos_necesarios_asignados) {
                throw new ExcepcionDatoNoValido("Ninguno de los siguientes campos puede ser null: codigo_producto, nombre, gama.");
            }

            return datos_necesarios_asignados ? producto : null;
        }
    }
}
