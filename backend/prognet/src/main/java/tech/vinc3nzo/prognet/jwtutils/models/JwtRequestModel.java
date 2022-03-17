package tech.vinc3nzo.prognet.jwtutils.models;

/**
 * The model representing a form of client's request.
 */
public class JwtRequestModel {
    private String username;
    private String password;

    protected JwtRequestModel() {}

    /**
     * Constructs a new JwtRequestModel instance by
     * username and password {@link java.lang.String}s
     * provided.
     * @param username user's login name
     * @param password user's password
     */
    public JwtRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
