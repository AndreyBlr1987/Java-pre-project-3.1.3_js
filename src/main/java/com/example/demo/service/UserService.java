package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> allUsers();

    void create(User user);

    void delete(User user);

    User getByEmail(String email);

    User findById(Integer id);

    void edit(User user);

    public User updateUser(Integer id, User updatedUser);
}
