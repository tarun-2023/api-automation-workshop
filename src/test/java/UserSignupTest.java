import clients.UserClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.response.SignupResponseModel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.UUID;

public class UserSignupTest extends BaseAPITest {
    @Test
    public void successfulRegistration() {
        // Arrange
        String email = UUID.randomUUID().toString() + "@ultralesson.com";
        String password = "1234567890";

        UserClient userClient = new UserClient();
        SignupResponseModel response = userClient.createUser(email, password);

        String authenticatedEmail = response.getData().getUser().getEmail();
        String role = response.getData().getUser().getRole();
        String accessToken = response.getData().getSession().getAccessToken();

        // Assert
        Assert.assertEquals(authenticatedEmail, email, "The returned email does not match the one used during signup");
        Assert.assertEquals(role, "authenticated", "The user role is not set to 'authenticated'");
        Assert.assertNotNull(accessToken, "The access token should not be null after successful signup.");
    }
}
