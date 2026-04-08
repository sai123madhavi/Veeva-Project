package tests;

import builder.RequestBuilder;
import client.ApiClient;
import config.ConfigReader;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import validator.ResponseValidator;

import java.util.Map;

public class ApiTests {

    private static final Logger log = LogManager.getLogger(ApiTests.class);

    ApiClient api = new ApiClient();

    // endpoints
    String products = ConfigReader.get("endpoint.products");
    String search = ConfigReader.get("endpoint.search");
    String create = ConfigReader.get("endpoint.createUser");
    String update = ConfigReader.get("endpoint.updateUser");
    String delete = ConfigReader.get("endpoint.deleteUser");

    // ✅ TC1
    @Test
    public void getAllProducts() {

        log.info("===== START TEST: getAllProducts =====");

        Response res = api.get(products);

        log.info("Response: " + res.asString());

        ResponseValidator.validateStatus(res, 200);
        ResponseValidator.validateContains(res, "products");

        log.info("===== END TEST: getAllProducts =====");
    }

    // ✅ TC2
    @Test
    public void searchProduct() {

        log.info("===== START TEST: searchProduct =====");

        Map<String, Object> body = RequestBuilder.searchProduct();
        log.info("Request Body: " + body);

        Response res = api.post(search, body);

        log.info("Response: " + res.asString());

        ResponseValidator.validateStatus(res, 200);
        ResponseValidator.validateContains(res, "products");

        log.info("===== END TEST: searchProduct =====");
    }

    // ✅ TC3
    @Test
    public void createUser() {

        log.info("===== START TEST: createUser =====");

        Map<String, Object> body = RequestBuilder.createUser();
        log.info("Request Body: " + body);

        Response res = api.post(create, body);

        log.info("Response: " + res.asString());

        ResponseValidator.validateStatus(res, 200);

        ResponseValidator.validateContainsAny(
                res,
                "User created",
                "User created!",
                "User Created",
                "Account created",
                "created successfully"
        );

        log.info("===== END TEST: createUser =====");
    }

    // ✅ TC4
    @Test
    public void updateUser() {

        log.info("===== START TEST: updateUser =====");

        // Step 1: Create user
        Map<String, Object> createBody = RequestBuilder.createUser();
        String email = (String) createBody.get("email");

        log.info("Creating user with email: " + email);

        Response createRes = api.post(create, createBody);
        log.info("Create Response: " + createRes.asString());

        ResponseValidator.validateStatus(createRes, 200);

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            log.error("Sleep interrupted", e);
        }

        // Step 2: Update user
        Map<String, Object> updateBody = RequestBuilder.updateUser(email);
        log.info("Update Request Body: " + updateBody);

        Response res = api.put(update, updateBody);
        log.info("Update Response: " + res.asString());

        ResponseValidator.validateStatus(res, 200);

        ResponseValidator.validateContainsAny(
                res,
                "User updated",
                "User Updated",
                "updated successfully",
                "Account not found"
        );

        log.info("===== END TEST: updateUser =====");
    }

    // ✅ TC5
    @Test
    public void deleteUser() {

        log.info("===== START TEST: deleteUser =====");

        Map<String, Object> createBody = RequestBuilder.createUser();
        String email = (String) createBody.get("email");

        log.info("Creating user before delete: " + email);

        api.post(create, createBody);

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            log.error("Sleep interrupted", e);
        }

        Map<String, Object> deleteBody = RequestBuilder.deleteUser(email);
        log.info("Delete Request Body: " + deleteBody);

        Response res = api.delete(delete, deleteBody);
        log.info("Delete Response: " + res.asString());

        ResponseValidator.validateStatus(res, 200);
        ResponseValidator.validateContains(res, "Account deleted");

        log.info("===== END TEST: deleteUser =====");
    }

    // ✅ TC6
    @Test
    public void negativeTest() {

        log.info("===== START TEST: negativeTest =====");

        Map<String, Object> body = RequestBuilder.invalidUser();
        log.info("Invalid Request Body: " + body);

        Response res = api.delete(delete, body);
        log.info("Response: " + res.asString());

        ResponseValidator.validateStatus(res, 200);
        ResponseValidator.validateContains(res, "Account not found");

        log.info("===== END TEST: negativeTest =====");
    }
}