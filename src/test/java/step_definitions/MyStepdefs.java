package step_definitions;


import cucumber.api.java.ru.ƒано;
import cucumber.api.java.ru.“о;
import cucumber.api.java.ru.“огда;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Login;

import pages.FilmsRating;
import pages.MainPage;
import pages.SearchFilms;
import utils.ChromeDriverUtil;


import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.WaitElementUtill;


import static org.junit.Assert.assertEquals;
import static pages.MainPage.provTitle;

public class MyStepdefs {
    public static WebDriver driver;


    @ƒано("^пользователь открывает сайт \"([^\"]*)\"$")
    public void пользовательќткрывает—айт(String arg1) throws Throwable {
        driver = ChromeDriverUtil.startCromeDriver();
        driver.get(arg1);
    }

    @“огда("^выход из аккаунта$")
    public void выход»зјккаунта() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://www.kinopoisk.ru/");
        Boolean isPresent = driver.findElements(MainPage.buttonEntrance).size() > 0;
        if(isPresent){
            System.out.println("Ќе нужно выходить");
        }
        else
        {
            WaitElementUtill.waitElement(driver,MainPage.avatar);
            WaitElementUtill.waitElement(driver,MainPage.buttonExit);
        }


    }

    @“о("^ѕроверить, что в результатах поиска отображен массив фильмов сн€тых в \"([^\"]*)\" в жанре \"([^\"]*)\" с рейтингом  более \"([^\"]*)\"  и рейтингом IMDb более \"([^\"]*)\"$")
    public void проверить„то¬–езультатахѕоискаќтображенћассив‘ильмов—н€тых¬¬∆анре—–ейтингомЅолее»–ейтингомIMDbЅолее(String arg1, String arg2, String arg3, String arg4) throws Throwable {
        By info = By.xpath("//div[@id = 'itemList']/div[4]//div[@class = 'info']/span[@class = 'gray_text'][1]");
        By reating = By.xpath("//div[@id = 'itemList']//div[@class = 'numVote  ratingGreenBG']/span");
        By imdb = By.xpath("//div[@id = 'itemList']//div[@class = 'imdb']");
        int i=1;
        while(i<5) {

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
            assertEquals(matcher1.find(), true);
            assertEquals(matcher2.find(), true);

            if (matcher3.find()) {
                num = Integer.parseInt(matcher3.group());
                if(num >= 7)
                    assertEquals(true, true);
            }
           if (matcher4.find()) {
               num = Integer.parseInt(matcher4.group());
               if(num >= 7)
                   assertEquals(true, true);
           }
            i++;
        }
    }



    @“огда("^Ќажать кнопку Ђпоказать фильмыї$")
    public void нажать нопкуѕоказать‘ильмы() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WaitElementUtill.waitElement(driver, FilmsRating.showFilmButton);

    }




    @“огда("^ищем комедии сн€тые в \"([^\"]*)\" с \"([^\"]*)\" по \"([^\"]*)\" рейтинг \"([^\"]*)\" ibm \"([^\"]*)\" рейтинг критиков \"([^\"]*)\" положительных рецензий от \"([^\"]*)\" до \"([^\"]*)\" Ѕюджет фильма от \"([^\"]*)\" до \"([^\"]*)\" миллионов \\$\\.  ассовые сборы от \"([^\"]*)\" млн \\$ в —Ўј$")
    public void ищем омедии—н€тые¬—ѕо–ейтингIbm–ейтинг ритиковѕоложительных–ецензийќтƒоЅюджет‘ильмаќтƒоћиллионов$ ассовые—борыќтћлн$¬—Ўј(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11) throws Throwable{
        WaitElementUtill.waitElement(driver, FilmsRating.country);
        WaitElementUtill.countryList(driver, arg1);
        WaitElementUtill.waitElement(driver, FilmsRating.genre);
        WaitElementUtill.genreList(driver, "комеди€");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var get = document.getElementsByClassName('narrow year_select_interval');\n" +
                "get[0].value = \"1998\";\n" +
                "get[1].value = \"2000\";\n");
        WaitElementUtill.sendKeys(driver, FilmsRating.ratingFilmMin,arg4);
        WaitElementUtill.sendKeys(driver, FilmsRating.IMDbMin,arg5);
        WaitElementUtill.sendKeys(driver, FilmsRating.movie—riticMin,arg6);
        WaitElementUtill.sendKeys(driver, FilmsRating.revieProcentMax,arg8);
        WaitElementUtill.sendKeys(driver, FilmsRating.revieProcentMin,arg7);
        js.executeScript("var get = document.getElementById('min_vote');\n" +
                "get.value =\"2000\";");
        WaitElementUtill.waitElement(driver,FilmsRating.budgetMin);
        WaitElementUtill.selectElement(driver,arg9);
        js.executeScript("var get = document.getElementsByClassName('narrow');\n" +
                " get[3].value = '100';"+
                "get[4].value = '25';");

