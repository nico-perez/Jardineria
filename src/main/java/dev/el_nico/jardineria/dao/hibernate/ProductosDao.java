package dev.el_nico.jardineria.dao.hibernate;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;

import dev.el_nico.jardineria.dao.IDao;
import dev.el_nico.jardineria.excepciones.ExcepcionCodigoYaExistente;
import dev.el_nico.jardineria.modelo.Producto;
import dev.el_nico.jardineria.util.hibernate.SesionHibernate;

public class ProductosDao implements IDao<Producto> {

    @Override
    public Optional<Producto> uno(Object id) {
        return Optional.ofNullable(SesionHibernate.get().get(Producto.class, (String) id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Producto> todos() {
        return SesionHibernate.get().createQuery("from Producto").list();
    }

    @Override
    public void guardar(Producto t) throws Exception {
        try {
            SesionHibernate.get().beginTransaction();
            SesionHibernate.get().save(t);
            SesionHibernate.get().getTransaction().commit();
        } catch (HibernateException e) {
            throw new ExcepcionCodigoYaExistente("ñ");
        }
    }

    @Override
    public void modificar(Producto t) {
        SesionHibernate.get().beginTransaction();
        SesionHibernate.get().evict(t);
        SesionHibernate.get().update(t);
        SesionHibernate.get().getTransaction().commit();
    }

    @Override
    public void eliminar(Producto t) {
        SesionHibernate.get().delete(t);
    }
    
}
