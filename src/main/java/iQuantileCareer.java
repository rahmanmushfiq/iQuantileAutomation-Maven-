import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class iQuantileCareer {

    private final WebDriver driver;

    public iQuantileCareer (WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCareerPage () {
        /*
          go to career page
         */
        String expectedCareerUrl = "https://www.iquantile.com/career";
//        WebElement element = driver.findElement (By.linkText ("WORK WITH US"));
        WebElement element = driver.findElement (By.xpath ("/html/body/header/nav/ul/li[4]/a"));
        System.out.print ("Career Page URL: ");
        String actualCareerUrl = element.getAttribute ("href");
        System.out.println (actualCareerUrl);
        driver.get (actualCareerUrl);
        if (actualCareerUrl.equals (expectedCareerUrl)) {
            System.out.println ("Career page verified !");
        } else {
            System.out.println ("Something went wrong with Career Page !");
        }
    }
}
