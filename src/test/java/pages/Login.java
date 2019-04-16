package java.pages;

import org.openqa.selenium.By;

public class Login {
    //locators
    public By login = By.xpath("//input[@id=\"passp-field-login\"]");
    public By buttonEnter = By.xpath("//button[contains(@class,'passp-form-button')]");
    public By password = By.xpath("//input[contains(@type,'password')]");

}
