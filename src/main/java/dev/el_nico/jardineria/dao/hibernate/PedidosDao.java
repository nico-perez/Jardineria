package dev.el_nico.jardineria.dao.hibernate;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;

import dev.el_nico.jardineria.dao.IDao;
import dev.el_nico.jardineria.excepciones.ExcepcionCodigoYaExistente;
import dev.el_nico.jardineria.modelo.Pedido;
import dev.el_nico.jardineria.util.hibernate.SesionHibernate;

public class PedidosDao implements IDao<Pedido> {

    @Override
    public Optional<Pedido> uno(Object id) {
        return Optional.ofNullable(SesionHibernate.get().get(Pedido.class, (Integer) id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Pedido> todos() {
        return SesionHibernate.get().createQuery("from Pedido").list();
    }

    @Override
    public void guardar(Pedido t) throws Exception {
        try {
            SesionHibernate.get().beginTransaction();
            SesionHibernate.get().save(t);
            SesionHibernate.get().getTransaction().commit();
        } catch (HibernateException e) {
            throw new ExcepcionCodigoYaExistente("u");
        }
    }

    @Override
    public void modificar(Pedido t) {
        SesionHibernate.get().beginTransaction();
        SesionHibernate.get().update(t);
        SesionHibernate.get().getTransaction().commit();
    }

    @Override
    public void eliminar(Pedido t) {
        SesionHibernate.get().beginTransaction();
        SesionHibernate.get().delete(t);
        SesionHibernate.get().getTransaction().commit();
    }
    
}
