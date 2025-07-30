package base;

import utils.Log;
import utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.apache.logging.log4j.Logger;

public class BasePage {
    protected WebDriver driver;
    protected WaitUtils wait;
    protected Logger logger;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        this.logger = Log.getLogger(this.getClass());
    }

    protected void click(By locator) {
        try {
            wait.waitForElementClickable(locator).click();
            logger.info("Clicked on element: {}", locator.toString());
        } catch (Exception e) {
            logger.error("Unable to click on element: {}", locator.toString(), e);
            throw e;
        }
    }

    protected void type(By locator, String text) {
        try {
            WebElement element = wait.waitForElementVisible(locator);
            element.clear();
            element.sendKeys(text);
            logger.info("Typed '{}' in element: {}", text, locator.toString());
        } catch (Exception e) {
            logger.error("Unable to type in element: {}", locator.toString(), e);
            throw e;
        }
    }

    protected String getText(By locator) {
        try {
            String text = wait.waitForElementVisible(locator).getText();
            logger.info("Text found: '{}' from element: {}", text, locator.toString());
            return text;
        } catch (Exception e) {
            logger.error("Unable to get text from element: {}", locator.toString(), e);
            throw e;
        }
    }

    protected boolean isDisplayed(By locator) {
        try {
            boolean visible = wait.waitForElementVisible(locator).isDisplayed();
            logger.info("Element is visible: {}", locator.toString());
            return visible;
        } catch (Exception e) {
            logger.warn("Element not visible: {}", locator.toString());
            return false;
        }
    }

    protected void assertEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual.trim(), expected.trim(), message);
            logger.info("Assertion passed: {}", message);
        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", message);
            throw e;
        }
    }
}
