import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class iQuantileHome {
    private final WebDriver driver;

    public iQuantileHome (WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome () {
        String expectedHomeUrl = "https://www.iquantile.com/home";
        WebElement element = driver.findElement (By.xpath ("/html/body/header/div[1]/a"));
        String actualHomeUrl = element.getAttribute ("href");
        System.out.println ("Home Page URL: " + actualHomeUrl);
        driver.get (actualHomeUrl);
        if (actualHomeUrl.equals (expectedHomeUrl)) {
            System.out.println ("Home Page is verified !");
        } else {
            System.out.println ("Something went wrong with Homepage !");
        }
    }
}
