package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserUtils;
import utils.WaitUtils;

public class ProductsPage extends BasePage {
    private final WaitUtils waitUtils;
    private final By productPrice = By.xpath("//span[contains(@class, 'productPrice_new-price')]");
    private final By addToCartButton = By.xpath("//div[contains(@class, 'productDetailContainer')]//button[@data-testid='pdc-btn-addtocart']"); // Adjust selector
    private final By viewCartButton = By.xpath("//button[@type='button' and contains(text(), 'View Cart')]");

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    public String getProductPrice(String productName) {
        try {
            WebElement closeButton = driver.findElement(By.xpath("//button[@id='bannerCloseButton']"));
            if(closeButton.isDisplayed()) {
                closeButton.click();
            }
        } catch (NoSuchElementException e) {
            // Close button not found, continue
        }

        WebElement productPriceElement = driver.findElement(productPrice);
        BrowserUtils.scrollToElement(driver, productPriceElement);
        waitUtils.waitForElementToBeVisible(productPrice, 5);
        WebElement priceElement = driver.findElement(productPrice);
        String price = priceElement.getText();
        logger.info("Product price: {}", price);
        return price;
    }

    public void selectHeightAndLightOptions(String height, String ledLights) {
        By productHeight = By.xpath("//span[contains(@class, 'renderSelectBoxFilterItem_attribute-name') and contains(text(), '" + height + "')]");
        By productLedLights = By.xpath("//span[contains(@class, 'renderSelectBoxFilterItem_attribute-name') and contains(text(), '" + ledLights + "')]");
        By heightHeader = By.xpath("//div[@id='product_filter_height']");

        try {
            WebElement closeButton = driver.findElement(By.xpath("//button[@id='bannerCloseButton']"));
            if(closeButton.isDisplayed()) {
                closeButton.click();
            }
        } catch (NoSuchElementException e) {
            // Close button not found, continue
        }

        WebElement heightElement = driver.findElement(heightHeader);
        BrowserUtils.scrollToElement(driver, heightElement);
        waitUtils.waitForElementToBeClickable(productHeight, 5);
        click(productHeight);
        click(productLedLights);
    }

    public void clickAddToCartButton() {
        try {
            WebElement closeButton = driver.findElement(By.xpath("//button[@id='bannerCloseButton']"));
            if(closeButton.isDisplayed()) {
                closeButton.click();
            }
        } catch (NoSuchElementException e) {
            // Close button not found, continue
        }

        click(addToCartButton);
    }

    public void clickViewCartButton() {
        waitUtils.waitForElementToBeClickable(viewCartButton, 5);
        click(viewCartButton);

        By cartHeader = By.xpath("//h2[contains(text(), 'Your Shopping Cart')]");
        waitUtils.waitForElementToBeVisible(cartHeader, 10);
    }
}
