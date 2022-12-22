package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {  // Обработка всех исключений
    // private final Connection connection = Util.getConnection();


 public UserDaoJDBCImpl() {
 }
    // Создание таблицы User(ов)
  public void createUsersTable() {
           try (Connection connection = Util.getConnection();
               Statement statement = connection.createStatement()) {
               statement.executeUpdate ("CREATE TABLE IF NOT EXISTS users " +
                       "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), lastname VARCHAR(40), age INT)");
               System.out.println("createUsersTable OK");
          } catch (SQLException e) {
               throw new RuntimeException(e);
           //    System.out.println("createUsersTable ERROR");
           }
      }


    // Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Добавление User в таблицу
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO test.users(name, lastname, age) VALUES(?,?,?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление User из таблицы ( по id )
    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement pstm = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
            System.out.println("Удаление User из таблицы ( по id )");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение всех User(ов) из таблицы
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection();
                ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users")) {
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastname"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Очистка содержания таблицы
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}