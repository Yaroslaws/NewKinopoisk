package pages;

import org.openqa.selenium.By;


public class MainPage {
    public static By avatar = By.xpath("//span[contains(@class,'avatar')]");
    public static By userName = By.xpath("//a/div[contains(@class,'user')]");
    public static By searchFilms = By.xpath("//a[@data-tooltip='Расширенный поиск']");







//    public static void nav(WebDriver driver, String buttonName) {
//        try {
//            Actions action = new Actions(driver);
//            WebElement element = (new WebDriverWait(driver, 1, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//nav//a[.='" + buttonName + "']")));
//            action.moveToElement(element);
//            element = driver.findElement(By.xpath("//a[.='Навигатор по фильмам']"));
//            element.click();
//        } catch (Exception e) {
//            fail();
//            System.out.println("no");
//        }
//
//    }

     public static void provTitle(String textEquals,String text){
         if(textEquals.equals(text))
         {
             System.out.println("ok");

         }
         else
         {
             System.out.println("none");

         }

     }



}
