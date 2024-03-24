package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        final UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        List<User> users = new ArrayList<>();
        users.add(new User("Alexandr", "Ponomariov", (byte) 33));
        users.add(new User("Vladimir", "Aronov", (byte) 21));
        users.add(new User("Irina", "Romanova", (byte) 50));
        users.add(new User("Anatoly", "Ivanov", (byte) 39));

        for (User us : users) {
            userService.saveUser(us.getName(), us.getLastName(), us.getAge());
            System.out.println("User " + us.getName() + " добавлен в базу данных");
        }

        List<User> usersTable = userService.getAllUsers();
        for (User us : usersTable) {
            System.out.println(us);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}