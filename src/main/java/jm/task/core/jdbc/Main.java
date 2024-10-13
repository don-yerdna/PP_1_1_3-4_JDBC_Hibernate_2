package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userDaoJDBC = new UserServiceImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Vasya", "Pupkin", (byte) 48);
        userDaoJDBC.saveUser("Sidor", "Sidorov", (byte) 32);
        userDaoJDBC.saveUser("Yuriy", "Gagarin", (byte) 24);
        userDaoJDBC.saveUser("Noname", "Nonameus", (byte) 15);
        List<User> users = userDaoJDBC.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
