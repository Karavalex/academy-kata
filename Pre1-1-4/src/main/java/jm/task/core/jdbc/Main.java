package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserServiceTest userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Александр", "Иванович", (byte) 45);
        userService.saveUser("Людмила", "Викторовна", (byte) 42);
        userService.saveUser("Ярослав", "Александрович", (byte) 11);
        userService.saveUser("Алина", "Шамильевна", (byte) 16);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}