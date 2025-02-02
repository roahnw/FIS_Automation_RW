package com.qa.pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qa.enums.Browsers;
import com.qa.enums.ConfigKeys;
import com.qa.factory.PropertiesReader;
import com.qa.factory.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class BasePage extends WebDriverManager {

    protected static final String OUTPUT_FOLDER = "./test-output/";
    protected static final String FILE_NAME = "TestExecutionReport.html";

    protected static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    protected static ExtentReports extentReports;

    public static void closeBrowsers() {
        getDriver().quit();
        removeInstance();
    }
    public String getScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
