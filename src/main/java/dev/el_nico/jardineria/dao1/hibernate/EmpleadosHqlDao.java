package dev.el_nico.jardineria.dao1.hibernate;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;

import dev.el_nico.jardineria.dao1.IDao;
import dev.el_nico.jardineria.modelo1.Empleado;

public class EmpleadosHqlDao implements IDao<Empleado> {

    private Session s;

	public EmpleadosHqlDao(Session s) {
        this.s = s;
	}

	@Override
    public Optional<Empleado> uno(Object id) {
        return Optional.ofNullable(s.get(Empleado.class, (Integer) id));
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
