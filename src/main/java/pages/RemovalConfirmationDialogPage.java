package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class RemovalConfirmationDialogPage extends BasePage {
    private final WaitUtils waitUtils;

    private final By dialogBox = By.xpath("//ul[contains(@class, 'cartProductDetailItem_removed-product-list')]");

    public RemovalConfirmationDialogPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    public boolean isDisplayed() {
        waitUtils.waitForElementToBeVisible(dialogBox, 5);
        return isDisplayed(dialogBox);
    }

    public void getConfirmationMessage(String productName, String message) {
        By confirmationText = By.xpath("//div[contains(@class, 'cartProductDetailItem_product-name-wrapper')]/a/span[contains(text(), '" + productName + "')]");
        waitUtils.waitForElementToBeVisible(confirmationText, 5);

        if (message.contains(productName) && message.contains("removed")) {
            logger.info("Confirmation message for product '{}' is displayed.", message);
        } else {
            logger.error("Confirmation message for product '{}' does not match expected format.", message);
            throw new RuntimeException("Confirmation message does not match expected format: " + message);
        }
    }
}
