package dev.el_nico.jardineria.dao.hibernate;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class HibernateTest {

    @BeforeAll
    public static final void establecerConexion() {
        if (!ConexionJardineria.login("admin", "admin")) {
            fail("uh oh");
        }
    }

    @AfterAll
    public static final void cerrarConexion() {
        try {
            ConexionJardineria.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
