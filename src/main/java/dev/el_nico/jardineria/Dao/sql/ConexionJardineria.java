package dev.el_nico.jardineria.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJardineria implements AutoCloseable {

    private Connection conn;

    private PedidosSqlDao pedidosDao;
    private ClientesSqlDao clientesDao;
    private ProductosSqlDao productosDao; 
    private EmpleadosSqlDao empleadosDao;

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

    public PedidosSqlDao pedidos() {
        return pedidosDao;
    }

    public ClientesSqlDao clientes() {
        return clientesDao;
    }

    public ProductosSqlDao productos() {
        return productosDao;
    }

    public EmpleadosSqlDao empleados() {
        return empleadosDao;
    }

    @Override
    public void close() throws SQLException {
        if (conn != null) {
            if (!conn.getAutoCommit()) conn.commit();
            conn.close();
        }
    }
}
