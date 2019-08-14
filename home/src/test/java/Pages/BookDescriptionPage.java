package Pages;

import Utils.getProperty;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BookDescriptionPage extends CommonMethods
{

    public BookDescriptionPage (WebDriver driver) {
        super(driver);
    }

    public void verifyBook (String name, String author, String published)
    {
        By bookName = By.xpath("//h1[@class='publication-title']//a[text()='"+name+"']");
        By bookAuthor = By.xpath("//span[@id='wd-book-author']//span[text()='"+author+"']");
        By bookPublished = By.xpath("//span[@id='wd-book-pub-date']//span[text()='"+published+"']");
        By bookImage = By.xpath("//img[@id='wd-book-cover-img']");

        Assert.assertEquals(getText(bookName), getProperty.getConfigProperty("bookName"));
        Assert.assertEquals(getText(bookAuthor),getProperty.getConfigProperty("bookAuthor"));
        Assert.assertEquals(getText(bookPublished), getProperty.getConfigProperty("bookPublished"));
        Assert.assertEquals(getAttributeValue(bookImage,"alt"),getProperty.getConfigProperty("altImage"));

    }







}
