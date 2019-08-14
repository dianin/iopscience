package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CommonMethods {

    private  WebDriverWait shortWait;
    private  WebDriverWait longWait;
    public WebDriver driver;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
        shortWait = new WebDriverWait(driver, 15);
        longWait = new WebDriverWait(driver, 40);
    }

    public void click(By selector) {
        driver.findElement(selector).click();
    }

    public void shotWaitAndClick(By selector) {
        shortWait.until(ExpectedConditions.elementToBeClickable(selector));
        driver.findElement(selector).click();
    }


    public List<WebElement> returnList(By selector) {
        try {
            wait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElements(selector);
    }

    public void clearThenInput(By selector, String text) {
        driver.findElement(selector).clear();
        driver.findElement(selector).sendKeys(text);
    }


    private enum ElementStatus {
        VISIBLE,
        NOTVISIBLE,
        ENABLED,
        NOTENABLED,
        PRESENT,
        NOTPRESENT
    }

    private ElementStatus isElementVisible(WebDriver driver, By by, ElementStatus getStatus) {
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

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void input(By selector, String textToInput) {
        shortWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
        driver.findElement(selector).click();
        driver.findElement(selector).sendKeys(textToInput);
    }

    public boolean isPresent(By selector) {
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return true;
    }

    public void shortWaitForLoad(By selector) {
        shortWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
    }

    public void scrollDownUntillVisibleElement(By selector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", selector);
    }

    public String getText(By selector) {
        return driver.findElement(selector).getText();
    }

    public   String getAttributeValue(By selector, String attributeName) {
        return driver.findElement(selector).getAttribute(attributeName);

    }
}
