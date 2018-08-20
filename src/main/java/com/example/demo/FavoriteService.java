package com.example.demo;

import com.example.demo.User;
import com.example.demo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Service
public class FavoriteService {
    @Autowired
    private Users users;

    public boolean checkLogin(String username, String password) {
        return users.login(username, password);
    }

    public void addUser(String root, String rootpasswd, String username, String password) {
        if ("root".equals(root) && "rootpasswd".equals(rootpasswd)) {
            users.addUser(username, password);
        }
    }

    public void removeUser(String root, String rootpasswd, String username) {
        if ("root".equals(root) && "rootpasswd".equals(rootpasswd)) {
            users.removeUser(username);
        }
    }

    public String getPassword(String root, String rootpasswd, String username) {
        if ("root".equals(root) && "rootpasswd".equals(rootpasswd)) {
            if (checkUser(username)){
                return users.getUser(username).getPassword();
            }
        }
        return "";
    }

    public boolean checkUser(String username) {
        return users.getUser(username) != null;
    }

    public List<String> getFavorites(String username, String password) {
        if (checkLogin(username, password)) {
            User user = users.getUser(username);
            return user.getFavorites();
        }
        return new ArrayList<>();

    }

    public void addFavorite(String username, String password, String favorite1) {
        if (checkLogin(username, password)) {
            User user = users.getUser(username);
            user.addFavorites(favorite1);
        }
    }

    public void removeFavorite(String username, String password, String favorite1) {
        if (checkLogin(username, password)) {
            User user = users.getUser(username);
            List<String> fav = user.getFavorites();
            fav.remove(favorite1);
            user.setFavorites(fav);
        }

    }

    public TreeSet<String> getAllFavorites(String root, String pass) {
        if ("root".equals(root) && "rootpasswd".equals(pass)) {
            List<User> userList = users.getUsers();
            TreeSet<String> favs = new TreeSet<>();
            for (User user : userList) {
                favs.addAll(user.getFavorites());
            }
            return favs;
        }
        return new TreeSet<>();
    }
}
