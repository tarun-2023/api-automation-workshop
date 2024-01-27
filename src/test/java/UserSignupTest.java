import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.UUID;

public class UserSignupTest extends BaseAPITest {
    @Test
    public void successfulRegistration() {
        // Arrange
        String signupEndpointResource = "/api/auth/signup";

        String email = UUID.randomUUID().toString() + "@ultralesson.com";
        String password = "1234567890";

        String signupRequestBody = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        // Act
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(signupRequestBody)
                .post(signupEndpointResource);

        int statusCode = response.getStatusCode();
        String authenticatedEmail = response.jsonPath().get("data.user.email");
        String role = response.jsonPath().get("data.user.role");
        String accessToken = response.jsonPath().get("data.session.access_token");

        // Assert
        Assert.assertEquals(statusCode, 201, "The API did not return the expected 201 Created status code");
        Assert.assertEquals(authenticatedEmail, email, "The returned email does not match the one used during signup");
        Assert.assertEquals(role, "authenticated", "The user role is not set to 'authenticated'");
        Assert.assertNotNull(accessToken, "The access token should not be null after successful signup.");
    }
}
