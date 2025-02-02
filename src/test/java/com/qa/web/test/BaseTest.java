package com.qa.web.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.enums.ConfigKeys;
import com.qa.factory.PropertiesReader;
import com.qa.pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

public class BaseTest extends BasePage {

    protected HomePage homePage;
    protected ProductPage productPage;
    protected ProductSearchPage productSearchPage;
    protected ShoppingCartPage shoppingCartPage;

    @BeforeSuite
    public void beforeSuite() {
        Path path = Paths.get(OUTPUT_FOLDER);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        reporter.config().setReportName("FIS Automation Assessment Test Results");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("System", "WINDOWS");
        extentReports.setSystemInfo("Author", "Rohan Waghmare");
        extent = extentReports;
        System.out.println("Test Suite started!");
    }

    @BeforeMethod
    @Parameters({"type"})
    public void beforeTest(@Optional("web") String type, ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        System.out.println("Test: ["+type.toUpperCase()+"] "+methodName + " started!");
        ExtentTest extentTest = extent.createTest("["+type.toUpperCase()+"] "+result.getMethod().getMethodName(),result.getMethod().getDescription());
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));

        String testType = type.toLowerCase();
        if (testType.equals("web")) {

            String browser = PropertiesReader.getProperty(ConfigKeys.BROWSER);
            initDriver(browser);
            getDriver().manage().window().maximize();
            String url = PropertiesReader.getProperty(ConfigKeys.WEB_URL);
            getDriver().get(url);
            System.out.println("Opening app: " + url);
        }
    }

    @Parameters({"type"})
    @AfterMethod
    public void afterTest(@Optional("web") String type, ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println(("Test: "+methodName + " passed!"));
            test.get().pass("Test passed");
            if (type.equalsIgnoreCase("web")) {
                test.get().pass(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshot()).build());
                closeBrowsers();

            }
            test.get().getModel().setEndTime(getTime(result.getEndMillis()));
            System.out.println(("Test: "+methodName + " ended!"));
        } else if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println(("Test: "+methodName + " failed!"));
            test.get().fail("Test failed");
            if (type.equalsIgnoreCase("web")) {
                test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshot()).build());
                closeBrowsers();
            }
            test.get().getModel().setEndTime(getTime(result.getEndMillis()));

        } else if (result.getStatus() == ITestResult.SKIP) {
            System.out.println(("Test: "+methodName + " skipped!"));
            test.get().fail("Test skipped");
        }
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println(("Test Suite is ending!"));
        extent.flush();
        test.remove();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
