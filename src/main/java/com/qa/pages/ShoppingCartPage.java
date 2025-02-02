package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.qa.utils.WebUtils.*;

public class ShoppingCartPage extends BasePage {
    private WebDriver driver;
    @FindBy(xpath = "//a[@href='https://cart.ebay.com']//span[@class='badge']")
    private WebElement text_cartIcon;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getCartProductCount() {
        String count = getText(driver,text_cartIcon);
        BasePage.test.get().info("Found quantity in add to cart: "+count);
        System.out.println("Found quantity in add to cart: "+count);
        return count;
    }
}
