import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class iQuantileServices {
    private final WebDriver driver;

    public iQuantileServices (WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToServices () {
        String expectedServicesUrl = "https://www.iquantile.com/index.php#servicez";
        WebElement element = driver.findElement (By.xpath ("/html/body/header/nav/ul/li[1]/a"));
        String actualServicesUrl = element.getAttribute ("href");
        System.out.println ("Services Page URL: " + actualServicesUrl);
        driver.get (actualServicesUrl);
        if (actualServicesUrl.equals (expectedServicesUrl)) {
            System.out.println ("Service Page is verified !");
        } else {
            System.out.println ("Something went wrong with Service Page !");
        }
    }
}
