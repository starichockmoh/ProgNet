package tech.vinc3nzo.prognet.jwtutils.models;

/**
 * The model representing a form of service's response.
 */
public class JwtResponseModel {
    private final String token;

    public JwtResponseModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
