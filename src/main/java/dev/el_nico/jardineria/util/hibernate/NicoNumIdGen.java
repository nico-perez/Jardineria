package dev.el_nico.jardineria.util.hibernate;

import java.io.Serializable;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * Para autogenerar claves numéricas. Pilla la primera nula o 1, si no hay ninguna.
 */
public class NicoNumIdGen implements IdentifierGenerator {

    public static final String STRAT = "dev.el_nico.jardineria.util.hibernate.NicoNumIdGen";

    @Override
    @SuppressWarnings("unchecked")
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        String query = "select min(:id) + 1 from :cl c where (select :id from :cl where :id = c.:id + 1) is null"
                .replaceAll(":id", session.getEntityPersister(obj.getClass().getName(), obj).getIdentifierPropertyName())
                .replaceAll(":cl", obj.getClass().getSimpleName());
       
        return (int) ((Optional<Integer>) session.createQuery(query).uniqueResultOptional()).orElse(1);
    }
    
}