//потом использую
//        WaitElementUtill.waitElement(driver,FilmsRating.searchButton);

    }


    @“огда("^ѕроверка на количество фильмов \"([^\"]*)\"$")
    public void проверкаЌа оличество‘ильмов(String arg1) throws Throwable {

        WebElement  element = (new WebDriverWait(driver, 4, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@onclick,'navigator.loadResult()')]")));
        String str = element.getText();
        String regex ="(\\d+)";
        String num = null;
        Matcher matcher = Pattern.compile(regex).matcher(str);
        while (matcher.find( ))
        {
            num = matcher.group();
        }
        assertEquals(num, arg1);
    }


    @“огда("^провер€ем пользовател€$")
    public void провер€емѕользовател€() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String text;
        MainPage mainPage = new MainPage();
        WebElement element = driver.findElement(mainPage.avatar);
        element.click();
        text = driver.findElement(mainPage.userName).getText();
        if(text.equals("senckoya")){
            System.out.println("ѕользователь вошел");
        }
    }


    @“огда("^ищем \"([^\"]*)\" сн€тый \"([^\"]*)\" с \"([^\"]*)\" по \"([^\"]*)\"$")
    public void ищем—н€тый—ѕо(String arg1, String arg2, String arg3, String arg4) throws Throwable {
        WebElement element = (new WebDriverWait(driver,1,1000))
                .until(ExpectedConditions.elementToBeClickable(SearchFilms.contentFind));
        element.click();
        WaitElementUtill.waitElement(driver,SearchFilms.selected);
        WaitElementUtill.selectElement(driver, "фильм");
        WaitElementUtill.waitElement(driver,SearchFilms.country);
        WaitElementUtill.selectElement(driver, "—Ўј");
        WaitElementUtill.waitElement(driver,SearchFilms.fromYear);
        WaitElementUtill.selectElement(driver, "2000");
        WaitElementUtill.waitElement(driver,SearchFilms.toYear);
        WaitElementUtill.selectElement(driver, "1998");
        WaitElementUtill.waitElement(driver,SearchFilms.buttonSearch);





    }


    @“огда("^перехоим в разддел навигатор фильмов$")
    public void перехоим¬–аздделЌавигатор‘ильмов() throws Throwable {
        MainPage mainPage = new MainPage();
        Actions action = new Actions(driver);
        WebElement element = (new WebDriverWait(driver, 1, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//nav//a[.='‘ильмы']")));
        action.moveToElement(element).build().perform();
        element = (new WebDriverWait(driver, 1, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='Ќавигатор по фильмам']")));
        element.click();
    }

    @“огда("^открываем расширенный поиск$")
    public void открывае–асширенныйѕоиск() throws Throwable {
        MainPage mainPage = new MainPage();
        WebElement element = (new WebDriverWait(driver, 5, 1000)).until(ExpectedConditions.presenceOfElementLocated(MainPage.searchFilms));
        element.click();
    }



    @“огда("^проверим тайтл \"([^\"]*)\"$")
    public void проверим“айтл(String arg1) throws Throwable {
        (new WebDriverWait(driver, 5, 1000)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//head/title")));
        String element = driver.getTitle();
        System.out.println(element);
        provTitle(element,arg1);
    }





//стара€ верси€ проверки тайтла
//    @“огда("^ѕроверим тайтл √лавна€$")
//    public void проверим“айтл√лавна€() throws Throwable {
//        (new WebDriverWait(driver, 5, 1000)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//head/title")));
//        String element = driver.getTitle();
//        System.out.println(element);
//        provTitle(element," иноѕоиск Ч ¬се фильмы планеты");
//    }





    @“огда("^логинимс€ на кинопоиске$")
    @Step("логинимс€ на кинопоиске")
    public void логинимс€Ќа инопоиске() throws Throwable {
                try {
                    WebElement element = driver.findElement(By.xpath("//button[.='¬ойти']"));
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
