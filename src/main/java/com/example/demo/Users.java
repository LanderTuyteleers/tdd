package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Users {
    @Autowired
    private UserDao userDao;

    public void addUser(String username, String password) {
        User user = new User(username, password);
        userDao.create(user);
    }

    public void removeUser(String username){
        User user=userDao.getUser(username);
        userDao.delete(user);
    }

    public boolean login(String username, String password) {
        User user = userDao.getUser(username);
        if (user == null) return false;
        if (!user.getPassword().equals(password)) return false;
        return true;
    }

    public User getUser(String username){
        return userDao.getUser(username);
    }

    public List<User> getUsers (){
        return userDao.getUsers();
    }

}
