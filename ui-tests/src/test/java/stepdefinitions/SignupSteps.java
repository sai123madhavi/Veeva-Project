package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.SignupPage;

public class SignupSteps {

    SignupPage signupPage = new SignupPage();

    @When("user registers a new account")
    public void register_user() {
        signupPage.registerUser();
    }

    @Then("account should be created successfully")
    public void validate_register() {
        Assert.assertTrue(signupPage.isAccountCreated(), "Account not created");
    }
}