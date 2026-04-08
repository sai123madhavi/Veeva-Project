package utils;

import org.openqa.selenium.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String name) {

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String fileName = "screenshots/" + name.replace(" ", "_")
                    + "_" + System.currentTimeMillis() + ".png";

            Files.createDirectories(Paths.get("screenshots"));

            Files.copy(src.toPath(), Paths.get(fileName));

            return fileName;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}