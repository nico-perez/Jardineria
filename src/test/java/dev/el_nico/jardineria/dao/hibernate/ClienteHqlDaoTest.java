package dev.el_nico.jardineria.dao.hibernate;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dev.el_nico.jardineria.excepciones.ExcepcionCodigoYaExistente;
import dev.el_nico.jardineria.excepciones.ExcepcionDatoNoValido;
import dev.el_nico.jardineria.excepciones.ExcepcionFormatoIncorrecto;
import dev.el_nico.jardineria.modelo.Cliente;

public class ClienteHqlDaoTest extends HibernateTest {

    @Test
    public void testAniadirUnCliente() {
        try {
            jard.clientes().guardar(new Cliente.Builder(4830, "TestHib", "dfsjkd", "dghdf", "ads", "dfs")
                    .conEmpleadoRepVentas(10).buildOrThrow()); // hay codigos de empleado del 1 al 31
        } catch (ExcepcionCodigoYaExistente | ExcepcionDatoNoValido | ExcepcionFormatoIncorrecto e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testAniadirUnClientePeroElCodigoRepVentasNoExiste() {
        try {
            jard.clientes().guardar(new Cliente.Builder(4824, "TestHib", "dfsjkd", "dghdf", "ads", "dfs")
                    .conEmpleadoRepVentas(69).buildOrThrow()); // hay codigos de empleado del 1 al 31
        } catch (ExcepcionCodigoYaExistente | ExcepcionDatoNoValido | ExcepcionFormatoIncorrecto e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getearCliente(){
        jard.clientes().uno(20).ifPresent(e -> System.out.println(e));
    }
}
