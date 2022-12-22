package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {

    public static void main(String[] args) {


        final UserService userService = new UserServiceImpl();


        userService.createUsersTable();

        userService.saveUser("Александр" , "Иванович" , (byte) 45);
        userService.saveUser("Людмила" , "Викторовна" , (byte) 42);
        userService.saveUser("Ярослав" , "Александрович" , (byte) 11);
        userService.saveUser("Степан" , "Александрович" , (byte) 16);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}