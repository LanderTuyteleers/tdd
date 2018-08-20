package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    void create(User user);
    User getUser(String username);
    void delete(User user);
    List<User> getUsers();
}
