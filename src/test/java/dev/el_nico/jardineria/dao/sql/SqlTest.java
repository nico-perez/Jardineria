package dev.el_nico.jardineria.dao.sql;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import dev.el_nico.jardineria.dao.sql.ClientesSqlDao;
import dev.el_nico.jardineria.dao.sql.ConexionJardineria;
import dev.el_nico.jardineria.dao.sql.PedidosSqlDao;
import dev.el_nico.jardineria.dao.sql.ProductosSqlDao;

public class SqlTest {
    private static ConexionJardineria jrd;
    protected static ClientesSqlDao clientes;
    protected static PedidosSqlDao pedidos;
    protected static ProductosSqlDao productos;

    @BeforeAll
    public static final void establecerConexion() {
        jrd = new ConexionJardineria();
        if (jrd.login("admin", "admin")) {
            clientes = jrd.clientes();
            pedidos = jrd.pedidos();
            productos = jrd.productos();
        }
    }

    @AfterAll
    public static final void cerrarConexion() {
        try {
            jrd.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
