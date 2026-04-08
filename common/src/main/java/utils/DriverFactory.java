package utils;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver() {

        String browser = ConfigReader.get("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            driver.set(new ChromeDriver());
        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.get().manage().window().maximize();

        return driver.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    // ✅ ADD THIS
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}