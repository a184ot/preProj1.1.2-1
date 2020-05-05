package service;

import dao.UserDAO;
import exception.DBException;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public UserService() {
    }

    UserDAO userDAO = getUserDAO();

    UserDAO dao = getUserDAO();

    public void updateUserA(User user) throws SQLException {
        updateUserName(user.getId(), user.getName());
        updateUserAge(user.getId(), user.getAge());
        updateUserEmail(user.getId(), user.getEmail());
    }

    public void updateUser(User user) throws SQLException {
        userDAO.updateUser(user);
    }

    public void updateUserName(Long id, String name) throws SQLException {
        userDAO.updateUserName(id, name);
    }

    public void updateUserAge(Long id, Long age) throws SQLException {
        userDAO.updateUserAge(id, age);
    }

    public void updateUserEmail(Long id, String email) throws SQLException {
        userDAO.updateUserEmail(id, email);
    }

    public User getUserById(Long id) {
        try {
            return userDAO.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsersByName(String name) {
        try {
            return userDAO.getAllUsersByName(name);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<User> getAllUsersByAddress(String email) {
        try {
            return userDAO.getAllUsersByAddress(email);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean isUserExist(String name, Long age, String email) {
        return userDAO.isUserExist(name, age, email);
    }

    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteUser(Long id) throws SQLException {
        return userDAO.deleteUser(id);
    }

    public boolean addUser(User user) throws SQLException {

        if (userDAO.isUserExist(user.getName(), user.getAge(), user.getEmail()) == false) {
            userDAO.addUser(user);
            return true;
        } else {
            return false;
        }
    }


    public static void cleanUp() throws DBException {
        try {
            getUserDAO().dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void createTable() throws DBException {
        try {
            getUserDAO().createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=1100").
                    append("&serverTimezone=UTC");
            System.out.println("URL: " + url + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }
}
