package com.qa.utils;

import com.qa.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

//import static com.qa.factory.WebDriverManager.getDriver;
import static com.qa.pages.BasePage.getDriver;

public class WebUtils extends ApiUtils{

    public static void switchToWindow(WebDriver driver) {

        String currentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for(String window : windows) {

            if(!currentWindow.equals(window)) {
                driver.switchTo().window(window);
                return;
            }
        }
    }

    public static void sendKeys(WebDriver driver, WebElement element, String input) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.sendKeys(input);
        }catch (Exception e) {
            System.out.println("Something went wrong for element:"+element.toString());
            getDriver().close();
            e.printStackTrace();
        }

    }

    public static void click(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }catch (Exception e) {
            System.out.println("Element not found:"+element.toString());
            getDriver().close();
            e.printStackTrace();
        }

    }

    public static String getText(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.getText().trim();
        }catch (Exception e) {
            System.out.println("Element not found:"+element.toString());
            getDriver().close();
            e.printStackTrace();
        }

        return null;
    }

    public static void assertEquals(String actual,String expected) {

        if(actual.equals(expected)) {
            BasePage.test.get().pass("Actual: ["+actual+"] matched with expected: ["+expected+"]");
        }else {
            BasePage.test.get().fail("Actual: ["+actual+"] not matched with expected: ["+expected+"]");
        }
    }
}
