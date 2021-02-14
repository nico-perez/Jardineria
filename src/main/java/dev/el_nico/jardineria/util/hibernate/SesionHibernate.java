package dev.el_nico.jardineria.util.hibernate;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SesionHibernate {

    private static Session INSTANCIA;

    private SesionHibernate() {
    }

    public static boolean login(String user, String pass) {
        if (INSTANCIA == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySetting("hibernate.connection.username", user)
                    .applySetting("hibernate.connection.password", pass).configure().build();
            try {
                SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                INSTANCIA = sessionFactory.openSession();
                return true;
            } catch (HibernateException e) {
                e.printStackTrace();
                StandardServiceRegistryBuilder.destroy(registry);
                return false;
            } 
        } else {
            return false;
        }
    }

    public static Session get() {
        if (INSTANCIA == null) {
            throw new Error("sesion no inicializada");
        } else {
            return INSTANCIA;
        }
    }

    public static void close() throws IOException {
        if (INSTANCIA != null) {
            INSTANCIA.close();
        }
    }

}
