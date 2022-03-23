package tech.vinc3nzo.prognet.models.util;

public class ResultCode {
    /**
     * This code simply means that the request
     * has successfully been processed in the service.
     */
    public static final int SUCCESS = 0;

    /**
     * This code means that the request failed due
     * to unspecified internal service error.
     */
    public static final int INTERNAL_ERROR = 1;

    /**
     * This code means that the request is malformed,
     * e.g. some necessary fields are absent in it.
     */
    public static final int REQUEST_FAILED = 2;

    /**
     * The code to specify that the authentication request
     * has been rejected due to errors in user credentials.
     */
    public static final int AUTH_REJECTED = 3;
}
