package dev.el_nico.jardineria.dao.sql;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class SqlTest {
    private static ConexionJardineriaSql jrd;
    protected static ClientesSqlDao clientes;
    protected static PedidosSqlDao pedidos;
    protected static ProductosSqlDao productos;

    @BeforeAll
    public static final void establecerConexion() {
        jrd = new ConexionJardineriaSql();
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
