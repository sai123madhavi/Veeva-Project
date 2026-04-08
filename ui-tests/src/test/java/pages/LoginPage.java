package pages;

import config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final Logger log = LogManager.getLogger(LoginPage.class);

    By email = By.name("email");
    By password = By.name("password");
    By loginBtn = By.xpath("//button[text()='Login']");
    By errorMsg = By.xpath("//p[text()='Your email or password is incorrect!']");

    public void open() {
        log.info("Opening login page");
        driver.get(ConfigReader.get("base.url") + "/login");
    }

    public void login(String user, String pass) {
        log.info("Performing login with user: " + user);

        open();

        driver.findElement(email).sendKeys(user);
        driver.findElement(password).sendKeys(pass);

        driver.findElement(loginBtn).click();

        log.info("Login action performed");
    }

    public boolean isErrorDisplayed() {
        log.info("Checking error message for invalid login");

        boolean status = driver.findElement(errorMsg).isDisplayed();

        log.info("Error displayed: " + status);
        return status;
    }
}