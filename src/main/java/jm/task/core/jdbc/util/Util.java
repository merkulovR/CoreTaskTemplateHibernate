package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pptasks";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "FFF8rkfccybwf";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.setProperty(Environment.HBM2DDL_AUTO, "update");
                properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.setProperty(Environment.SHOW_SQL, "true");
                properties.setProperty(Environment.DRIVER, DRIVER);
                properties.setProperty(Environment.USER, USERNAME);
                properties.setProperty(Environment.PASS, PASSWORD);
                properties.setProperty(Environment.URL, URL);

                Configuration configuration = new Configuration();
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.err.println("Не удалось создать SessionFactory!");
            }
        }

        return sessionFactory;
    }

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Ошибка соединения!");
        }

        return connection;
    }
}