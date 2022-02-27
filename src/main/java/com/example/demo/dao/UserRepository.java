package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

   @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = (:email)")
    User findByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles")
    List<User> findAll() ;

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id =(:id)")
    Optional<User> findById(Integer id);
}


