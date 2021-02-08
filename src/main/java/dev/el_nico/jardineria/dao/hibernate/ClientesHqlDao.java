package dev.el_nico.jardineria.dao.hibernate;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;

import dev.el_nico.jardineria.dao.IDao;
import dev.el_nico.jardineria.excepciones.ExcepcionCodigoYaExistente;
import dev.el_nico.jardineria.modelo.Cliente;

public class ClientesHqlDao implements IDao<Cliente> {

    private ConexionJardineriaHql daos;

    public ClientesHqlDao(ConexionJardineriaHql daos) {
        this.daos = daos;
    }

    @Override
    public Optional<Cliente> uno(Object id) {
        return Optional.ofNullable(daos.getSession().get(Cliente.class, (Integer) id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Cliente> todos() {
        return daos.getSession().createQuery("from Cliente").list();
    }

    @Override
    public void guardar(Cliente t) throws ExcepcionCodigoYaExistente {
        try {
            daos.getSession().beginTransaction();
            daos.getSession().save(t);
            daos.getSession().getTransaction().commit();
        } catch (HibernateException e) {
            throw new ExcepcionCodigoYaExistente("a");
        }
    }

    @Override
    public void modificar(Cliente t) {
        daos.getSession().beginTransaction();
        daos.getSession().update(t);
        daos.getSession().getTransaction().commit();
    }

    @Override
    public void eliminar(Cliente t) {
        daos.getSession().delete(t);
    }
    
}
