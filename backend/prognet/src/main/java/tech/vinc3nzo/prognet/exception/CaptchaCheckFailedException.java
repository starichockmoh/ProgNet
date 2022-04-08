package tech.vinc3nzo.prognet.exception;

import tech.vinc3nzo.prognet.jpa.models.User;

public class CaptchaCheckFailedException extends Exception {
    private final User who;

    public CaptchaCheckFailedException(User who) {
        super("A Captcha check has been failed");
        this.who = who;
    }

    public User getWho() {
        return who;
    }
}
