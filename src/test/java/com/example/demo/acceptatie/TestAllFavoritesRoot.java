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
import static org.junit.Assert.assertThat;

public class TestAllFavoritesRoot {

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

        element = driver.findElement(By.name("favorite"));
        element.sendKeys("pasta");
        element.submit();

        element = driver.findElement(By.name("favorite"));
        element.sendKeys("asta");
        element.submit();
    }

    @Test
    public void allFavorites(){
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9090/tdd");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("root");
        element = driver.findElement(By.name("password"));
        element.sendKeys("rootpasswd");
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: root"));

        element = driver.findElement(By.name("allfav"));
        element.submit();

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Favorites: root"));
        element = driver.findElement(By.name("favorites"));
        List<WebElement> rows = element.findElements(By.tagName("td"));
        assertEquals(2, rows.size());
        assertEquals("asta", rows.get(0).getText());
        assertEquals("pasta", rows.get(1).getText());

    }

}
