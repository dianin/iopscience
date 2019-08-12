package Tests;

import Pages.MainPage;
import Utils.ScreenshotListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(ScreenshotListener.class)
public class Test_Authorization {

    //private WebDriverManager webDriverManager;
    private WebDriver driver;
    private MainNoAuthorizedPage mainNoAuthorizedPage;
    private MainPage mainPage;


    @BeforeMethod
    public void SetUp (ITestContext context)
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://github.com/login");
        mainNoAuthorizedPage = new MainNoAuthorizedPage(driver);
        mainPage = new MainPage(driver);
        context.setAttribute("driver", driver);

    }

    @Test
    public void LogInWithNegativeCasesPass ()
    {
        mainNoAuthorizedPage.enterInvalidDataPass();
        mainNoAuthorizedPage.signIn();
        mainNoAuthorizedPage.checkForWarningError();
    }

    @Test
    public void LogInWithNegativeCasesEmail ()
    {
        mainNoAuthorizedPage.enterInvalidDataEmail();
        mainNoAuthorizedPage.signIn();
        mainNoAuthorizedPage.checkForWarningError();
    }


    @Test
    public synchronized void LogInValidDataViaEmail ()  {
        mainNoAuthorizedPage.authorizedViaEmail();
        mainNoAuthorizedPage.signIn();
        mainPage.verifyPage();
    }

    @Test
    public synchronized void LogInValidDataViaUserName ()
    {
        mainNoAuthorizedPage.authorizedVidUserName();
        mainNoAuthorizedPage.signIn();
        mainPage.verifyPage();
    }


    @AfterMethod
    public synchronized void GetDown ()
    {
        driver.quit();
        driver = null;

    }
}
