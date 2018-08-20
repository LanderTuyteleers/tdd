package com.example.demo;
import com.example.demo.acceptatie.*;
import com.example.demo.integratie.TestAddedFavorites;
import com.example.demo.integratie.TestFavorites;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestRemoveUser.class,
        TestLoginRoot.class ,
        TestLoginScreen.class,
        TestLoginUser.class,
        TestRoot.class,
        TestUser.class,
        TestRootFavorites.class,
        TestAllFavoritesRoot.class,
        TestAddedFavorites.class,
        TestFavorites.class})
public class allTest {
}

