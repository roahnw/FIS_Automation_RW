package com.qa.factory;

import com.qa.enums.Browsers;
import com.qa.enums.ConfigKeys;
import com.qa.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver webDriver) {
        WebDriverManager.driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    protected WebDriver initDriver(String browser) {

        boolean headless = Boolean.parseBoolean(PropertiesReader.getProperty(ConfigKeys.HEADLESS));

        if (browser.equalsIgnoreCase(Browsers.CHROME.toString())) {
            ChromeOptions options = new ChromeOptions();
            if (headless)
                options.addArguments("--headless=new");
            setDriver(new ChromeDriver(options));

        } else if (browser.equalsIgnoreCase(Browsers.FIREFOX.toString())) {
            FirefoxOptions options = new FirefoxOptions();
            if (headless)
                options.addArguments("-headless");
            setDriver(new FirefoxDriver(options));
        } else if (browser.equalsIgnoreCase(Browsers.EDGE.toString())) {
            EdgeOptions options = new EdgeOptions();
            if (headless) {
                options.addArguments("--headless");
            }
            setDriver(new EdgeDriver(options));
        } else {
            throw new IllegalArgumentException("Invalid browser :" + browser);
        }
        System.out.println("Browser: [" + browser.toUpperCase() + "] started");
        return getDriver();
    }

    protected static void removeInstance() {
        driver.remove();
    }
}
