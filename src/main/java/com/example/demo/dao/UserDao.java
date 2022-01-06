package com.example.demo.dao;


import com.example.demo.entity.User;

import java.util.List;

public interface UserDao {

    List<User> allUsers();

    void add(User user);

    void delete(User user);

    void edit(User user);

    User getById(int id);

    User getUserByName(String name);

}
