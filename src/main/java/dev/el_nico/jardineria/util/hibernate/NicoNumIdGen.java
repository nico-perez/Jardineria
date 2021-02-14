package dev.el_nico.jardineria.util.hibernate;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.spi.NativeQueryImplementor;
import org.hibernate.query.spi.QueryImplementor;

/**
 * Para autogenerar claves numéricas
 */
public class NicoNumIdGen implements IdentifierGenerator {

    public static final String STRAT = "dev.el_nico.jardineria.util.hibernate.NicoNumIdGen";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        String query = "select min(:id) + 1 from :cl c where (select :id from :cl where :id = c.:id + 1) is null"
                .replaceAll(":id", session.getEntityPersister(obj.getClass().getName(), obj).getIdentifierPropertyName())
                .replaceAll(":cl", obj.getClass().getSimpleName());
        return (int) session.createQuery(query).uniqueResultOptional().orElse(1);
    }
    
}
