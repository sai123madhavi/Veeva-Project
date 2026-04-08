package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CartPage;

public class CartSteps {

    private CartPage cartPage = new CartPage(); // ✅ Initialize once

    @When("user adds a product to cart")
    public void add_to_cart() {
        cartPage.addProduct();
    }

    @Then("product should be added to cart")
    public void validate_cart() {
        Assert.assertTrue(cartPage.isProductAdded(), "Product not added to cart");
    }

    @And("user removes product")
    public void remove_product() {
        cartPage.removeProduct();
    }

    @Then("cart should be empty")
    public void validate_empty_cart() {
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty");
    }
}