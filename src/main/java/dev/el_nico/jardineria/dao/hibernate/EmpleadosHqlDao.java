package dev.el_nico.jardineria.dao.hibernate;

import java.util.List;
import java.util.Optional;

import dev.el_nico.jardineria.dao.IDao;
import dev.el_nico.jardineria.modelo.Empleado;

public class EmpleadosHqlDao implements IDao<Empleado> {

    private ConexionJardineriaHql daos;

	public EmpleadosHqlDao(ConexionJardineriaHql daos) {
        this.daos = daos;
	}

	@Override
    public Optional<Empleado> uno(Object id) {
        return Optional.ofNullable(daos.getSession().get(Empleado.class, (Integer) id));
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
