package dev.el_nico.jardineria.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import dev.el_nico.jardineria.dao.IDao;
import dev.el_nico.jardineria.modelo.Empleado;

public class EmpleadosSqlDao implements IDao<Empleado> {

    private Connection sql;
    private ConexionJardineria daos;

    public EmpleadosSqlDao(ConexionJardineria daos) {
        this.daos = daos;
        sql = daos.getConnection();
    }

    @Override
    public Optional<Empleado> uno(Object id) {
        try (PreparedStatement stat = sql.prepareStatement("select * from empleado where codigo_empleado=?;")) {
            stat.setString(1, (String)id);
            ResultSet res = stat.executeQuery();
            if (res.next()) {
                return sacarEmpleadoDeResultSet(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
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

    
    private Optional<Empleado> sacarEmpleadoDeResultSet(ResultSet res) {
        // TODO uffffffffffffff
    }

}
