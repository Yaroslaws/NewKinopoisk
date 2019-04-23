package pages;

import org.openqa.selenium.By;


public class MainPage {
    public static By avatar = By.xpath("//span[contains(@class,'avatar')]");
    public static By userName = By.xpath("//a/div[contains(@class,'user')]");
    public static By searchFilms = By.xpath("//a[@data-tooltip='Расширенный поиск']");
    public static By buttonEntrance = By.xpath("//button[.='Войти']");
    public static By buttonExit = By.xpath("//div[.='Выйти']");




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
