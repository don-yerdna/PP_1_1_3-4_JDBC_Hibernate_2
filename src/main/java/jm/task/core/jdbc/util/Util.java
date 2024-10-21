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
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL_TO_DB = "jdbc:mysql://localhost:3306/db_pp?useSSL=false";
    private static final String LOGIN_TO_DB = "root";
    private static final String PASSWORD_TO_DB = "ghjcnjgfhjkm";
    private static final String DIALECT_DB = "org.hibernate.dialect.MySQL55Dialect";

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try{
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DB_DRIVER);
                settings.put(Environment.URL, URL_TO_DB);
                settings.put(Environment.USER, LOGIN_TO_DB);
                settings.put(Environment.PASS, PASSWORD_TO_DB);
                settings.put(Environment.DIALECT, DIALECT_DB);
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;

    }
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(URL_TO_DB, LOGIN_TO_DB, PASSWORD_TO_DB);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Не удалось установить соединение с БД");
        }
        return connection;
    }
}
