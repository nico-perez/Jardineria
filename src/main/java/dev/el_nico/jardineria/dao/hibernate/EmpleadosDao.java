package dev.el_nico.jardineria.dao.hibernate;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;

import dev.el_nico.jardineria.dao.IDao;
import dev.el_nico.jardineria.excepciones.ExcepcionCodigoYaExistente;
import dev.el_nico.jardineria.modelo.Empleado;
import dev.el_nico.jardineria.util.hibernate.SesionHibernate;

public class EmpleadosDao implements IDao<Empleado> {

	@Override
    public Optional<Empleado> uno(Object id) {
        return Optional.ofNullable(SesionHibernate.get().get(Empleado.class, (Integer) id));
    }

    @Override
    public List<Empleado> todos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void guardar(Empleado t) throws ExcepcionCodigoYaExistente {
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
    public void modificar(Empleado t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void eliminar(Empleado t) {
        // TODO Auto-generated method stub

    }

}
