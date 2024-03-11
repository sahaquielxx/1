package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    private static volatile SessionFactory sessionFactory;
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private Util() {
    }

    public static Session getSession() {
        if (sessionFactory == null) {
            synchronized (Util.class) {
                if (sessionFactory == null) {
                    try {
                        Configuration configuration = new Configuration();
                        configuration.setProperty("hibernate.connection.driver_class", DB_DRIVER);
                        configuration.setProperty("hibernate.connection.url", DB_URL);
                        configuration.setProperty("hibernate.connection.username", DB_USERNAME);
                        configuration.setProperty("hibernate.connection.password", DB_PASSWORD);

                        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

                        configuration.addAnnotatedClass(User.class);

                        sessionFactory = configuration.buildSessionFactory();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return sessionFactory.openSession();
    }
}