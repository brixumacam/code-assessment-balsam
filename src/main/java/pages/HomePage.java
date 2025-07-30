package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class HomePage extends BasePage {
    private final WaitUtils waitUtils;
    private final By searchBar = By.xpath("//input[@id='constructor-search-input']");
    private final By productsList = By.xpath("//span[contains(@class, 'autoSuggestList_name')]");

    public HomePage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    public void searchProduct(String searchItem) {
        waitUtils.waitForElementVisible(searchBar);
        type(searchBar, searchItem);
        waitUtils.waitForElementToBeClickable(productsList, 5);
    }

    public String getProductName(String productName) {
        By productNameLocator = By.xpath("//span[contains(@data-testid, '" + productName +"')]");
        WebElement productElement = driver.findElement(productNameLocator);

        productName = productElement.getText();
        String [] extractedName = productName.split("®");

        return extractedName[0].trim();
    }
}