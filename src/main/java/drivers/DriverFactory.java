package drivers;

import enums.BrowserType;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    // Thread-local storage for parallel execution
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    /**
     * Initializes a new WebDriver instance based on the specified browser type.
     * The browser type is read from the configuration file.
     *
     * @return WebDriver instance
     */
    public static WebDriver initDriver() {
        String browser = ConfigLoader.get("browser").toUpperCase();
        BrowserType browserType = BrowserType.valueOf(browser);
        WebDriver driverInstance;

        switch (browserType) {
            case FIREFOX:
                driverInstance = new FirefoxDriver();
                break;
            case EDGE:
                driverInstance = new EdgeDriver();
                break;
            case CHROME:
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new"); // new headless mode (Chrome 109+)
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*"); // optional
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-infobars");
                // do NOT set --user-data-dir to avoid conflict
                driverInstance = new ChromeDriver(options);
                break;
        }

        driver.set(driverInstance);
        return driverInstance;
    }

    /**
     * Retrieves the thread-local WebDriver instance.
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quits and removes the WebDriver instance for the current thread.
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

