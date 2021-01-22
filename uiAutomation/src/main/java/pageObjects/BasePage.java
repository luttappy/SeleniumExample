package pageObjects;

import managers.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends WebDriverManager {
    private static final int DEFAULT_WAIT = 60;

    public BasePage(WebDriver driver) {
        WebDriverManager.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
