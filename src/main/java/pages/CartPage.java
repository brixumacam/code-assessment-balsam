package pages;

import base.BasePage;
import org.openqa.selenium.*;

import utils.WaitUtils;

public class CartPage extends BasePage {
    private final WaitUtils waitUtils;
    private final By cartItemCount = By.xpath("//input[contains(@data-testid, 'input-quanity')]");
    private final By removeButton = By.xpath("//button[contains(@data-testid, 'btn-remove')]");
    private final By cartTotal = By.xpath("//span[contains(@class, 'totalAmountCard_total-value')]/strong/span");

    public CartPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    public String getCartPrice(String cartPrice) {
        String price = getText(cartTotal);
        logger.info("Cart total price: {}", price);
        return price;
    }

    public int getCartItemCount() {
        WebElement itemCountElement = driver.findElement(cartItemCount);
        String count = itemCountElement.getAttribute("value");
        assert count != null;
        int countValue = Integer.parseInt(count);
        logger.info("Cart item count: {}", countValue);
        return countValue;
    }

    public void clickRemove() throws InterruptedException {
        waitUtils.waitForElementToBeVisible(removeButton, 5);
        Thread.sleep(5000);
        try {
            waitUtils.waitForElementToBeClickable(removeButton, 5);
        } catch (Exception e) {
            logger.error("Remove button not clickable: {}", e.getMessage());
        }
        click(removeButton);
        logger.info("Clicked on remove button");
    }
}
