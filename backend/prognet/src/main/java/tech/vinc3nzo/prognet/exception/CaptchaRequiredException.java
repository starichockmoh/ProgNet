package tech.vinc3nzo.prognet.exception;

public class CaptchaRequiredException extends Exception {
    public CaptchaRequiredException() {
        super("A Captcha check is required before you can proceed");
    }
}
