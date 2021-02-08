package dev.el_nico.jardineria.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import dev.el_nico.jardineria.dao.IDao;
import dev.el_nico.jardineria.excepciones.NicoExcepcion;
import dev.el_nico.jardineria.modelo.Empleado;

public class EmpleadosSqlDao implements IDao<Empleado> {

    private Connection sql;

    @SuppressWarnings("unused")
    private ConexionJardineriaSql daos;

    public EmpleadosSqlDao(ConexionJardineriaSql daos) {
        this.daos = daos;
        sql = daos.getConnection();
    }

    @Override
    public Optional<Empleado> uno(Object id) {
        if (id != null) {
            try (PreparedStatement stat = sql.prepareStatement("select * from empleado where codigo_empleado=?;")) {
                stat.setInt(1, (Integer)id);
                ResultSet res = stat.executeQuery();
                if (res.next()) {
                    return sacarEmpleadoDeResultSet(res);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        if (res != null) {

            Empleado empleado;
            try {
                int codigo_empleado = res.getInt("codigo_empleado");
                String nombre = res.getString("nombre");
                String apellido1 = res.getString("apellido1");
                String apellido2 = res.getString("apellido2");
                String extension = res.getString("extension");
                String email = res.getString("email");
                String codigo_oficina = res.getString("codigo_oficina");
                Integer codigo_jefe = res.getInt("codigo_jefe");
                String puesto = res.getString("puesto");

                empleado = new Empleado.Builder(codigo_empleado, nombre, apellido1, apellido2, extension, email, codigo_oficina, codigo_jefe, puesto).buildOrThrow();
            } catch (NicoExcepcion | SQLException e) {
                e.printStackTrace();
                empleado = null;
            }

            return Optional.ofNullable(empleado);
        } else {
            return Optional.empty();
        }
    }

}
