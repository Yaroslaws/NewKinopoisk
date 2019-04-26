package utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.fail;

public class ChromeDriverUtil {

    public static WebDriver startCromeDriver() {
        ChromeDriver driver = null;
        try {

            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\ysenko\\IdeaProjects\\NewKinopoisk\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            return driver;
        } catch (Exception e) {
            driver.quit();
            fail();
        }
        return null;
    }
}
