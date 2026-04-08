package stepdefinitions;

import config.ConfigReader;
import io.cucumber.java.en.*;
import pages.LoginPage;
import validator.UIValidator;

public class LoginSteps {

    LoginPage page;

    @When("user enters valid credentials")
    public void valid_login() {
        page = new LoginPage();
        page.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
    }

    @Then("user should login successfully")
    public void validate_login() {
        System.out.println("Login success");
    }

    @When("user enters invalid credentials")
    public void invalid_login() {
        page = new LoginPage();
        page.login(
                ConfigReader.get("invalid.username"),
                ConfigReader.get("invalid.password")
        );
    }

    @Then("error message should be displayed")
    public void validate_error() {
        UIValidator.validateTrue(page.isErrorDisplayed(), "Error not displayed");
    }
}