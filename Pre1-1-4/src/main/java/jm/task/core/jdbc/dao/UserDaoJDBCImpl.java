package jm.task.core.jdbc.dao;

import com.mysql.cj.util.Util;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {  // Обработка всех исключений
    // private final Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        String sql = ("CREATE TABLE IF NOT EXISTS 'test'.'users'("
                    + "'id' mediumint not null auto_increment,"
                    + " 'name' VARCHAR(50),"
                    + " 'lastname' VARCHAR(50),"
                    + " 'age' tinyint,"
                    + "PRIMARY KEY ('id'))");
            System.out.println("Таблица создана");
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
//
//    public void dropUsersTable() {
//        try (Statement statement = connection.createStatement()) {
//            statement.executeUpdate("DROP TABLE IF EXISTS 'test'.'users'");
//            System.out.println("Таблица удалена");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        String sql = "INSERT INTO test.users(name, lastname, age) VALUES(?,?,?)";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setByte(3, age);
//            preparedStatement.executeUpdate();
//            System.out.println("User с именем – " + name + " добавлен в базу данных");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void removeUserById(long id) {
//        try (Statement statement = connection.createStatement()) {
//            String sql = "DELETE FROM test.users WHERE id";
//            statement.executeUpdate(sql);
//            System.out.println("User удален");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<User> getAllUsers() {
//        List<User> allUser = new ArrayList<>();
//        String sql = "SELECT id, name, lastName, age FROM test.users";
//
//        try (Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getLong("id"));
//                user.setName(resultSet.getString("name"));
//                user.setLastName(resultSet.getString("lastName"));
//                user.setAge(resultSet.getByte("age"));
//                allUser.add(user);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return allUser;
//    }
//
//    public void cleanUsersTable() {
//        String sql = "TRUNCATE test.users";
//        try (Statement statement = connection.createStatement()) {
//            statement.executeUpdate(sql);
//            System.out.println("Таблица очищена");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Не удалось очистить");
//        }
//    }
}

