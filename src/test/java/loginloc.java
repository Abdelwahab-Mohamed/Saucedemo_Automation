import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class loginloc {
public WebElement username () {
   return Main.driver.findElement(By.id("user-name"));

}

public WebElement Password() {
    return Main.driver.findElement(By.id("password"));

}

public WebElement button() {
    return Main.driver.findElement(By.id("login-button"));
}
}
