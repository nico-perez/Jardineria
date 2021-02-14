package dev.el_nico.jardineria.dao.hibernate;

import java.io.IOException;

import org.hibernate.Session;

import dev.el_nico.jardineria.util.hibernate.SesionHibernate;

public class ConexionJardineria {

    private static ClientesDao clientesDao;
    private static PedidosDao pedidosDao;
    private static ProductosDao productosDao;
    private static EmpleadosDao empleadosDao;

    private ConexionJardineria() {}

    public static boolean login(String user, String pass) {

        if (SesionHibernate.login(user, pass)) {

            clientesDao = new ClientesDao();
            pedidosDao = new PedidosDao();
            productosDao = new ProductosDao();
            empleadosDao = new EmpleadosDao();

            return true;

        } else {

            System.out.println("Error al loginear.");
            return false;

        }
    }

    protected static Session getSession() {
        return SesionHibernate.get();
    }

    public static ClientesDao clientes() {
        return clientesDao;
    }

    public static PedidosDao pedidos() {
        return pedidosDao;
    }

    public static ProductosDao productos() {
        return productosDao;
    }

    public static EmpleadosDao empleados() {
        return empleadosDao;
    }

    public static void close() throws IOException {
		SesionHibernate.close();
	}
}
