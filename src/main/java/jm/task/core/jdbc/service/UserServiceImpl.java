package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;


public class UserServiceImpl implements UserService {
    private final UserDao UserDaoHiber = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        UserDaoHiber.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        UserDaoHiber.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        UserDaoHiber.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        UserDaoHiber.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return UserDaoHiber.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        UserDaoHiber.cleanUsersTable();
    }


}
