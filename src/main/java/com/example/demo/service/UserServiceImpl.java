package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void create(User user) {
        User newUser = user;
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);
    }

    @Transactional
    public void deleteByID(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public void edit(User user) {
        if (user.getPassword() == "")
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        User user1 = user.get();
        return user1;
    }

    @Override
    @Transactional
    public User updateUser(Integer id, User updatedUser) {
        Optional<User> user2 = userRepository.findById(id);
        User user = user2.get();
        user.setName(updatedUser.getName());
        user.setLastname(updatedUser.getLastname());
        user.setAge(updatedUser.getAge());
        user.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != "")
            user.setPassword(updatedUser.getPassword());
        user.setRoles(updatedUser.getRoles());
        return user;
    }

}

