package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.qa.utils.WebUtils.*;

public class HomePage extends BasePage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@title='Search']")
    private WebElement input_Search;

    @FindBy(id = "gh-search-btn")
    private WebElement button_Search;


    @FindBy(xpath = "//span[text()='Shop by category']")
    private WebElement link_shopByCat;

    @FindBy(xpath = "//div[@class='gh-categories__main']//a/h3[text()='Motors']")
    private WebElement link_Motors;

    @FindBy(xpath = "//div[@class='main-content']//h1")
    private WebElement header_motors;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void searchProduct(String productName) {
        sendKeys(driver,input_Search,productName);
        click(driver,button_Search);
        BasePage.test.get().info("Searching product: "+productName);
        System.out.println("Searching product: "+productName);
    }

    public void gotoMotors() {
        click(driver,link_shopByCat);
        click(driver,link_Motors);

    }

    public String getMotorPageTitle() {
        return header_motors.getText().trim();
    }

}
