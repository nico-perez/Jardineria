package dev.el_nico.jardineria.dao.hibernate;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class HibernateTest {

    @BeforeAll
    public static final void establecerConexion() {
        if (!Jardineria.login("admin", "admin")) {
            fail("uh oh");
        }
    }

    @AfterAll
    public static final void cerrarConexion() {
        try {
            Jardineria.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
