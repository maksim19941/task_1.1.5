package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "    id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "    name VARCHAR(255) NOT NULL," +
                "    lastname VARCHAR(255) NOT NULL," +
                "    age INT NOT NULL" +
                ")";

        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS users";

        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastname, age) VALUES(?, ?, ?)";

        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sql)
                    .setParameter(1, name)
                    .setParameter(2, lastName)
                    .setParameter(3, age)
                    .executeUpdate();
            transaction.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id=?";

        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sql)
                    .setParameter(1, id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT id, name, age, lastname FROM users";
        List<User> user = new ArrayList<>();
        try(Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(User.class);
            user = query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (User u : user) {
            System.out.println(u.toString());
        }
        return user;
}




    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM users";

        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
