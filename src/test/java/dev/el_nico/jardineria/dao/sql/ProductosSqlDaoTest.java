package dev.el_nico.jardineria.dao.sql;

import org.junit.jupiter.api.Test;

import dev.el_nico.jardineria.modelo.Producto;

public class ProductosSqlDaoTest extends SqlTest {

    @Test
    public void testModificar() {
        productos.modificar(new Producto.Builder(productos.uno("11679").get()).conNombre("nombre nuevo")
                .conDimensiones("dimnes nuevas").conProveedor("prov. nuevo").conDescripcion("desc. nueva")
                .conCantidadEnStock(69).conPrecioProveedor(420.69).conPrecioVenta(666.0).build().get());
    }
}
