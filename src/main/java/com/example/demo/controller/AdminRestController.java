package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.allUsers());
    }

    @GetMapping("/oneUser")
    public ResponseEntity<User> oneUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<Void> create(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.delete(userService.findById(id));
    }

    @PatchMapping("/users/{id}") // изменяет
    public User updateUser(@RequestBody User user, @PathVariable("id") int id) {
        return userService.updateUser(id, user);
    }


}
