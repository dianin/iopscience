package Pages;

import Utils.WaitHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static Utils.getProperty.getConfigProperty;

public class MainPage {

    private WebDriver driver;

    private WaitHandler waitHandler;

    private By dropdownCaret = By.xpath("(//*[@class='Header-link']//*[@class='dropdown-caret'])[2]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
        waitHandler = new WaitHandler(driver);
    }

    public synchronized void verifyPage ()  {

    }
}
