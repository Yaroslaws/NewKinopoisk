package pages;

import org.openqa.selenium.By;

public class FilmsRating {

    public static By country = By.xpath("//div[@id = 'countryListTitle']");
    public static By genre = By.xpath("//div[@id = 'genreListTitle']");
    public static By ratingFilmMin = By.xpath("//input[@id = 'rating_min']");
    public static By IMDbMin = By.xpath("//input[@id = 'ex_rating_min']");
    public static By moveCriticMin = By.xpath("//input[@id = 'tomat_rating_min']");
    public static By revieProcentMin = By.xpath("//input[@id = 'review_procent_min']");
    public static By revieProcentMax = By.xpath("//input[@id = 'review_procent_max']");
    public static By budgetMin = By.xpath("//select[contains(@name,'[budget][min]')]");
    public static By showFilmButton = By.xpath("//input[@value = 'поиск']");

}
