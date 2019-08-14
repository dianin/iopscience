package Tests;

import Pages.BookDescriptionPage;
import Pages.MainPage;
import Utils.ScreenshotListener;
import Utils.getProperty;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

@Listeners(ScreenshotListener.class)
public class Test_MainPage {

    private WebDriver driver;
    private MainPage mainPage;
    private BookDescriptionPage descriptionPage;

    @BeforeMethod
    public void SetUp (ITestContext context)
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://iopscience.iop.org/bookList/10/1");
        mainPage = new MainPage(driver);
        descriptionPage = new BookDescriptionPage(driver);
        context.setAttribute("driver", driver);
    }

    @Title("Select books. Working with dropdowns")
    @Description("Checking that all books was 2016 year")
    @Test
    public void  selectBooks ()
    {
        mainPage.selectBooksBySubject(getProperty.getConfigProperty("subjectBio"));
        mainPage.selectBooksByCollections(getProperty.getConfigProperty("collectionConcise"));
        mainPage.selectBooksByYear(getProperty.getConfigProperty("year2016"));
        mainPage.performSearch();
        mainPage.verifyYearBooks(getProperty.getConfigProperty("year2016"));
    }

    @Title("Search one book")
    @Description("Search one book and verify it")
    @Test
    public void searchBook ()
    {
        mainPage.selectBooksBySubject(getProperty.getConfigProperty("subjectMaths"));
        mainPage.selectBooksByCollections(getProperty.getConfigProperty("collectionExpanding"));
        mainPage.selectBooksByYear(getProperty.getConfigProperty("year2019"));
        mainPage.performSearch();
        mainPage.openMoreBooks();
        mainPage.openBookOnPage(getProperty.getConfigProperty("bookName"));
        descriptionPage.verifyBook(getProperty.getConfigProperty("bookName"),
                getProperty.getConfigProperty("bookAuthor"),
                getProperty.getConfigProperty("bookPublished"));

    }

    @AfterMethod
    public synchronized void GetDown ()
    {
        driver.quit();
        driver = null;

    }
}
