package validator;

import org.testng.Assert;

public class UIValidator {

    public static void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }
}