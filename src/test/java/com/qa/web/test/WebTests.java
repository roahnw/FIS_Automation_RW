package com.qa.web.test;

import static com.qa.utils.WebUtils.*;
import com.qa.pages.HomePage;
import com.qa.pages.ProductPage;
import com.qa.pages.ProductSearchPage;
import com.qa.pages.ShoppingCartPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class WebTests extends BaseTest{

    @Test(description = "Verify item can be added to Cart", enabled = false)
    public void verifyProductAddedToCart() {

        String searchProduct = "book";
        String expectedCartCount = "1";

        homePage = new HomePage(getDriver());
        homePage.searchProduct(searchProduct);

        productSearchPage = new ProductSearchPage(getDriver());
        String actualProductText = productSearchPage.getFirstProductText();
        productSearchPage.selectFirstProduct();

        productPage = new ProductPage(getDriver());
        assertEquals(actualProductText,productPage.getProductTitle());
        productPage.addProductToCart();

        shoppingCartPage = new ShoppingCartPage(getDriver());
        assertEquals(shoppingCartPage.getCartProductCount(),expectedCartCount);

        }

    @Test(description = "Verify title of page")
    public void verifyEbayMotors() {

        homePage = new HomePage(getDriver());
        homePage.gotoMotors();
        String actualTitle = homePage.getMotorPageTitle();
        Assert.assertEquals(actualTitle,"eBay Motors","Page title does not matched");
    }

}
