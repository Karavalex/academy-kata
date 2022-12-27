package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;


public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), lastname VARCHAR(40), age INT)");
            connection.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name , String lastName , byte age) {
        String sql = "INSERT INTO test.users(name, lastname, age) VALUES(?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1 , name);
            statement.setString(2 , lastName);
            statement.setByte(3 , age);
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " " + lastName + " " + age + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pstm.setLong(1 , id);
            pstm.executeUpdate();
            System.out.println("Удаление User из таблицы ( по id )");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>(); //
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, name, lastname, age FROM users")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(Long.valueOf(resultSet.getString(1)));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}