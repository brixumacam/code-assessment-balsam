package tests;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;
import org.testng.annotations.Test;
import utils.ConfigLoader;

public class PurchaseTest extends BaseTest {
    protected WebDriver driver;
    private String resultPagePrice;
    private String productPagePrice;
    private String productName;
    private static final String productNameWithoutSymbol = "";

    private static final String SEARCH_ITEM = "Christmas Tree";
    private static final String PRODUCT_HEIGHT = "6";
    private static final String PRODUCT_LIGHTS = "LED Clear Lights";
    private static final int ACTIVE_PRODUCT_INDEX = 2;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.initDriver();
        driver.get(ConfigLoader.get("baseUrl"));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void searchAndSelectProduct() {
        HomePage home = new HomePage(driver);
        home.searchProduct(SEARCH_ITEM);
        productName = home.getProductName(productNameWithoutSymbol);

        SearchResultsPage results = new SearchResultsPage(driver);
        resultPagePrice = results.getPriceForResult(ACTIVE_PRODUCT_INDEX);
        System.out.println("Price from third item: " + resultPagePrice);
        results.selectResult(ACTIVE_PRODUCT_INDEX);
    }

    @Test(priority = 2)
    public void customizeAndAddToCart() {
        ProductsPage product = new ProductsPage(driver);
        productPagePrice = product.getProductPrice(SEARCH_ITEM);

        product.selectHeightAndLightOptions(PRODUCT_HEIGHT, PRODUCT_LIGHTS);
        product.clickAddToCartButton();
        product.clickViewCartButton();
    }

    @Test(priority = 3)
    public void validateCartDetails() {
        CartPage cart = new CartPage(driver);
        String cartPagePrice = cart.getCartPrice(SEARCH_ITEM);

        Assert.assertEquals(resultPagePrice, productPagePrice, "Mismatch: result price vs product product price");
        Assert.assertEquals(cartPagePrice, resultPagePrice, "Mismatch: result price vs cart price");
        Assert.assertEquals(cart.getCartItemCount(), 1, "Cart item count should be 1");
    }

    @Test(priority = 4)
    public void removeItemFromCart() throws InterruptedException {
        CartPage cart = new CartPage(driver);
        cart.clickRemove();

        RemovalConfirmationDialogPage dialog = new RemovalConfirmationDialogPage(driver);
        Assert.assertTrue(dialog.isDisplayed(), "Confirmation dialog not shown.");
        dialog.getConfirmationMessage(productName, "removed");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
