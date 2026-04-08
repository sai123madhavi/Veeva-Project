package builder;

import config.ConfigReader;
import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {

    // Search Product
    public static Map<String, Object> searchProduct() {
        Map<String, Object> body = new HashMap<>();
        body.put("search_product", ConfigReader.get("search.product"));
        return body;
    }

    // Create User
    public static Map<String, Object> createUser() {
        Map<String, Object> body = new HashMap<>();

        long time = System.currentTimeMillis();

        body.put("name", ConfigReader.get("user.name") + time);
        body.put("email", "user" + time + "@test.com");
        body.put("password", ConfigReader.get("user.password"));
        body.put("title", ConfigReader.get("user.title"));
        body.put("firstname", ConfigReader.get("user.firstname"));
        body.put("lastname", ConfigReader.get("user.lastname"));
        body.put("company", ConfigReader.get("user.company"));
        body.put("address1", ConfigReader.get("user.address1"));
        body.put("address2", ConfigReader.get("user.address2"));
        body.put("country", ConfigReader.get("user.country"));
        body.put("zipcode", ConfigReader.get("user.zipcode"));
        body.put("state", ConfigReader.get("user.state"));
        body.put("city", ConfigReader.get("user.city"));

        // 🔥 IMPORTANT FIX
        body.put("mobile_number", "9" + time);

        return body;
    }

    // Delete User
    public static Map<String, Object> deleteUser(String email) {
        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("password", ConfigReader.get("user.password"));
        return body;
    }

    // Update User
    public static Map<String, Object> updateUser(String email) {
        Map<String, Object> body = new HashMap<>();

        body.put("email", email);
        body.put("password", ConfigReader.get("user.password"));

        // 🔥 IMPORTANT: send all fields (same as create)
        body.put("name", "Updated " + ConfigReader.get("user.name"));
        body.put("title", ConfigReader.get("user.title"));
        body.put("firstname", ConfigReader.get("user.firstname"));
        body.put("lastname", ConfigReader.get("user.lastname"));
        body.put("company", ConfigReader.get("user.company"));
        body.put("address1", ConfigReader.get("user.address1"));
        body.put("address2", ConfigReader.get("user.address2"));
        body.put("country", ConfigReader.get("user.country"));
        body.put("zipcode", ConfigReader.get("user.zipcode"));
        body.put("state", ConfigReader.get("user.state"));
        body.put("city", ConfigReader.get("user.city"));
        body.put("mobile_number", ConfigReader.get("user.mobile"));

        return body;
    }

    // Negative Case
    public static Map<String, Object> invalidUser() {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "invalid@test.com");
        body.put("password", "wrong");
        return body;
    }
}