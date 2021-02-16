package dev.el_nico.jardineria.util.hibernate;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * Para autogenerar los códigos de productos. Los códigos de producto son: - Si
 * la gama es "Herramientas", un entero (String) de cinco cifras. - Si la gama
 * es cualquier otra, los dos primeros caracteres de la gama, en mayúsculas, un
 * guion y un numero de tres cifras. Eg. Gama "Frutales" -> Productos FR-012,
 * FR-459, FR-100, etc.
 */

public class NicoProdIdGen implements IdentifierGenerator {

    public static final String STRAT = "dev.el_nico.jardineria.util.hibernate.NicoProdIdGen";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }
}
