package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("SELECT DISTINCT user FROM User user join fetch user.roles", User.class)
                .getResultList();
    }

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(getById(user.getId()));
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByEmail(String email) {
        return entityManager.createQuery(
                        "SELECT user FROM User user join fetch  user.roles WHERE user.email =:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User getUserById(int id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(int id, User updatedUser) {
        User user = getUserById(id);
        user.setName(updatedUser.getName());
        user.setLastname(updatedUser.getLastname());
        user.setAge(updatedUser.getAge());
        user.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != "")
            user.setPassword(updatedUser.getPassword());
        user.setRoles(updatedUser.getRoles());
        entityManager.merge(user);
    }
}
