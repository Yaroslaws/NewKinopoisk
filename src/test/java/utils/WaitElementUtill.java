package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitElementUtill {
    //public static WebDriver driver = ChromeDriverUtil.startCromeDriver();
    public static WebElement element;

    public static  WebElement waitElement(WebDriver driver,By locator) {
        //WebElement element=null;
        try {
          element =  (new WebDriverWait(driver, 3, 1000)).until(ExpectedConditions.elementToBeClickable(locator));
           element.click();
        }
        catch (Exception e)
        {
            System.out.println("Не нашел локатор" + locator);
        }
        return element;
    }


    public static  void selectElement(WebDriver driver,String text) {
        //WebElement element=null;
        try {
            element =  (new WebDriverWait(driver, 3, 1000))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//select/option[.='" + text + "']")));
            element.click();
        }
        catch (Exception e)
        {
            System.out.println("Не нашел локатор" + text);
        }
    }

}
