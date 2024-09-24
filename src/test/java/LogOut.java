import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LogOut {

    public WebElement menu (){

        return Main.driver.findElement(By.id("react-burger-menu-btn"));
    }
    public WebElement logoutButton(){
        return Main.driver.findElement(By.id("logout_sidebar_link"));
    }
}
