package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class MainPage extends CommonMethods
{
    private By bookSubjectDropdown = By.xpath("//*[@id=\"BF_SUBJECT\"]");
    private By collectionDropdown = By.xpath("//*[@id=\"BF_COLLECTION\"]");
    private By yearDropdown = By.xpath("//*[@id=\"BF_YEAR\"]");
    private By searchButton = By.xpath("//input[@type='submit' and @value='Go']");
    private By publishedField = By.xpath("//*[@class='small art-list-item-meta'][2]");
    private By moreBooksButton = By.id("resultsShown");
    private By selectMoreBooks = By.xpath("//*[@value='/50/1']");



    public MainPage(WebDriver driver) {
        super(driver);
    }

    public  void selectBooksBySubject (String subject)
    {
        click(bookSubjectDropdown);
        By subjectLocator = By.xpath("//*[@value='"+ subject + "']");
        click(subjectLocator);
    }

    public void selectBooksByCollections (String collection)
    {
        click(collectionDropdown);
        By collectionLocator = By.xpath("//*[@value='"+collection+"']");//IOP Concise Physics
        click(collectionLocator);
    }

    public void performSearch ()
    {
        click(searchButton);
        System.out.println();
    }

    public void selectBooksByYear (String year)
    {
        click(yearDropdown);
        By yearLocator = By.xpath("//*[@value='"+year+"']");
        click(yearLocator);
    }

    public void openMoreBooks ()
    {
        click(moreBooksButton);
        click(selectMoreBooks);
    }

    public void verifyYearBooks (String year)
    {
        List<WebElement> publishedFieldList = driver.findElements(publishedField);
        publishedFieldList.forEach(webElement -> Assert.assertTrue(webElement.getText().contains(year)));
    }

    public BookDescriptionPage openBookOnPage(String name)
    {
        By bookForSearch = By.xpath("//h2[text()='"+ name +"']");
        click(bookForSearch);
        return new BookDescriptionPage(driver);
    }



}
