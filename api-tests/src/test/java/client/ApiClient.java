package client;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ApiClient {

    private static final Logger log = LogManager.getLogger(ApiClient.class);

    static {
        RestAssured.baseURI = ConfigReader.get("base.url");
    }

    public Response get(String endpoint) {
        log.info("GET Request: " + endpoint);

        Response res = RestAssured
                .given()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();

        log.info("Response: " + res.asString());
        return res;
    }

    public Response post(String endpoint, Map<String, Object> body) {
        log.info("POST Request: " + endpoint);
        log.info("Request Body: " + body);

        Response res = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();

        log.info("Response: " + res.asString());
        return res;
    }

    public Response put(String endpoint, Map<String, Object> body) {
        log.info("PUT Request: " + endpoint);
        log.info("Request Body: " + body);

        Response res = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();

        log.info("Response: " + res.asString());
        return res;
    }

    public Response delete(String endpoint, Map<String, Object> body) {
        log.info("DELETE Request: " + endpoint);
        log.info("Request Body: " + body);

        Response res = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(body)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();

        log.info("Response: " + res.asString());
        return res;
    }
}