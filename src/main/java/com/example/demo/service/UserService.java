package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> allUsers();

    void create(User user);

    void delete(User user);

    User getById(int id);

    User getUserByEmail(String email);

    User getUserById(int id);

    void updateUser(int id, User updatedUser);

    void edit(User user);
}
