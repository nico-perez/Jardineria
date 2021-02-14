package dev.el_nico.jardineria.dao.hibernate;

import org.junit.jupiter.api.Test;

public class EmpleadoHqlDaoTest extends HibernateTest {
    
    @Test
    public void getearEmpleado() {

        jard.empleados().uno(8).ifPresent(
            e -> System.out.println(e)
        );

    }

}
