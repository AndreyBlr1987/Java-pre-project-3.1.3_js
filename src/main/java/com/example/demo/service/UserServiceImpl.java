package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void deleteByID(int id) {
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
//    @Transactional
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public User updateUser(int id, User updatedUser) {
        User user = findById(id);
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

