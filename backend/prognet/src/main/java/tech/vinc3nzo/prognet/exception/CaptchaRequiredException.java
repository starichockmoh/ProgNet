package tech.vinc3nzo.prognet.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class CaptchaRequiredException extends Exception {
    public CaptchaRequiredException() {
        super("A Captcha check is required before you can proceed");
    }
}
