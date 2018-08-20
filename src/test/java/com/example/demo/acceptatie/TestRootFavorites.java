package com.example.demo.acceptatie;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestRootFavorites {

    @Before
    public void setup() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9090/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("root");
        element = driver.findElement(By.name("password"));
        element.sendKeys("rootpasswd");
        element.submit();

        element = driver.findElement(By.name("newusername"));
        element.sendKeys("normaluser");
        element = driver.findElement(By.name("newpassword"));
        element.sendKeys("normaluser");
        element.submit();

        driver.get("http://127.0.0.1:9090/tdd");
        element = driver.findElement(By.name("username"));
        element.sendKeys("normaluser");
        element = driver.findElement(By.name("password"));
        element.sendKeys("normaluser");
        element.submit();

        element = driver.findElement(By.name("favorite"));
        element.sendKeys("pasta");
        element.submit();
    }

    @Test
    public void testGetFavoritePerUser() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9090/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("root");
        element = driver.findElement(By.name("password"));
        element.sendKeys("rootpasswd");
        element.submit();

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: root"));
        element = driver.findElement(By.name("favuser"));
        element.sendKeys("normaluser");
        element.submit();

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: root"));
        element = driver.findElement(By.name("favorites"));
        List<WebElement> rows = element.findElements(By.tagName("td"));
        assertEquals(1, rows.size());
        assertEquals("pasta", rows.get(0).getText());

    }

    @Test
    public void testGetFavoriteWrongUser() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9090/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("root");
        element = driver.findElement(By.name("password"));
        element.sendKeys("rootpasswd");
        element.submit();

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: root"));
        element = driver.findElement(By.name("favuser"));
        element.sendKeys("wrong");
        element.submit();

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: root"));
        element = driver.findElement(By.id("error"));
        String error = element.getText();
        assertEquals("User does not exist", error);

    }

}
