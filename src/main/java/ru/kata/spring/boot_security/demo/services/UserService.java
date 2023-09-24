package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    User showUser(Long id);

    List<User> showUsers();

    User findByUsername(String name);

    User findByEmail (String email);

    public void updateUser(Long id, User user);

    public void saveUser(User user);

    public void deleteUserById(Long id);

    public void register(User user);
}
