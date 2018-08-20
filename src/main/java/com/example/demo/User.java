package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;
    private final String password;
    private List<String> favorites;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.favorites = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }

    public List<String> addFavorites(String favorite){
        this.favorites.add(favorite);
        return favorites;
    }

    public String getUsername() {
        return username;
    }
}
