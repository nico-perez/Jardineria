package dev.el_nico.jardineria.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dev.el_nico.jardineria.dao.DaoHolder;
import dev.el_nico.jardineria.dao.IDao;
import dev.el_nico.jardineria.modelo.DetallePedido;

public class ConexionJardineria extends DaoHolder implements AutoCloseable {

    private Connection conn;

    protected Connection getConnection() {
        return conn;
    }

    public boolean login(String user, String pass) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jardineria?serverTimezone=UTC", user, pass);
            
            pedidosDao = new PedidosSqlDao(this);
            clientesDao = new ClientesSqlDao(this);
            productosDao = new ProductosSqlDao(this);
            empleadosDao = new EmpleadosSqlDao(this);

            return true;
        } catch (SQLException e) {
            System.out.println("Error al loginear.");
            return false;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public PedidosSqlDao pedidos() {
        return (PedidosSqlDao) pedidosDao;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ClientesSqlDao clientes() {
        return (ClientesSqlDao) clientesDao;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ProductosSqlDao productos() {
        return (ProductosSqlDao) productosDao;
    }

    @Override
    @SuppressWarnings("unchecked")
    public EmpleadosSqlDao empleados() {
        return (EmpleadosSqlDao) empleadosDao;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IDao<DetallePedido> detalles() {
        return detallesPedidoDao;
    }

    @Override
    public void close() throws SQLException {
        if (conn != null) {
            if (!conn.getAutoCommit()) conn.commit();
            conn.close();
        }
    }
}
