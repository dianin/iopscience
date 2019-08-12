package Utils;

import com.google.common.base.Function;
import com.google.common.util.concurrent.FluentFuture;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

//import static sun.plugin2.message.HeartbeatMessage.DEFAULT_TIMEOUT;

public class WaitHandler {

    private final WebDriverWait shortWait;
    private final WebDriverWait longWait;
    private WebDriver driver;
    private FluentFuture wait;

    public WaitHandler(WebDriver driver) {
        this.driver = driver;
        shortWait = new WebDriverWait(driver,15);
        longWait = new WebDriverWait(driver, 40);
    }

    public void shotWaitAndClick (By selector)
    {
        shortWait.until(ExpectedConditions.elementToBeClickable(selector));
        driver.findElement(selector).click();
    }

    /*public boolean elementDisplayed (By selector) {
      WebElement list = shortWait.until(ExpectedConditions.visibilityOfElementLocated(selector));

    }*/

    public List<WebElement> returnList(By selector)
    {
        try {
            wait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElements(selector);
    }

    public void clearThenInput (By selector, String text)
    {
        driver.findElement(selector).clear();
        driver.findElement(selector).sendKeys(text);
    }



    private enum ElementStatus{
        VISIBLE,
        NOTVISIBLE,
        ENABLED,
        NOTENABLED,
        PRESENT,
        NOTPRESENT
    }
    private ElementStatus isElementVisible(WebDriver driver, By by,ElementStatus getStatus) {
        try {
            if (getStatus.equals(ElementStatus.ENABLED)) {
                if (driver.findElement(by).isEnabled())
                    return ElementStatus.ENABLED;
                return ElementStatus.NOTENABLED;
            }
            if (getStatus.equals(ElementStatus.VISIBLE)) {
                if (driver.findElement(by).isDisplayed())
                    return ElementStatus.VISIBLE;
                return ElementStatus.NOTVISIBLE;
            }
            return ElementStatus.PRESENT;
        } catch (org.openqa.selenium.NoSuchElementException nse) {
            return ElementStatus.NOTPRESENT;
        }
    }
    protected boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }

    public void input (By selector, String textToInput) {
        shortWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
        driver.findElement(selector).click();
        driver.findElement(selector).sendKeys(textToInput);

    }

    public boolean isPresent (By selector)
    {
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return true;
    }

//    public WebElement waitForElement(final By locator, final Function<By, ExpectedCondition<WebElement>> condition, final Integer timeout) {
//
//        final WebElement element = shortWait.withTimeout(
//                Optional.ofNullable(timeout)
//                        .filter(value -> value >= 0)
//                        .orElse((int) DEFAULT_TIMEOUT), TimeUnit.SECONDS)
//                .until(condition.apply(locator));
//
//        shortWait.withTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//
//        return element;
//    }
  /*  waitForElement(By.id(""), ExpectedConditions::presenceOfElementLocated, 0);
    waitForElement(By.xpath(""), ExpectedConditions::visibilityOfElementLocated, null);
    waitForElement(By.cssSelector(""), ExpectedConditions::elementToBeClickable, 15);*/

     public void shortWaitForLoad (By selector) {
         shortWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
     }

     public void scrollDownUntillVisibleElement (By selector) {
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].scrollIntoView(true);", selector);
     }

     public String getText (By selector)
     {
         return driver.findElement(selector).getText();
     }


}
