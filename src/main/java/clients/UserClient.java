package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.SignupRequestModel;
import models.response.SignupResponseModel;

import java.util.UUID;

public class UserClient {
    public SignupResponseModel createUser(String email, String password) {
        String signupEndpointResource = "/api/auth/signup";

        SignupRequestModel signupRequestModel = SignupRequestModel.builder()
                .email(email)
                .password(password)
                .build();

        SignupResponseModel signupResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(signupRequestModel)
                .post(signupEndpointResource).as(SignupResponseModel.class);
        return signupResponse;
    }
}
