package base;

import drivers.DriverFactory;
import utils.ConfigLoader;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.initDriver();
        driver.get(ConfigLoader.get("baseUrl"));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            attachScreenshot(driver);
            driver.quit();
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public void attachScreenshot(WebDriver driver) {
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
