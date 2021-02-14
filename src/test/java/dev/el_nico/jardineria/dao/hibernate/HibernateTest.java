package dev.el_nico.jardineria.dao.hibernate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class HibernateTest {

    protected static ConexionJardineriaHql jard;

    @BeforeAll
    public static final void establecerConexion() {
        jard = new ConexionJardineriaHql();
        if (!jard.login("admin", "admin")) {
            throw new Error("uh oh");
        }
    }

    @AfterAll
    public static final void cerrarConexion() {
        if (jard != null) {
            jard.close();
        }
    }
    
}
