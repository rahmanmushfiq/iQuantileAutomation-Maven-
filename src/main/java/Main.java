import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Scanner;

public class Main {
    private static WebDriver driver;
    private static String browser;
    private String baseUrl = "https://www.iquantile.com";
    private static String projectPath = System.getProperty ("user.dir");
    private static String OperatingSystem = System.getProperty ("os.name");
    /**
     * For Windows Operating System
     */
    private static final String WINCHROMEDRIVERPATH = projectPath + "\\drivers\\chromedriver\\chromedriver.exe";
    private static final String WINGECKODRIVERPATH = projectPath + "\\drivers\\firefoxdriver\\geckodriver.exe";
    private static final String WINEDGEDRIVERPATH = projectPath + "\\drivers\\edgedriver\\MicrosoftWebDriver.exe";

    /**
     * For Linux Operating System
     */
    private static final String LINUXCHROMEDRIVERPATH = projectPath + "/drivers/chromedriver/chromedriver";
    private static final String LINUXGECKODRIVERPATH = projectPath + "/drivers/firefoxdriver/geckodriver";

    public static void main (String[] args) {

        while (true) {
            setBrowser ();
            if (setBrowserConfig ()) {
                break;
            }
        }
        BrokenLinks brokenLinks = new BrokenLinks (driver);
        brokenLinks.findAllLinks ();
        brokenLinks.checkLinks ();
        new iQuantileHome (driver).navigateToHome ();
        new iQuantileServices (driver).navigateToServices ();
        new iQuantilePortfolio (driver).navigateToPortfolio ();
        new iQuantileAbout (driver).navigateToAbout ();
        new iQuantileContact (driver).navigateToContactPage ();
        new iQuantileCareer (driver).navigateToCareerPage ();
        tearDown ();
    }

    public static void setBrowser () {
        Scanner input = new Scanner (System.in);
        System.out.println ("Which browser do you want to use ? ");
        browser = input.nextLine ();
    }

    public static boolean setBrowserConfig () {
        if (OperatingSystem.equalsIgnoreCase ("Windows")) {
            try {
                if (browser.equalsIgnoreCase ("Chrome")) {
                    System.setProperty ("webdriver.chrome.driver", WINCHROMEDRIVERPATH);
                    driver = new ChromeDriver ();
                    return true;
                } else if (browser.equalsIgnoreCase ("Headless")) {
                    System.setProperty ("webdriver.chrome.driver", WINCHROMEDRIVERPATH);
                    ChromeOptions options = new ChromeOptions ();
                    options.addArguments ("--headless");
                    driver = new ChromeDriver (options);
                    return true;
                } else if (browser.equalsIgnoreCase ("Edge")) {
                    System.setProperty ("webdriver.edge.driver", WINEDGEDRIVERPATH);
                    driver = new EdgeDriver ();
                    return true;
                } else if (browser.equalsIgnoreCase ("Firefox")) {
                    System.setProperty ("webdriver.gecko.driver", WINGECKODRIVERPATH);
                    driver = new FirefoxDriver ();
                    return true;
                }
            } catch (Exception e) {
                System.out.println ("Exception is: " + e.getMessage ());
                System.out.println ("Cause is: " + e.getCause ());
            }
        } else if (OperatingSystem.equalsIgnoreCase ("Linux")) {
            try {
                if (browser.equalsIgnoreCase ("Chrome")) {
                    System.setProperty ("webdriver.chrome.driver", LINUXCHROMEDRIVERPATH);
                    driver = new ChromeDriver ();
                    return true;
                } else if (browser.equalsIgnoreCase ("Headless")) {
                    System.setProperty ("webdriver.chrome.driver", LINUXCHROMEDRIVERPATH);
                    ChromeOptions options = new ChromeOptions ();
                    options.addArguments ("--headless");
                    driver = new ChromeDriver (options);
                    return true;
                } else if (browser.equalsIgnoreCase ("Firefox")) {
                    System.setProperty ("webdriver.gecko.driver", LINUXGECKODRIVERPATH);
                    driver = new FirefoxDriver ();
                    return true;
                }
            } catch (Exception e) {
                System.out.println ("Exception is: " + e.getMessage ());
                System.out.println ("Cause is: " + e.getCause ());
            }
        }
        System.out.println ("You have entered an invalid browser name !");
        return false;
    }

    /*
     * quit the browser and clear the session
     */

    public static void tearDown () {
        System.out.println (browser + " is Closing....");
        if (browser.equalsIgnoreCase ("Firefox")) {
            driver.quit ();
        } else {
            driver.close ();
            driver.quit ();
        }
    }
}
