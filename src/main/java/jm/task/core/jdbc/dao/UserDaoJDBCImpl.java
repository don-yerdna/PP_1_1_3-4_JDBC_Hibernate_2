package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `users` (" +
                    "  `id` BIGINT(255) NOT NULL AUTO_INCREMENT," +
                    "  `name` VARCHAR(45) NULL," +
                    "  `last_name` VARCHAR(45) NULL," +
                    "  `age` TINYINT(255) NULL," +
                    "  PRIMARY KEY (`id`));";
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Не удалось создать таблицу");
        }
        if (connection != null) {
            Util.closeConnection(connection);
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS users;";
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Не удалось удалить таблицу");
        }
        if (connection != null) {
            Util.closeConnection(connection);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "INSERT INTO `users` (`name`, `last_name`, `age`) VALUES ('" + name + "', '" + lastName + "', '" + age + "');";
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Не удалось создать юзера");
        }
        if (connection != null) {
            Util.closeConnection(connection);
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "DELETE FROM users WHERE (id = " + id + ");";
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Не удалось удалить юзера");
        }
        if (connection != null) {
            Util.closeConnection(connection);
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        Connection connection = Util.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM users;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Не удалось создать спсок юзеров");
        }
        if (connection != null) {
            Util.closeConnection(connection);
        }
        return users;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "DELETE FROM users;";
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Не удалось очистить таблицу");
        }
        if (connection != null) {
            Util.closeConnection(connection);
        }
    }
}
