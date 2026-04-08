package hooks;

import config.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import utils.ScreenshotUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Hooks {

    @Before
    public void setUp() {
        // Initialize driver and open URL
        File folder = new File("screenshots");
        if (folder.exists()) {
            for (File file : folder.listFiles()) {
                file.delete();
            }
        }
        DriverFactory.initDriver().get(ConfigReader.get("base.url"));
    }

    @After
    public void tearDown(Scenario scenario) {

        WebDriver driver = DriverFactory.getDriver();

        try {
            // 🔥 DEBUG (remove later if you want)
            System.out.println("Scenario: " + scenario.getName());
            System.out.println("Status: " + scenario.getStatus());

            // ✅ ONLY capture when FAILED
            if (scenario.isFailed()) {

                String name = scenario.getName().replace(" ", "_");

                System.out.println("❌ Capturing screenshot for FAILED scenario");

                String path = ScreenshotUtil.capture(driver, name);

                // Optional: attach to report
                if (path != null) {
                    byte[] screenshot = Files.readAllBytes(Paths.get(path));
                    scenario.attach(screenshot, "image/png", name);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ✅ Always quit driver (important for parallel)
            if (driver != null) {
                driver.quit();
            }
        }
    }
}