package pages;

import org.openqa.selenium.By;

public class FilmsRating {

    public static By country = By.xpath("//div[@id = 'countryListTitle']");
    public static By genre = By.xpath("//div[@id = 'genreListTitle']");
    public static By fromYear = By.xpath("//span[.='Ò:']/following::select[1]");
    public static By toYear = By.xpath("//span[.='ÔÓ:']/following::select[1]");
    public static By ratingFilmMin = By.xpath("//input[@id = 'rating_min']");
    public static By IMDbMin = By.xpath("//input[@id = 'ex_rating_min']");
    public static By movie—riticMin = By.xpath("//input[@id = 'tomat_rating_min']");
    public static By revieProcentMin = By.xpath("//input[@id = 'review_procent_min']");
    public static By revieProcentMax = By.xpath("//input[@id = 'review_procent_max']");
    public static By searchButton = By.xpath("//input[@class = 'nice_button submit']");
    public static By budgetMin = By.xpath("//select[contains(@name,'[budget][min]')]");
    public static By budgetMax = By.xpath("//select[contains(@name,'[budget][max]')]");
    public static By grossMin = By.xpath("//select[contains(@name,'[gross][min]')]");








}
