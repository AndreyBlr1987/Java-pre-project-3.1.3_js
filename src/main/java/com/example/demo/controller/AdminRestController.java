package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public List<User> allUsers() {
        return userService.allUsers();
    }

    @GetMapping(value = "/users/{id}")
    public User findById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user) {
        userService.edit(user);
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.delete(userService.findById(id));
    }


}
