package com.example.demo.acceptatie;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

public class TestRemoveUser {

    @Before
    public void setup() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9090/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("root");
        element = driver.findElement(By.name("password"));
        element.sendKeys("rootpasswd");
        element.submit();
        (new WebDriverWait(driver, 5)).until((WebDriver d) -> d.getTitle().equals("Favorites: root"));
        element = driver.findElement(By.name("newusername"));
        element.sendKeys("testuser");
        element = driver.findElement(By.name("newpassword"));
        element.sendKeys("testuser");
        element.submit();
    }

    @Test
    public void testRemoveUser() throws InterruptedException {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9090/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("root");
        element = driver.findElement(By.name("password"));
        element.sendKeys("rootpasswd");
        element.submit();
        (new WebDriverWait(driver, 5)).until((WebDriver d) -> d.getTitle().equals("Favorites: root"));
        element = driver.findElement(By.name("removeuser"));
        element.sendKeys("testuser");
        element.submit();
        driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9090/tdd");
        element = driver.findElement(By.name("username"));
        element.sendKeys("testuser");
        element = driver.findElement(By.name("password"));
        element.sendKeys("testuser");
        element.submit();
        (new WebDriverWait(driver, 5)).until((WebDriver d) -> !(d.findElement(By.id("error")).getText().equals("")));
        element = driver.findElement(By.id("error"));
        String error = element.getText();
        assertEquals("Wrong username or password", error);
    }

    @Test
    public void testRemoveWrongUser() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9090/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("root");
        element = driver.findElement(By.name("password"));
        element.sendKeys("rootpasswd");
        element.submit();
        (new WebDriverWait(driver, 5)).until((WebDriver d) -> d.getTitle().equals("Favorites: root"));
        element = driver.findElement(By.name("removeuser"));
        element.sendKeys("wrong");
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> !(d.findElement(By.id("error")).getText().equals("")));
        element = driver.findElement(By.id("error"));
        String error = element.getText();
        assertEquals("User does not exist", error);
    }

}
