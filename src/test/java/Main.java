import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static ChromeDriver driver;
    loginloc logloc = new loginloc();
    cart cartloc = new cart();
    products prodloc = new products();
    LogOut signout = new LogOut();

    @BeforeMethod
    void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

    }
@AfterMethod
void close() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
}
    @Test
        // Valid Ceredentials Scenario
    void LoginTC_1() {
        driver.get("https://www.saucedemo.com/");

        logloc.username().sendKeys("standard_user");
        logloc.Password().sendKeys("secret_sauce");
        logloc.button().click();
        WebDriverWait expWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        expWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_link")));
        // WebElement t = driver.findElement(By.className("shopping_cart_link"));


        Assert.assertTrue(driver.findElement(By.className("shopping_cart_link")).isDisplayed());

    }

    @Test
        // Invalid Credentials scenario
    void LoginTC_2() {
        driver.get("https://www.saucedemo.com/");


        logloc.username().sendKeys("standard_user");
        logloc.Password().sendKeys("qwey");
        logloc.button().click();

        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());


    }

    @Test
        // Validate that appropiate error message displayed
    void LoginTC_3() {
        driver.get("https://www.saucedemo.com/");


        logloc.username().sendKeys("standard_user");
        logloc.Password().sendKeys("qwey");
        logloc.button().click();
        WebElement getMsg = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3"));
        String ErrorMsg = "Epic sadface: Username and password do not match any user in this service";
        String Actual = getMsg.getText();

        Assert.assertEquals(Actual, ErrorMsg);


    }

    @Test
        //add product to cart and verify that cart is updated
    void CartTC_addproduct() throws InterruptedException {

        driver.get("https://www.saucedemo.com/");

        logloc.username().sendKeys("standard_user");
        logloc.Password().sendKeys("secret_sauce");
        logloc.button().click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        cartloc.viewCart().click();

        Assert.assertTrue(driver.findElement(By.id("item_0_title_link")).isDisplayed());

        cartloc.continueShopping().click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        cartloc.viewCart().click();
        Assert.assertTrue(driver.findElement(By.id("item_1_title_link")).isDisplayed());


    }

    @Test
    void CartTC_2() throws InterruptedException {

        driver.get("https://www.saucedemo.com/");

        logloc.username().sendKeys("standard_user");
        logloc.Password().sendKeys("secret_sauce");
        logloc.button().click();

        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        cartloc.viewCart().click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.id("item_0_title_link")).isDisplayed());
        driver.findElement(By.id("remove-sauce-labs-bike-light")).click();
        Assert.assertFalse(driver.findElement(By.id("item_0_title_link")).isDisplayed());

    }

    @Test
    void CartTC_3() {

        driver.get("https://www.saucedemo.com/");

        logloc.username().sendKeys("standard_user");
        logloc.Password().sendKeys("secret_sauce");
        logloc.button().click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        cartloc.cartCount();
        String count = "1";
        String actualCount = cartloc.cartCount().getText();
        Assert.assertEquals(actualCount, count);
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        cartloc.cartCount();
        String count2 = "2";
        actualCount = cartloc.cartCount().getText();

        Assert.assertEquals(actualCount, count2);


    }

    @Test
    void CartTC_4() {

        driver.get("https://www.saucedemo.com/");

        logloc.username().sendKeys("standard_user");
        logloc.Password().sendKeys("secret_sauce");
        logloc.button().click();
        cartloc.addCart().click();
        String actualCount;

        for (int i = 1; i < 4; i++) {
            cartloc.cartCount();

            actualCount = cartloc.cartCount().getText();
            int q = Integer.parseInt(actualCount);

            Assert.assertEquals(q, i);

            cartloc.addCart().click();
            System.out.println("Round no :" + i);

        }


    }

    @Test
    void prodTC_VerifyProducts() {

        driver.get("https://www.saucedemo.com/");

        logloc.username().sendKeys("standard_user");
        logloc.Password().sendKeys("secret_sauce");
        logloc.button().click();
        prodloc.verifyProducts();


    }

    @Test
    void productTC_SortLowToHigh(){

        driver.get("https://www.saucedemo.com/");

        logloc.username().sendKeys("standard_user");
        logloc.Password().sendKeys("secret_sauce");
        logloc.button().click();
        prodloc.sort();
    }

    @Test
    void productTC_SortHighToLow(){
        driver.get("https://www.saucedemo.com/");

        logloc.username().sendKeys("standard_user");
        logloc.Password().sendKeys("secret_sauce");
        logloc.button().click();
        prodloc.sortDesc();

    }

    @Test
    void logoutTC_1() {
        driver.get("https://www.saucedemo.com/");

        logloc.username().sendKeys("standard_user");
        logloc.Password().sendKeys("secret_sauce");
        logloc.button().click();
        signout.menu().click();
        signout.logoutButton().click();
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());

    }
}