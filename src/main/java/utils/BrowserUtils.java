package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.Objects;
import java.util.Set;

import utils.WaitUtils;
/**
 * Utility class providing common browser and DOM interaction methods for Selenium WebDriver.
 */
public class BrowserUtils {
    private final WebDriver driver;

    public BrowserUtils(WebDriver driver) {
        this.driver = driver;
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
            if (Objects.requireNonNull(driver.getTitle()).contains(expectedTitle)) {
                return;
            }
        }
        throw new RuntimeException("Window with title containing '" + expectedTitle + "' not found.");
    }

    public static void switchToWindowByIndex(WebDriver driver, int index) {
        Set<String> allWindows = driver.getWindowHandles();
        if (index < 0 || index >= allWindows.size()) {
            throw new IllegalArgumentException("Invalid window index: " + index);
        }
        String windowHandle = allWindows.toArray(new String[0])[index];
        driver.switchTo().window(windowHandle);
    }

    public static void acceptAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.err.println("No alert to accept: " + e.getMessage());
        }
    }

    public static void dismissAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException e) {
            System.err.println("No alert to dismiss: " + e.getMessage());
        }
    }

    public static String getAlertText(WebDriver driver) {
        try {
            return driver.switchTo().alert().getText();
        } catch (NoAlertPresentException e) {
            System.err.println("No alert present to get text from.");
            return null;
        }
    }

    public static void sendTextToAlert(WebDriver driver, String input) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(input);
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.err.println("No alert present to send text.");
        }
    }

    public static void clickElementWithJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void hoverOverElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public static void goBack(WebDriver driver) {
        driver.navigate().back();
    }

    public static void goForward(WebDriver driver) {
        driver.navigate().forward();
    }
}