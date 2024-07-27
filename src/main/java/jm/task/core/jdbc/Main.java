package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {


        Util.getSessionFactory();
        UserService userDao = new UserServiceImpl();

        userDao.createUsersTable();
        try {
            userDao.saveUser("Name1", "LastName1", (byte) 20);
            userDao.saveUser("Name2", "LastName2", (byte) 25);
            userDao.saveUser("Name3", "LastName3", (byte) 31);
            userDao.saveUser("Name4", "LastName4", (byte) 38);
        } catch (SQLException e) {
            System.out.println("Ошибка сохранения " + e);
        }


        try {
            userDao.removeUserById(1);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления " + e);
        }

        try {
            userDao.getAllUsers();
        } catch (SQLException e) {
            System.out.println("Не удалось вернуть список пользователей " + e);
        }


        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }


}
