import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class products {

    public List<WebElement> getProduct() {

        return Main.driver.findElements(By.className("inventory_item_price"));
    }

}
