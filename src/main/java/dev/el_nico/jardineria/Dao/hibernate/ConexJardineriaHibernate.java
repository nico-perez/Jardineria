package dev.el_nico.jardineria.dao.hibernate;

import org.hibernate.Session;

public class ConexJardineriaHibernate {

    private ClientesHqlDao clientesDao;
    private PedidosHqlDao pedidosDao;
    private ProductosHqlDao productosDao;
    private EmpleadosHqlDao empleadosDao;

	private Session s;

    public ConexJardineriaHibernate(Session s) {
        clientesDao = new ClientesHqlDao(s);
        pedidosDao = new PedidosHqlDao(s);
		productosDao = new ProductosHqlDao(s);
		empleadosDao = new EmpleadosHqlDao(s);

		this.s = s;
    }

	public ClientesHqlDao clientes() {
		return clientesDao;
	}

	public PedidosHqlDao pedidos() {
		return pedidosDao;
	}

	public ProductosHqlDao productos() {
		return productosDao;
	}

    public EmpleadosHqlDao empleados() {
        return empleadosDao;
    }

	public void close() {
		s.close();
	}
}
