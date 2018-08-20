package com.example.demo.integratie;

import com.example.demo.FavoriteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"../../../../../resources/testcontext.xml"})

public class TestAddedFavorites {

    @Autowired
    private FavoriteService favoriteService;

    @Before
    public void setup() {
        favoriteService.addUser("root", "rootpasswd", "username", "password");
        favoriteService.addUser("root", "rootpasswd", "username2", "password2");
     }

    @Test
    public void testAllFavoritesRoot(){
        favoriteService.addFavorite("username", "password", "pasta");
        favoriteService.addFavorite("username", "password", "rasta");
        favoriteService.addFavorite("username", "password", "zasta");
        favoriteService.addFavorite("username2", "password2", "masta");
        favoriteService.addFavorite("username2", "password2", "tasta");
        favoriteService.addFavorite("username2", "password2", "zasta");
        TreeSet<String> favorites = favoriteService.getAllFavorites("root", "rootpasswd");
        assertNotNull("getFavorites should never return null", favorites);
        assertEquals("there should be 5 favorites in the treeset", 5, favorites.size());
    }

    @Test
    public void testNoFavoritesRoot(){
        TreeSet<String> favorites = favoriteService.getAllFavorites("root", "rootpasswd");
        assertNotNull("getFavorites should never return null", favorites);
        assertEquals("there should be 0 favorites in the treeset", 0, favorites.size());
    }

    @Test
    public void testGetPasswordOfNonExistingUser() {
        String pass = favoriteService.getPassword("root", "rootpasswd", "who");
        assertNotNull("password should never return null", pass);
        assertEquals("there should be a empty password", "", pass);
    }

    @Test
    public void testGetPasswordOfUser() {
        String pass = favoriteService.getPassword("root", "rootpasswd", "username");
        assertNotNull("password should never return null", pass);
        assertEquals("there should be a password", "password", pass);
    }

    @Test
    public void testGetPasswordOfUserNotRoot() {
        String pass = favoriteService.getPassword("user", "user", "username");
        assertNotNull("password should never return null", pass);
        assertEquals("there should be a password", "", pass);
    }

    @Test
    public void testCheckExistingUser() {
         assertTrue("there should be a user", favoriteService.checkUser("username"));
    }

    @Test
    public void testCheckNonExistingUser() {
        assertFalse("there should no user", favoriteService.checkUser("who"));
    }
}
