package dev.el_nico.jardineria;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dev.el_nico.jardineria.modelo.Cliente;
import dev.el_nico.jardineria.util.SesionHibernate;

public class SesionHibernateTest {
    
    @BeforeAll
    public static final void init() {
        if (SesionHibernate.login("admin", "admin")) {
            System.out.println("login ok!");
        } else {
            fail("uh oh");
        }
    }

    @Test
    public void getearConexion() {
        System.out.println(SesionHibernate.get().get(Cliente.class, 4));
    }

}
