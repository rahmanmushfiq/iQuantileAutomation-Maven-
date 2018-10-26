import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class BrokenLinks {
    private static WebDriver driver;

    public BrokenLinks (WebDriver driver) {
        BrokenLinks.driver = driver;
    }

    List findAllLinks () throws InterruptedException {
        /*
         * go to iquantile
         */
        driver.manage ().timeouts ().implicitlyWait (10, TimeUnit.SECONDS);
        driver.navigate ().to ("https://www.iquantile.com/");

        /*
         * make a list of anchor and image tag from the page make a final list to check
         * the links
         */
        List<WebElement> elementList;
        List finalList = new ArrayList ();
        elementList = driver.findElements (By.tagName ("a"));
        elementList.addAll (driver.findElements (By.tagName ("img")));
        for (WebElement element : elementList) {
            if (element.getAttribute ("href") != null) {
                finalList.add (element);
            }
        }
        return finalList;
    }

    /*
     * establish a connection to check response
     */
    private String isLinkBroken (URL url) throws Exception {
        String response;
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection ();
        try {
            connection.connect ();
            response = connection.getResponseMessage ();
            connection.disconnect ();
            return response;
        } catch (Exception e) {
            return e.getMessage ();

        }
    }

    /*
     * find all the broken links with execption messages
     */
    void checkLinks () throws InterruptedException {
        List<WebElement> allImages = findAllLinks ();
        System.out.println ("Total Links Found: " + allImages.size ());
        for (WebElement element : allImages) {
            try {
                System.out.println ("URL: " + element.getAttribute ("href") + " -> returned "
                        + isLinkBroken (new URL (element.getAttribute ("href"))));

            } catch (Exception e) {
                System.out.println (
                        "At " + element.getAttribute ("innerHTML") + " Exception occured -&gt; " + e.getMessage ());
            }
        }
    }

}