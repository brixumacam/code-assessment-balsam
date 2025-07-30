package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

import java.util.List;

public class SearchResultsPage extends BasePage {
    private final WaitUtils waitUtils;
    private final By priceList = By.xpath("//span[contains(@class, 'autoSuggestList_sales')]");
    private final By addToCartButton = By.xpath("//div[contains(@class, 'productDetailContainer')]//button[@data-testid='pdc-btn-addtocart']");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    public String getPriceForResult(int index) {
        List<WebElement> prices = driver.findElements(priceList);
        if (prices.size() > index) {
            String price = prices.get(index).getText();
            logger.info("Price for product at index {}: {}", index, price);
            return price;
        } else {
            logger.error("Less than {} results found.", index + 1);
            throw new RuntimeException("Not enough search results.");
        }
    }

    public void selectResult(int index) {
        waitUtils.waitForElementToBeClickable(priceList, 5);
        List<WebElement> prices = driver.findElements(priceList);
        if (prices.size() > index) {
            prices.get(index).click();
            waitUtils.waitForElementToBeVisible(addToCartButton, 5);
            logger.info("Selected product at index: {}", index);
        } else {
            logger.error("Less than {} results found.", index + 1);
            throw new RuntimeException("Not enough search results.");
        }
    }
}
