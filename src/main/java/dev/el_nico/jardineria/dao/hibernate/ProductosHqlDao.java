package dev.el_nico.jardineria.dao.hibernate;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;

import dev.el_nico.jardineria.dao.IDao;
import dev.el_nico.jardineria.excepciones.ExcepcionCodigoYaExistente;
import dev.el_nico.jardineria.modelo.Producto;

public class ProductosHqlDao implements IDao<Producto> {

    ConexionJardineriaHql daos;

    public ProductosHqlDao(ConexionJardineriaHql daos) {
        this.daos = daos;
    }

    @Override
    public Optional<Producto> uno(Object id) {
        return Optional.ofNullable(daos.getSession().get(Producto.class, (String) id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Producto> todos() {
        return daos.getSession().createQuery("from Producto").list();
    }

    @Override
    public void guardar(Producto t) throws Exception {
        try {
            daos.getSession().beginTransaction();
            daos.getSession().save(t);
            daos.getSession().getTransaction().commit();
        } catch (HibernateException e) {
            throw new ExcepcionCodigoYaExistente("ñ");
        }
    }

    @Override
    public void modificar(Producto t) {
        daos.getSession().beginTransaction();
        daos.getSession().evict(t);
        daos.getSession().update(t);
        daos.getSession().getTransaction().commit();
    }

    @Override
    public void eliminar(Producto t) {
        daos.getSession().delete(t);
    }
    
}
