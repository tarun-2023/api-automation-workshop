package models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupResponseModel {
    private Data data;

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private User user;
        private Session session;
    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private String role;
        private String email;
    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Session{
        @JsonProperty("access_token")
        private String accessToken;
    }
}
