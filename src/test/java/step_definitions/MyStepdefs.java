package step_definitions;


import cucumber.api.java.ru.����;
import cucumber.api.java.ru.��;
import cucumber.api.java.ru.�����;
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


    @����("^������������ ��������� ���� \"([^\"]*)\"$")
    public void �������������������������(String arg1) throws Throwable {
        driver = ChromeDriverUtil.startCromeDriver();
        driver.get(arg1);
    }

    @�����("^����� �� ��������$")
    public void ���������������() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://www.kinopoisk.ru/");
        Boolean isPresent = driver.findElements(MainPage.buttonEntrance).size() > 0;
        if(isPresent){
            System.out.println("�� ����� ��������");
        }
        else
        {
            WaitElementUtill.waitElement(driver,MainPage.avatar);
            WaitElementUtill.waitElement(driver,MainPage.buttonExit);
        }


    }

    @��("^���������, ��� � ����������� ������ ��������� ������ ������� ������ � \"([^\"]*)\" � ����� \"([^\"]*)\" � ���������  ����� \"([^\"]*)\"  � ��������� IMDb ����� \"([^\"]*)\"$")
    public void ������������������������������������������������������������������������������������������IMDb�����(String arg1, String arg2, String arg3, String arg4) throws Throwable {
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

            //������� regexp �� ��������� ������
            String regex1 =arg1;
            String regex2 = arg2;
            String regex3 = "[^.]*";
            String regex4 = "[0-9]";

            int num;
            Matcher matcher1 = Pattern.compile(regex1).matcher(infoFilm);
            Matcher matcher2 = Pattern.compile(regex2).matcher(infoFilm);
            Matcher matcher3 = Pattern.compile(regex3).matcher(infoReating);
            Matcher matcher4 = Pattern.compile(regex4).matcher(infoImdb);

            //�������� ��������� ����������
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



    @�����("^������ ������ ��������� �������$")
    public void ��������������������������() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WaitElementUtill.waitElement(driver, FilmsRating.showFilmButton);

    }




    @�����("^���� ������� ������ � \"([^\"]*)\" � \"([^\"]*)\" �� \"([^\"]*)\" ������� \"([^\"]*)\" ibm \"([^\"]*)\" ������� �������� \"([^\"]*)\" ������������� �������� �� \"([^\"]*)\" �� \"([^\"]*)\" ������ ������ �� \"([^\"]*)\" �� \"([^\"]*)\" ��������� \\$\\. �������� ����� �� \"([^\"]*)\" ��� \\$ � ���$")
    public void ����������������������������Ibm�����������������������������������������������������������������$������������������$����(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11) throws Throwable{
        WaitElementUtill.waitElement(driver, FilmsRating.country);
        WaitElementUtill.countryList(driver, arg1);
        WaitElementUtill.waitElement(driver, FilmsRating.genre);
        WaitElementUtill.genreList(driver, "�������");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var get = document.getElementsByClassName('narrow year_select_interval');\n" +
                "get[0].value = \"1998\";\n" +
                "get[1].value = \"2000\";\n");
        WaitElementUtill.sendKeys(driver, FilmsRating.ratingFilmMin,arg4);
        WaitElementUtill.sendKeys(driver, FilmsRating.IMDbMin,arg5);
        WaitElementUtill.sendKeys(driver, FilmsRating.movie�riticMin,arg6);
        WaitElementUtill.sendKeys(driver, FilmsRating.revieProcentMax,arg8);
        WaitElementUtill.sendKeys(driver, FilmsRating.revieProcentMin,arg7);
        js.executeScript("var get = document.getElementById('min_vote');\n" +
                "get.value =\"2000\";");
        WaitElementUtill.waitElement(driver,FilmsRating.budgetMin);
        WaitElementUtill.selectElement(driver,arg9);
        js.executeScript("var get = document.getElementsByClassName('narrow');\n" +
                " get[3].value = '100';"+
                "get[4].value = '25';");

//����� ���������
//        WaitElementUtill.waitElement(driver,FilmsRating.searchButton);

    }


    @�����("^�������� �� ���������� ������� \"([^\"]*)\"$")
    public void ���������������������������(String arg1) throws Throwable {

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


    @�����("^��������� ������������$")
    public void ���������������������() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String text;
        MainPage mainPage = new MainPage();
        WebElement element = driver.findElement(mainPage.avatar);
        element.click();
        text = driver.findElement(mainPage.userName).getText();
        if(text.equals("senckoya")){
            System.out.println("������������ �����");
        }
    }


    @�����("^���� \"([^\"]*)\" ������ \"([^\"]*)\" � \"([^\"]*)\" �� \"([^\"]*)\"$")
    public void �������������(String arg1, String arg2, String arg3, String arg4) throws Throwable {
        WebElement element = (new WebDriverWait(driver,1,1000))
                .until(ExpectedConditions.elementToBeClickable(SearchFilms.contentFind));
        element.click();
        WaitElementUtill.waitElement(driver,SearchFilms.selected);
        WaitElementUtill.selectElement(driver, "�����");
        WaitElementUtill.waitElement(driver,SearchFilms.country);
        WaitElementUtill.selectElement(driver, "���");
        WaitElementUtill.waitElement(driver,SearchFilms.fromYear);
        WaitElementUtill.selectElement(driver, "2000");
        WaitElementUtill.waitElement(driver,SearchFilms.toYear);
        WaitElementUtill.selectElement(driver, "1998");
        WaitElementUtill.waitElement(driver,SearchFilms.buttonSearch);





    }


    @�����("^�������� � ������� ��������� �������$")
    public void ��������������������������������() throws Throwable {
        MainPage mainPage = new MainPage();
        Actions action = new Actions(driver);
        WebElement element = (new WebDriverWait(driver, 1, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//nav//a[.='������']")));
        action.moveToElement(element).build().perform();
        element = (new WebDriverWait(driver, 1, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='��������� �� �������']")));
        element.click();
    }

    @�����("^��������� ����������� �����$")
    public void ������������������������() throws Throwable {
        MainPage mainPage = new MainPage();
        WebElement element = (new WebDriverWait(driver, 5, 1000)).until(ExpectedConditions.presenceOfElementLocated(MainPage.searchFilms));
        element.click();
    }



    @�����("^�������� ����� \"([^\"]*)\"$")
    public void �������������(String arg1) throws Throwable {
        (new WebDriverWait(driver, 5, 1000)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//head/title")));
        String element = driver.getTitle();
        System.out.println(element);
        provTitle(element,arg1);
    }





//������ ������ �������� ������
//    @�����("^�������� ����� �������$")
//    public void ��������������������() throws Throwable {
//        (new WebDriverWait(driver, 5, 1000)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//head/title")));
//        String element = driver.getTitle();
//        System.out.println(element);
//        provTitle(element,"��������� � ��� ������ �������");
//    }





    @�����("^��������� �� ����������$")
    @Step("��������� �� ����������")
    public void ���������������������() throws Throwable {
                try {
                    WebElement element = driver.findElement(By.xpath("//button[.='�����']"));
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
