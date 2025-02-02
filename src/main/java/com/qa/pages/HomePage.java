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

}
