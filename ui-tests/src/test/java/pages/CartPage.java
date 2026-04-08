package pages;

import config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private static final Logger log = LogManager.getLogger(CartPage.class);

    public void openHome() {
        log.info("Opening home page");
        driver.get(ConfigReader.get("base.url"));
    }

    public void addProduct() {

        log.info("Starting addProduct flow");

        openHome();

        // ✅ Wait for full page load
        wait.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
        log.info("Page loaded successfully");

        // ✅ FIXED JavaScript (IMPORTANT)
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('iframe, ins.adsbygoogle').forEach(function(el){ el.remove(); });"
        );
        log.info("Removed ads/iframes");

        // Go to products page
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/products']")
        )).click();
        log.info("Navigated to products page");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='features_items']")
        ));

        Actions actions = new Actions(driver);

        // ================= FIRST PRODUCT =================
        WebElement firstProduct = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//div[@class='product-image-wrapper'])[1]")
                )
        );

        actions.moveToElement(firstProduct).perform();

        WebElement addBtn1 = firstProduct.findElement(By.xpath(".//a[@data-product-id]"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn1);
        log.info("First product added");

        clickContinueShopping();

        // ================= SECOND PRODUCT =================
        WebElement secondProduct = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//div[@class='product-image-wrapper'])[2]")
                )
        );

        actions.moveToElement(secondProduct).perform();

        WebElement addBtn2 = secondProduct.findElement(By.xpath(".//a[@data-product-id]"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn2);
        log.info("Second product added");

        // View cart
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[text()='View Cart']")
        )).click();

        log.info("Navigated to cart page");
    }

    // ✅ Reusable method (clean design)
    private void clickContinueShopping() {

        log.info("Clicking Continue Shopping");

        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(text(),'Continue Shopping')]")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

        wait.until(ExpectedConditions.elementToBeClickable(btn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public boolean isProductAdded() {

        log.info("Validating product added to cart");

        boolean status = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("cart_info_table")
        )).isDisplayed();

        log.info("Product added status: " + status);
        return status;
    }

    public void removeProduct() {

        log.info("Removing all products from cart");

        while (driver.findElements(By.xpath("//a[@class='cart_quantity_delete']")).size() > 0) {

            WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//a[@class='cart_quantity_delete'])[1]")
            ));

            deleteBtn.click();
            log.info("Product removed");

            wait.until(ExpectedConditions.stalenessOf(deleteBtn));
        }

        log.info("All products removed");
    }

    public boolean isCartEmpty() {

        log.info("Validating cart is empty");

        try {
            WebElement emptyMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(text(),'empty')] | //b[contains(text(),'empty')]")
            ));

            boolean status = emptyMsg.isDisplayed();
            log.info("Cart empty status: " + status);

            return status;

        } catch (Exception e) {
            log.error("Cart is not empty or element not found");
            return false;
        }
    }
}