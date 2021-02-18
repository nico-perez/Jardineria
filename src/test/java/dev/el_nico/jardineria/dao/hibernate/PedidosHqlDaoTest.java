package dev.el_nico.jardineria.dao.hibernate;

import org.junit.jupiter.api.Test;

import dev.el_nico.jardineria.modelo.Pedido;

public class PedidosHqlDaoTest extends HibernateTest {

    @Test 
    public void getearPedidoTest() {
        Jardineria.pedidos().uno(10).ifPresent(e -> System.out.println(e));
    }

    @Test
    public void eliminarPedidoTest() {
        Pedido aElim = Jardineria.pedidos().uno(10).get();
        Jardineria.pedidos().eliminar(aElim);
    }
}
