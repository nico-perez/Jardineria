package dev.el_nico.jardineria.util.hibernate;

import java.io.Serializable;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import dev.el_nico.jardineria.modelo.Producto;

/**
 * Para autogenerar los códigos de productos. Los códigos de producto son: - Si
 * la gama es "Herramientas", un entero (String) de cinco cifras. - Si la gama
 * es cualquier otra, los dos primeros caracteres de la gama, en mayúsculas, un
 * guion y un numero.
 * 
 * Eg. Gama "Frutales" -> Productos FR-2, FR-59, FR-183, etc.
 */
public class NicoProdIdGen implements IdentifierGenerator {

    public static final String STRAT = "dev.el_nico.jardineria.util.hibernate.NicoProdIdGen";

    @Override
    @SuppressWarnings("unchecked")
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (object instanceof Producto) {
            Producto p = (Producto) object;
            if (p.getGama().equals("Herramientas")) {
                // número
                Integer nuevo = ((Stream<String>) session.createQuery("select codigo_producto from Producto where gama = 'Herramientas'")
                        .stream()).mapToInt(Integer::parseInt).max().orElse(0) + 1;
                return nuevo.toString();
            } else {
                String prefijo;
                try {
                    prefijo = p.getGama().substring(0, 2).toUpperCase();
                } catch (IndexOutOfBoundsException e) {
                    prefijo = "??";
                }
                
                String query = "select substring(codigo_producto, 4) from Producto where codigo_producto like ':??-%'"
                        .replace(":??", prefijo);
                int nuevo = ((Stream<String>) session.createQuery(query).stream()).mapToInt(Integer::parseInt).max().orElse(0) + 1;

                return prefijo + "-" + nuevo;
            }
        } else {
            return null;
        }
    }
}
