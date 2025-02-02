package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.qa.utils.WebUtils.*;

public class ProductSearchPage extends BasePage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@id='srp-river-results']//ul[contains(@class,'srp-results')]/li[1]//span[@role='heading']")
    private WebElement link_FirstProduct;

    public ProductSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getFirstProductText() {
        return getText(driver,link_FirstProduct);
    }

    public void selectFirstProduct() {
        click(driver,link_FirstProduct);
        BasePage.test.get().info("Opening first product from product search page");
        System.out.println("Opening first product from product search page");
        switchToWindow(driver);
    }

}
