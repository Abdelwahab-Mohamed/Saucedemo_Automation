import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class navigationBar {

    public WebElement navbar(){

        return Main.driver.findElements(By.className("nav")).get(1);
    }
}
