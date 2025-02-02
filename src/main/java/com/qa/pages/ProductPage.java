package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.qa.utils.WebUtils.*;

public class ProductPage extends BasePage {

    private WebDriver driver;
    @FindBy(xpath = "//div[@id='mainContent']//h1")
    private WebElement text_productTitle;

    @FindBy(xpath = "//span[text()='Add to cart']")
    private WebElement button_addToCart;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getProductTitle() {
        String productTitle = getText(driver,text_productTitle);
        BasePage.test.get().info("Product opened: "+productTitle);
        System.out.println("Product opened: "+productTitle);
        return productTitle;
    }

    public void addProductToCart() {
        click(driver,button_addToCart);
        BasePage.test.get().info("Adding 1 quantity to cart");
        System.out.println("Adding 1 quantity to cart");
    }

}
