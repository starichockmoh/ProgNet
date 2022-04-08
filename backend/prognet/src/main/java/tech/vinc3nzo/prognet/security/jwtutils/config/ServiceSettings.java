package tech.vinc3nzo.prognet.security.jwtutils.config;

public class ServiceSettings {
    public static final long USERS_PER_PAGE_COUNT = 10L;
    public static final long FIRST_PAGE = 1L;

    /**
     * The JSON Web Token validity period in seconds.
     */
    public static final long TOKEN_VALIDITY = 24 * 60 * 60;

    /**
     * This value defines the amount of failed authorization
     * attempts before the user will be requested for a Captcha check.
     */
    public static final short MAX_FAILED_ATTEMPTS = 3;
}
