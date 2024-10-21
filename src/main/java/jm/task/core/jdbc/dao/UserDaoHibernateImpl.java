package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.awt.datatransfer.Transferable;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction = null;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS `users` (" +
                "  `id` BIGINT(255) NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(45) NULL," +
                "  `last_name` VARCHAR(45) NULL," +
                "  `age` TINYINT(255) NULL," +
                "  PRIMARY KEY (`id`))";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS users";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);

        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.createQuery("delete from User where id = " + id).executeUpdate();
        transaction.commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();

        return session.createQuery("from User",User.class).list();
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        transaction.commit();
    }
}
