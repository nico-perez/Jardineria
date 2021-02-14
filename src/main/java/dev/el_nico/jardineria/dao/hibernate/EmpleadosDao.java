package dev.el_nico.jardineria.dao.hibernate;

import java.util.List;
import java.util.Optional;

import dev.el_nico.jardineria.dao.IDao;
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
    public void guardar(Empleado t) throws Exception {
        // TODO Auto-generated method stub

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
