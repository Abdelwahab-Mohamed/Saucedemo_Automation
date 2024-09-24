import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class cart {
 public WebElement viewCart() {

     return Main.driver.findElement(By.className("shopping_cart_link"));
 }

 public WebElement deleteProd() {
     return Main.driver.findElement(By.className("cart_quantity_delete"));
 }

 public WebElement addTocart() {

      return  Main.driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
 }

public WebElement continueShopping(){
     return Main.driver.findElement(By.id("continue-shopping"));
}

public WebElement cartCount(){

     return Main.driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a/span"));

}
public WebElement addCart() {

    return  Main.driver.findElement(By.className("btn_primary"));
}
}
