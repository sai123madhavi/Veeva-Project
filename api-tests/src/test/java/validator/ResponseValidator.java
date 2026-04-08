package validator;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ResponseValidator {

    private static final Logger log = LogManager.getLogger(ResponseValidator.class);

    public static void validateStatus(Response res, int code) {
        log.info("Validating Status Code: Expected=" + code + " Actual=" + res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), code);
    }

    public static void validateContains(Response res, String text) {
        log.info("Validating Response contains: " + text);
        Assert.assertTrue(res.getBody().asString().contains(text));
    }

    public static void validateContainsAny(Response res, String... texts) {
        String response = res.getBody().asString();
        boolean found = false;

        for (String text : texts) {
            if (response.contains(text)) {
                log.info("Matched text: " + text);
                found = true;
                break;
            }
        }

        Assert.assertTrue(found, "None of expected texts found in response");
    }
}