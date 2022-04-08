package tech.vinc3nzo.prognet.security.jwtutils.models;

import org.springframework.lang.NonNull;

public class JwtRequestModel {
    private String username;
    private String password;
    private String captcha;

    protected JwtRequestModel() {}

    public JwtRequestModel(@NonNull String username, @NonNull String password, String captcha) {
        this.username = username;
        this.password = password;
        this.captcha = captcha;
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

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
