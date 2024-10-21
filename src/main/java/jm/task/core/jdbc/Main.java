package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userDao = new UserServiceImpl();
        userDao.createUsersTable();
        userDao.saveUser("Vasya", "Pupkin", (byte) 48);
        userDao.saveUser("Sidor", "Sidorov", (byte) 32);
        userDao.saveUser("Yuriy", "Gagarin", (byte) 24);
        userDao.saveUser("Noname", "Nonameus", (byte) 15);
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        userDao.removeUserById(2);
        List<User> users1 = userDao.getAllUsers();
        for (User user : users1) {
            System.out.println(user.toString());
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
