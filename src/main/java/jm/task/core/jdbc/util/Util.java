package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL_TO_DB = "jdbc:mysql://localhost:3306/db_pp";
    private static final String LOGIN_TO_DB = "root";
    private static final String PASSWORD_TO_DB = "ghjcnjgfhjkm";

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
