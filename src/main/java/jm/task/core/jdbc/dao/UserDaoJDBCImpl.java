package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "CREATE TABLE IF NOT EXISTS `users` (" +
                "  `id` BIGINT(255) NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(45) NULL," +
                "  `last_name` VARCHAR(45) NULL," +
                "  `age` TINYINT(255) NULL," +
                "  PRIMARY KEY (`id`))";
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Не удалось создать таблицу");
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Не удалось удалить таблицу");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, last_name, age) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Не удалось создать юзера");
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE (id = ?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Не удалось удалить юзера");
        }
    }

    @Override
    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
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
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Не удалось очистить таблицу");
        }
    }
}
