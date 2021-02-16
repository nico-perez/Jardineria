package dev.el_nico.jardineria.dao.hibernate;

import org.junit.jupiter.api.Test;

public class PedidosHqlDaoTest extends HibernateTest {

    @Test 
    public void getearPedidoTest() {
        Jardineria.pedidos().uno(10).ifPresent(e -> System.out.println(e));
    }
}
