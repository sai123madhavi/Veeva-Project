package pages;

import config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage extends BasePage {

    private static final Logger log = LogManager.getLogger(SignupPage.class);

    public void registerUser() {

        log.info("Starting user registration");

        driver.get(ConfigReader.get("base.url"));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/login']")
        )).click();

        String email = "test" + System.currentTimeMillis() + "@gmail.com";
        log.info("Generated email: " + email);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")))
                .sendKeys(ConfigReader.get("user.name"));

        driver.findElement(By.xpath("//input[@data-qa='signup-email']"))
                .sendKeys(email);

        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2"))).click();

        driver.findElement(By.id("password"))
                .sendKeys(ConfigReader.get("user.password"));

        driver.findElement(By.id("days")).sendKeys("10");
        driver.findElement(By.id("months")).sendKeys("May");
        driver.findElement(By.id("years")).sendKeys("1998");

        driver.findElement(By.id("first_name"))
                .sendKeys(ConfigReader.get("user.firstname"));

        driver.findElement(By.id("last_name"))
                .sendKeys(ConfigReader.get("user.lastname"));

        driver.findElement(By.id("address1"))
                .sendKeys(ConfigReader.get("user.address1"));

        driver.findElement(By.id("state"))
                .sendKeys(ConfigReader.get("user.state"));

        driver.findElement(By.id("city"))
                .sendKeys(ConfigReader.get("user.city"));

        driver.findElement(By.id("zipcode"))
                .sendKeys(ConfigReader.get("user.zipcode"));

        driver.findElement(By.id("mobile_number"))
                .sendKeys(ConfigReader.get("user.mobile"));

        WebElement createBtn = driver.findElement(By.xpath("//button[text()='Create Account']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createBtn);

        wait.until(ExpectedConditions.elementToBeClickable(createBtn)).click();

        log.info("User registration form submitted");
    }

    public boolean isAccountCreated() {

        log.info("Validating account creation");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[normalize-space()='Account Created!']")
        ));

        boolean status = successMsg.isDisplayed();

        log.info("Account created status: " + status);

        return status;
    }
}