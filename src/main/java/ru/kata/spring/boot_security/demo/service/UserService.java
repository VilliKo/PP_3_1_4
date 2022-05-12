package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);
    void removeUserById(long id);
    List<User> getAllUsers();
    void cleanUsersTable();
    void updateUser(User user);
    User getUserById(long id);
    User getUserByEmail(String username);

}
