import io.cucumber.java.it.Ma;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class products {

    public By imgLocator(int index) {

        return By.xpath("(//div[@class='inventory_item'])[" + index + "]//div[@class='inventory_item_img']//img");
    }

    public By nameLocator(int index) {
        return By.xpath("(//div[@class='inventory_item'])[" + index + "]//div[@data-test='inventory-item-name']");
    }

    public By priceLocator(int index) {

        return By.xpath("(//div[@class='inventory_item'])[" + index + "]//div[@data-test='inventory-item-price']");
    }

    public By descriptionLocator(int index) {
        return By.xpath("(//div[@class='inventory_item'])[" + index + "]//div[@data-test='inventory-item-desc']\n");
    }

    public List<WebElement> productList() {

        return Main.driver.findElements(By.xpath("//div[@class='inventory_item']"));
    }

    public int prodCount() {
        int c = productList().size();
        return c;
    }

    public void verifyProducts() {

        for (int i = 0; i < prodCount(); i++) {
            String prodDescription = Main.driver.findElement(descriptionLocator(i+1)).getText();
            System.out.println("prodDescription :"+i +" " + prodDescription );
            String prodimg = Main.driver.findElement(imgLocator(i+1)).getAttribute("src");
            System.out.println("image :"+i +" " + prodimg );
            String prodPrice = Main.driver.findElement(priceLocator(i+1)).getText();
            System.out.println("Price :"+i +" " + prodPrice );
            String prodName = Main.driver.findElement(nameLocator(i+1)).getText();
            System.out.println("Name :"+i +" " + prodName );
            Assert.assertNotEquals(prodDescription,"");
            Assert.assertNotEquals(prodimg,"");
            Assert.assertNotEquals(prodPrice,"");
            Assert.assertNotEquals(prodName,"");
        }
    }
    public void selectsortLowtoHigh(){
        WebElement ddl = Main.driver.findElement(By.className("product_sort_container"));
        Select dropdownselect = new Select(ddl);
        dropdownselect.selectByIndex(2);
    }
    public void selectsortHightoLow(){
        WebElement ddl = Main.driver.findElement(By.className("product_sort_container"));
        Select dropdownselect = new Select(ddl);
        dropdownselect.selectByIndex(3);
    }
    public List<WebElement> productsPrice (){

        return Main.driver.findElements(By.className("inventory_item_price"));

    }
    public void sort (){

         List<Double> beforesort = new ArrayList<>();
        for (WebElement p:productsPrice()) {
            beforesort.add(Double.valueOf(p.getText().replace("$","")));


        }
        Collections.sort(beforesort);
        selectsortLowtoHigh();
        List<Double> aftersort = new ArrayList<>();
        for (WebElement p:productsPrice()) {
            aftersort.add(Double.valueOf(p.getText().replace("$","")));
        }
        Assert.assertEquals(beforesort,aftersort);
    }
    public void sortDesc (){

        List<Double> beforesort = new ArrayList<>();
        for (WebElement p:productsPrice()) {
            beforesort.add(Double.valueOf(p.getText().replace("$","")));


        }
        Collections.sort(beforesort);
        Collections.reverse(beforesort);
        selectsortHightoLow();
        List<Double> aftersort = new ArrayList<>();
        for (WebElement p:productsPrice()) {
            aftersort.add(Double.valueOf(p.getText().replace("$","")));
        }
        Assert.assertEquals(beforesort,aftersort);

    }

}
