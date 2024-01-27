import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class UserSignupTest extends BaseAPITest {
    @Test
    public void successfulRegistration() {
        // Arrange
        String signupEndpointResource = "/api/auth/signup";

        String email = "jane11@ultralesson.com";
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
        Assert.assertEquals(statusCode, 201, "Failed due to wrong status code");
        Assert.assertEquals(authenticatedEmail, email, "Failed due to incorrect email");
        Assert.assertEquals(role, "authenticated", "Failed to validate the role to be authenticated");
        Assert.assertNotNull(accessToken, "Failed as access token is null");
    }
}
