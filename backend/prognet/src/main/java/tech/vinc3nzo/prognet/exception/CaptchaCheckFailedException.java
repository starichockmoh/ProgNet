package tech.vinc3nzo.prognet.exception;

public class CaptchaCheckFailedException extends Exception {
    public CaptchaCheckFailedException() {
        super("A Captcha check has been failed");
    }
}
