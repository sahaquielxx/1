package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class Util {

    private static volatile SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (Util.class) {
                if (sessionFactory == null) {
                    try {
                        Configuration configuration = new Configuration();
                        Properties settings = new Properties();
                        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mydbtest");
                        settings.put(Environment.USER, "root");
                        settings.put(Environment.PASS, "root");
                        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                        settings.put(Environment.SHOW_SQL, "true");

                        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                        settings.put(Environment.HBM2DDL_AUTO, "");

                        configuration.setProperties(settings);

                        configuration.addAnnotatedClass(User.class);

                        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties()).build();

                        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return sessionFactory;
    }
}