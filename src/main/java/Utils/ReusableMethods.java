package Utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ReusableMethods {

    private static Logger log = LogManager.getLogger(ReusableMethods.class.getName());

    public static void waitForPageLoaded(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete")&&((Boolean)((JavascriptExecutor)driver).executeScript("return jQuery.active == 0")));
            }
        };
        try {
            Thread.sleep(100);
            WebDriverWait waitForLoad = new WebDriverWait(driver, 10);
            waitForLoad.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    public static void clickUsingJS(WebDriver driver, WebElement e) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", e);
    }

    public static void clickElement(WebDriver driver, WebElement element) {
//        checkPageIsReady(driver);
        waitForPageLoaded(driver);
        boolean elementStatus = ReusableMethods.waitForElement(driver, element);
        if (elementStatus) {
            try {
                ReusableMethods.clickUsingJS(driver, element);
            } catch (Exception e) {
                log.info("Element is not clicked.Exception is displayed");
            }
        } else {
            log.info("Element not clicked");
        }

    }

    public static boolean waitForElement(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            log.info("Element is not present");
            return false;
        }
    }

    public static void clickElementNormalClick(WebDriver driver, WebElement element) {
        waitForPageLoaded(driver);
        boolean elementStatus = ReusableMethods.waitForElement(driver, element);
        if (elementStatus) {
            try {
                element.click();
            } catch (Exception e) {
                log.info("Element is not clicked. EXception is displayed");
            }

        } else {
            log.info("Element not present");
        }
    }


}
