package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }


    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SELECT * FROM user_tab");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                Long age = result.getLong(3);
                String email = result.getString(4);
                list.add(new User(id, name, age, email));
            }
        }
        return list;
    }

    public void updateUserEmail(Long id, String email) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("update user_tab set email=? where id=?")) {
            stmt.setString(1, email);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    public void updateUserAge(Long id, Long age) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("update user_tab set age=? where id=?")) {
            stmt.setLong(1, age);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    public void updateUserName(Long id, String name) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("update user_tab set name=? where id=?")) {
            stmt.setLong(2, id);
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }
    public void updateUser(User user) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("update user_tab set name=? , age=? , email=? where id=?")) {
            stmt.setString(1, user.getName());
            stmt.setLong(2,user.getAge());
            stmt.setString(3,user.getEmail());
            stmt.setLong(4, user.getId());
            stmt.executeUpdate();
        }
    }

    public User getUserById(long id) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("select * from user_tab where id='" + id + "'");
            ResultSet result = stmt.getResultSet();
            result.next();
            String name = result.getString(2);
            Long age = result.getLong(3);
            String email = result.getString(4);
            return new User(id, name, age, email);
        }
    }

    public List<User> getAllUsersByName(String name) throws SQLException {
        List<User> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("select * from user_tab where name='" + name + "'");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                long id = result.getLong(1);
                Long age = result.getLong(3);
                String email = result.getString(4);
                list.add(new User(id, name, age, email));
            }
        }
        return list;
    }

    public List<User> getAllUsersByAddress(String email) throws SQLException {
        List<User> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("select * from user_tab where email='" + email + "'");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                Long age = result.getLong(3);
                list.add(new User(id, name, age, email));
            }
        }
        return list;
    }

    public boolean isUserExist(String name, Long age, String email) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SELECT ROW_COUNT () FROM user_tab WHERE name='" + name + "'");
            ResultSet result = stmt.getResultSet();
            result.next();
            return result.first();
        } catch (SQLException d) {
            return false;
        }
    }

    public boolean isClientHasSum(String name, Long expectedSum) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("select * from user_tab where name='" + name + "'");
            ResultSet result = stmt.getResultSet();
            result.next();
            Long res = result.getLong(1);
            long money = result.getLong("money");
            res = res + 0L;
            return money >= expectedSum;
        }
    }
/*
    public User getClientByName(String name) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("select * from user_tab where name='" + name + "'");
            ResultSet result = stmt.getResultSet();
            if (result.next()) {
                long id = result.getLong(1);
                String pass = result.getString("password");
                Long money = result.getLong("money");
                return new User(id, name, age, address);
            } else return null;
        }
    }*/

    public boolean deleteUser(Long id) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM user_tab WHERE id = " + id);
            return true;
        } catch (SQLException t) {
            return false;
        }
    }

    public void addUser(User user) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("insert into user_tab (name, age, email) values (?, ?, ?)")) {
            stmt.setString(1, user.getName());
            stmt.setLong(2, user.getAge());
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();
        }
    }

    public void createTable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("create table if not exists user_tab (id bigint auto_increment, name varchar(256), age bigint, email varchar(256), primary key (id))");
        }
    }

    public void dropTable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS user_tab");
        }
    }
}
