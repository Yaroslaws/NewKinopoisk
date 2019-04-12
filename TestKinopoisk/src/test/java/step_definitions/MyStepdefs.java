package step_definitions;


import cucumber.api.java.ru.����;
import cucumber.api.java.ru.�����;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Login;
import pages.MainPage;
import utils.ChromeDriverUtil;
import java.util.concurrent.TimeUnit;
public class MyStepdefs {
    public static WebDriver driver;


    @����("^������������ ��������� ���� \"([^\"]*)\"$")
    public void �������������������������(String arg1) throws Throwable {
        driver = ChromeDriverUtil.startCromeDriver();
        driver.get(arg1);
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
            System.out.println("ok");
        }
    }


    @�����("^�������� �����$")
    public void �������������() throws Throwable {

        String element = driver.findElement(By.xpath("//head/title")).getText();
        if (element.equals("���� � ������: ������ � ���������� ������� � ���������")) {
            System.out.println("ok");
        }
    }

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
