package step_definitions;



import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.То;
import cucumber.api.java.ru.Тогда;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.ITestResult;
import pages.Login;

import pages.FilmsRating;
import pages.MainPage;
import pages.SearchFilms;


import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import utils.ChromeDriverUtil;


import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.WaitElementUtill;



import static pages.MainPage.provTitle;
import static utils.WaitElementUtill.takeScreenShot;

public class MyStepdefs  {
    public static WebDriver driver;



    @Before("@withdrawal")
    public void prepareData() {
        //подготовить данные

        System.out.println("This will run");
    }




    @After("@withdrawal")
    public void clearData(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenShot(driver);
        }
        driver.close();


    }



    @Дано("^пользователь открывает сайт \"([^\"]*)\"$")
    public void пользовательОткрываетСайт(String arg1) throws Throwable {
        driver = ChromeDriverUtil.startCromeDriver();
        driver.get(arg1);
    }


    @Тогда("^выход из аккаунта$")
    public void выходИзАккаунта() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://www.kinopoisk.ru/");
        Boolean isPresent = driver.findElements(MainPage.buttonEntrance).size() > 0;
        if(isPresent){
            System.out.println("Не нужно выходить");
        }
        else
        {
            WaitElementUtill.waitElement(driver,MainPage.avatar);
            WaitElementUtill.waitElement(driver,MainPage.buttonExit);
        }


    }

    @То("^Проверить, что в результатах поиска отображен массив фильмов снятых в \"([^\"]*)\" в жанре \"([^\"]*)\" с рейтингом  более \"([^\"]*)\"  и рейтингом IMDb более \"([^\"]*)\"$")
    public void проверитьЧтоВРезультатахПоискаОтображенМассивФильмовСнятыхВВЖанреСРейтингомБолееИРейтингомIMDbБолее(String arg1, String arg2, String arg3, String arg4) throws Throwable {
        int i=1;

        while(i<4) {

            String infoFilm = (new WebDriverWait(driver, 4, 1000))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'itemList']/div["+i+"]//div[@class = 'info']/span[@class = 'gray_text'][1]"))).getText();
            String infoReating = (new WebDriverWait(driver, 4, 1000))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'itemList']/div["+i+"]/div[@class = 'numVote  ratingGreenBG']/span"))).getText();
            String infoImdb = (new WebDriverWait(driver, 4, 1000))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'itemList']/div["+i+"]//div[@class= 'imdb']"))).getText();

            //правило regexp на введенные данные
            String regex1 =arg1;
            String regex2 = arg2;
            String regex3 = "[^.]*";
            String regex4 = "[0-9]";

            int num;
            Matcher matcher1 = Pattern.compile(regex1).matcher(infoFilm);
            Matcher matcher2 = Pattern.compile(regex2).matcher(infoFilm);
            Matcher matcher3 = Pattern.compile(regex3).matcher(infoReating);
            Matcher matcher4 = Pattern.compile(regex4).matcher(infoImdb);

            //проверка введенных аргументов
            Assert.assertEquals(matcher1.find(), true);
            Assert.assertEquals(matcher2.find(), true);

            if (matcher3.find()) {
                num = Integer.parseInt(matcher3.group());
                if(num >= 7)
                    Assert.assertEquals(true, true);
            }
           if (matcher4.find()) {
               num = Integer.parseInt(matcher4.group());
               if(num >= 7)
                   Assert.assertEquals(true, true);
           }
            i++;
        }
    }



    @Тогда("^Нажать кнопку «показать фильмы»$")
    public void нажатьКнопкуПоказатьФильмы() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WaitElementUtill.waitElement(driver, FilmsRating.showFilmButton);

    }




    @Тогда("^ищем по параметрам$")
    public void ищемПоПараметрам(DataTable arg1) throws Throwable {



        String key, value = "";
        List<Map<String, String>> table = arg1.asMaps(String.class, String.class);

        WaitElementUtill.waitElement(driver, FilmsRating.country);
        WaitElementUtill.countryList(driver, table.get(1).get("col2"));
        WaitElementUtill.waitElement(driver, FilmsRating.genre);
        WaitElementUtill.genreList(driver, "комедия");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var get = document.getElementsByClassName('narrow year_select_interval');\n" +
                String.format("get[0].value = \"%s\";", table.get(2).get("col2")) +
                String.format("get[1].value = \"%s\";", table.get(3).get("col2")));
        WaitElementUtill.sendKeys(driver, FilmsRating.ratingFilmMin,table.get(4).get("col2"));
        WaitElementUtill.sendKeys(driver, FilmsRating.IMDbMin,table.get(5).get("col2"));
        WaitElementUtill.sendKeys(driver, FilmsRating.moveCriticMin,table.get(6).get("col2"));
        WaitElementUtill.sendKeys(driver, FilmsRating.revieProcentMax,"100");
        WaitElementUtill.sendKeys(driver, FilmsRating.revieProcentMin,table.get(8).get("col2"));
        js.executeScript("var get = document.getElementById('min_vote');\n" + "get.value =\"2000\";");
        WaitElementUtill.waitElement(driver,FilmsRating.budgetMin);
        WaitElementUtill.selectElement(driver,table.get(9).get("col2"));



        js.executeScript("var get = document.getElementsByClassName('narrow');\n" +
                String.format("get[3].value = \"%s\";", table.get(10).get("col2"))+
                String.format("get[4].value = \"%s\";", table.get(11).get("col2")));





    }


    @Тогда("^Проверка на количество фильмов \"([^\"]*)\"$")
    public void проверкаНаКоличествоФильмов(String arg1) throws Throwable {

        WebElement  element = (new WebDriverWait(driver, 4, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@onclick,'navigator.loadResult()')]")));
        String str = element.getText();
        String regex ="(\\d+)";
        String num = null;
        Matcher matcher = Pattern.compile(regex).matcher(str);
        while (matcher.find( ))
        {
            num = matcher.group();
        }
        Assert.assertEquals(num, arg1);


    }


    @Step("проверка на конкретного пользователя")
    @Тогда("^проверяем пользователя$")
    public void проверяемПользователя() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String text;
        MainPage mainPage = new MainPage();
        WebElement element = driver.findElement(mainPage.avatar);
        element.click();
        text = driver.findElement(mainPage.userName).getText();
        if(text.equals("senckoya")){
            System.out.println("Пользователь вошел");
        }
    }


    @Тогда("^ищем \"([^\"]*)\" снятый \"([^\"]*)\" с \"([^\"]*)\" по \"([^\"]*)\"$")
    public void ищемСнятыйСПо(String arg1, String arg2, String arg3, String arg4) throws Throwable {
        WebElement element = (new WebDriverWait(driver,1,1000))
                .until(ExpectedConditions.elementToBeClickable(SearchFilms.contentFind));
        element.click();
        WaitElementUtill.waitElement(driver,SearchFilms.selected);
        WaitElementUtill.selectElement(driver, "фильм");
        WaitElementUtill.waitElement(driver,SearchFilms.country);
        WaitElementUtill.selectElement(driver, "США");
        WaitElementUtill.waitElement(driver,SearchFilms.fromYear);
        WaitElementUtill.selectElement(driver, "2000");
        WaitElementUtill.waitElement(driver,SearchFilms.toYear);
        WaitElementUtill.selectElement(driver, "1998");
        WaitElementUtill.waitElement(driver,SearchFilms.buttonSearch);
    }


    @Тогда("^перехоим в разддел навигатор фильмов$")
    public void перехоимВРаздделНавигаторФильмов() throws Throwable {
        MainPage mainPage = new MainPage();
        Actions action = new Actions(driver);
        WebElement element = (new WebDriverWait(driver, 1, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//nav//a[.='Фильмы']")));
        action.moveToElement(element).build().perform();
        element = (new WebDriverWait(driver, 1, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='Навигатор по фильмам']")));
        element.click();
    }

    @Тогда("^открываем расширенный поиск$")
    public void открываеРасширенныйПоиск() throws Throwable {
        MainPage mainPage = new MainPage();
        WebElement element = (new WebDriverWait(driver, 5, 1000)).until(ExpectedConditions.presenceOfElementLocated(MainPage.searchFilms));
        element.click();
    }



    @Тогда("^проверим тайтл \"([^\"]*)\"$")
    public void проверимТайтл(String arg1) throws Throwable {
        (new WebDriverWait(driver, 5, 1000)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//head/title")));
        String element = driver.getTitle();
        System.out.println(element);
        provTitle(element,arg1);
    }



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
