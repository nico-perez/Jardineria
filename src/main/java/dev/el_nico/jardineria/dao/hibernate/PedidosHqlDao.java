package dev.el_nico.jardineria.dao.hibernate;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;

import dev.el_nico.jardineria.dao.IDao;
import dev.el_nico.jardineria.excepciones.ExcepcionCodigoYaExistente;
import dev.el_nico.jardineria.modelo.Pedido;

public class PedidosHqlDao implements IDao<Pedido> {

    ConexionJardineriaHql daos;

    public PedidosHqlDao(ConexionJardineriaHql daos) {
        this.daos = daos;
    }

    @Override
    public Optional<Pedido> uno(Object id) {
        return Optional.ofNullable(daos.getSession().get(Pedido.class, (Integer) id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Pedido> todos() {
        return daos.getSession().createQuery("from Pedido").list();
    }

    @Override
    public void guardar(Pedido t) throws Exception {
        try {
            daos.getSession().beginTransaction();
            daos.getSession().save(t);
            daos.getSession().getTransaction().commit();
        } catch (HibernateException e) {
            throw new ExcepcionCodigoYaExistente("u");
        }
    }

    @Override
    public void modificar(Pedido t) {
        daos.getSession().beginTransaction();
        daos.getSession().update(t);
        daos.getSession().getTransaction().commit();
    }

    @Override
    public void eliminar(Pedido t) {
        daos.getSession().delete(t);
    }
    
}
