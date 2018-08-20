package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoWithMap implements UserDao {
    private Map<String, User> users;

    public UserDaoWithMap() {
        this.users = new HashMap<>();
    }

    @Override
    public void create(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }

    @Override
    public void delete(User user) {
        users.remove(user.getUsername(),user);
    }

    public List<User> getUsers(){
        List<User> list = new ArrayList<User>(users.values());
        return list;
    }

}
