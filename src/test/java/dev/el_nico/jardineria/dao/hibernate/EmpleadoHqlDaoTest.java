package dev.el_nico.jardineria.dao.hibernate;

import org.junit.jupiter.api.Test;

import dev.el_nico.jardineria.excepciones.ExcepcionCodigoYaExistente;
import dev.el_nico.jardineria.modelo.Empleado;

public class EmpleadoHqlDaoTest extends HibernateTest {

    @Test
    public void testAniadirEmpleadoAutoclave() {
        Empleado nuevo = new Empleado.Builder(null, "Autocvlae", "iajdsd", "aisd", "eef", "asd@sdf.com", "BCN-ES", null,
                "dsa").build().get();
        try {
            Jardineria.empleados().guardar(nuevo);
        } catch (ExcepcionCodigoYaExistente e) {
            e.printStackTrace();
        }
    }

}
