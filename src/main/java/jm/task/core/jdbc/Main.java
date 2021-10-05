package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Arkadio", "Buendia", (byte) 127);
        userService.saveUser("Pietro", "Krespi", (byte) 27);
        userService.saveUser("Howard", "Duck", (byte) 1);
        userService.saveUser("Ma", "Donna", (byte) 99);

        List<User> users = userService.getAllUsers();

        for (User user : users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
