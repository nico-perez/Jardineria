package dev.el_nico.jardineria.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import dev.el_nico.jardineria.dao.DaoHolder;

public class ConexionJardineriaHql extends DaoHolder implements AutoCloseable {

	private Session session;

    public boolean login(String user, String pass) {
        // configures settings from hibernate.cfg.xml
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .applySetting("hibernate.connection.username", user)
            .applySetting("hibernate.connection.password", pass)
            .configure()
            .build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            session = sessionFactory.openSession();
            
            clientesDao = new ClientesHqlDao(this);
            pedidosDao = new PedidosHqlDao(this);
            productosDao = new ProductosHqlDao(this);
            empleadosDao = new EmpleadosHqlDao(this);

            return true;
        } catch(Exception e) {
            
            System.out.println("Error al loginear.");
            StandardServiceRegistryBuilder.destroy(registry);

            e.printStackTrace();

            return false;
        }
    }

    protected Session getSession() {
        return session;
    }

    @Override
    @SuppressWarnings("unchecked")
	public ClientesHqlDao clientes() {
		return (ClientesHqlDao) clientesDao;
	}

    @Override
    @SuppressWarnings("unchecked")
    public PedidosHqlDao pedidos() {
		return (PedidosHqlDao) pedidosDao;
	}

    @Override
    @SuppressWarnings("unchecked")
    public ProductosHqlDao productos() {
		return (ProductosHqlDao) productosDao;
	}

    @Override
    @SuppressWarnings("unchecked")
    public EmpleadosHqlDao empleados() {
        return (EmpleadosHqlDao) empleadosDao;
    }

    @Override
	public void close() {
		session.close();
	}
}
