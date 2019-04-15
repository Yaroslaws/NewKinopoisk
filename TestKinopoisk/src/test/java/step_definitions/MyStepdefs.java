package step_definitions;


import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Тогда;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Login;
import pages.MainPage;
import utils.ChromeDriverUtil;
import java.util.concurrent.TimeUnit;

import static pages.MainPage.provTitle;

public class MyStepdefs {
    public static WebDriver driver;


    @Дано("^пользователь открывает сайт \"([^\"]*)\"$")
    public void пользовательОткрываетСайт(String arg1) throws Throwable {
        driver = ChromeDriverUtil.startCromeDriver();
        driver.get(arg1);
    }




    @Тогда("^проверяем пользователя$")
    public void проверяемПользователя() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String text;
        MainPage mainPage = new MainPage();
        WebElement element = driver.findElement(mainPage.avatar);
        element.click();
        text = driver.findElement(mainPage.userName).getText();
        if(text.equals("senckoya")){
            System.out.println("ok");
        }
    }


    @Тогда("^перехоим в разддел навигатор фильмов$")
    public void перехоимВРаздделНавигаторФильмов() throws Throwable {
        MainPage mainPage = new MainPage();
        Actions action = new Actions(driver);
        WebElement element = (new WebDriverWait(driver, 1, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//nav//a[.='Фильмы']")));
        action.moveToElement(element).build().perform();
        System.out.println("44");
        element = (new WebDriverWait(driver, 1, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='Навигатор по фильмам']")));
        element.click();
    }


    @Тогда("^проверим тайтл \"([^\"]*)\"$")
    public void проверимТайтл(String arg1) throws Throwable {
        (new WebDriverWait(driver, 5, 1000)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//head/title")));
        String element = driver.getTitle();
        System.out.println(element);
        provTitle(element,arg1);
    }





//старая версия проверки тайтла
//    @Тогда("^Проверим тайтл Главная$")
//    public void проверимТайтлГлавная() throws Throwable {
//        (new WebDriverWait(driver, 5, 1000)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//head/title")));
//        String element = driver.getTitle();
//        System.out.println(element);
//        provTitle(element,"КиноПоиск — Все фильмы планеты");
//    }





    @Тогда("^логинимся на кинопоиске$")
    @Step("логинимся на кинопоиске")
    public void логинимсяНаКинопоиске() throws Throwable {
                try {
                    WebElement element = driver.findElement(By.xpath("//button[.='Войти']"));
                    element.click();
                    Login login = new Login();
                    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    element = driver.findElement(login.login);
                    element.sendKeys("senckoya");
                    element = driver.findElement(login.buttonEnter);
                    element.click();
                    element = (new WebDriverWait(driver, 1, 1000)).until(ExpectedConditions.elementToBeClickable(login.password));
                    element = driver.findElement(login.password);
                    System.out.println(element.isDisplayed());
                    element.sendKeys("as009007");
                    element = driver.findElement(login.buttonEnter);
                    element.click();

                }catch (TimeoutException e){
                    System.out.println(driver.getCurrentUrl());
                    e.printStackTrace();

                }
    }
}
