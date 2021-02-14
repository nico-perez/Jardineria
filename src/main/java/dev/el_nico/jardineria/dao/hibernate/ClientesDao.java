package dev.el_nico.jardineria.dao.hibernate;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;

import dev.el_nico.jardineria.dao.IDao;
import dev.el_nico.jardineria.excepciones.ExcepcionCodigoYaExistente;
import dev.el_nico.jardineria.modelo.Cliente;
import dev.el_nico.jardineria.util.hibernate.SesionHibernate;

public class ClientesDao implements IDao<Cliente> {

    @Override
    public Optional<Cliente> uno(Object id) {
        return Optional.ofNullable(SesionHibernate.get().get(Cliente.class, (Integer) id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Cliente> todos() {
        return SesionHibernate.get().createQuery("from Cliente").list();
    }

    @Override
    public void guardar(Cliente t) throws ExcepcionCodigoYaExistente {
        try {
            SesionHibernate.get().beginTransaction();
            SesionHibernate.get().save(t);
            SesionHibernate.get().getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ExcepcionCodigoYaExistente("a");
        }
    }

    @Override
    public void modificar(Cliente t) {
        SesionHibernate.get().beginTransaction();
        SesionHibernate.get().update(t);
        SesionHibernate.get().getTransaction().commit();
    }

    @Override
    public void eliminar(Cliente t) {
        SesionHibernate.get().delete(t);
    }
    
}
