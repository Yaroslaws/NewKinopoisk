package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitElementUtill {
    //public static WebDriver driver = ChromeDriverUtil.startCromeDriver();
    public static WebElement element;
    public  static WebElement elementCopy;



    public static  WebElement sendKeys(WebDriver driver,By locator, String text) {
        //WebElement element=null;
        try {
            element =  (new WebDriverWait(driver, 3, 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            element.clear();
            element.sendKeys(text);
        }
        catch (Exception e)
        {
            System.out.println("Не нашел локатор" + locator);
        }
        return element;
    }

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



    public static  void selectElement(WebDriver driver,By locator, String text) {
        try {
            WebElement elementCopy = (new WebDriverWait(driver, 3, 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            elementCopy.click();
            Thread.sleep(2000);
            WebElement element = (new WebDriverWait(driver, 3, 1000))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(locator+"/option[.='" + text + "']")));
            element.click();

        }
        catch (NullPointerException ex){
            System.out.println("Не нашел локатор" + locator + "/option[.='" + text + "']");
        }
        catch (Exception e)
        {
            System.out.println("Другая ошибка");
            e.printStackTrace();
        }
    }

    public static  void intervalFilmsRating(WebDriver driver,String MaxMin , String Year) {
        try {
            element =  (new WebDriverWait(driver, 3, 1000))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//select[contains(@name,'" + MaxMin + "')]/option[.='" + Year + "']")));
            element.click();
        }
        catch (Exception e)
        {
            System.out.println("Не нашел локатор");
        }
    }


    public static  void countryList(WebDriver driver,String text) {
        try {
            element =  (new WebDriverWait(driver, 3, 1000))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id= 'countryList']//input[@data-name = '" + text + "']")));
            element.click();
        }
        catch (Exception e)
        {
            System.out.println("Не нашел локатор" + text);
        }
    }

    public static  void genreList(WebDriver driver,String text) {
        try {
            element =  (new WebDriverWait(driver, 3, 1000))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id= 'genreList']//input[@data-name = '" + text + "']")));
            element.click();
        }
        catch (Exception e)
        {
            System.out.println("Не нашел локатор" + text);
        }
    }



}
