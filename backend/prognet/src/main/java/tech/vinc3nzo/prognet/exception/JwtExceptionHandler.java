package tech.vinc3nzo.prognet.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.vinc3nzo.prognet.jpa.models.User;
import tech.vinc3nzo.prognet.jpa.repositories.UserRepository;
import tech.vinc3nzo.prognet.rspentities.CommonResponseObject;
import tech.vinc3nzo.prognet.rspentities.FieldErrorObject;
import tech.vinc3nzo.prognet.rspentities.util.ResultCode;
import tech.vinc3nzo.prognet.security.Captcha;
import tech.vinc3nzo.prognet.security.CaptchaPair;

import java.util.List;
import java.util.Map;

@ControllerAdvice
public class JwtExceptionHandler {
    @Autowired
    private UserRepository userRepository;

    @ExceptionHandler(value = CaptchaRequiredException.class)
    public ResponseEntity<CommonResponseObject> captchaRequired(CaptchaRequiredException e) {
        return ResponseEntity.ok(
                new CommonResponseObject(
                        Map.of(),
                        List.of("Captcha check has failed"),
                        List.of(new FieldErrorObject("captcha", "The Captcha is invalid")),
                        ResultCode.CAPTCHA_REQUIRED));
    }

    @ExceptionHandler(value = CaptchaCheckFailedException.class)
    public ResponseEntity<CommonResponseObject> captchaCheckFailed(CaptchaCheckFailedException e) {
        User u = e.getWho();
        CaptchaPair old = u.getCaptcha();
        u.setCaptcha(Captcha.getRandomButNot(old));
        userRepository.save(u);

        return ResponseEntity.ok(
                new CommonResponseObject(
                        Map.of("captchaUri", u.getCaptcha().getImageUri()),
                        List.of("Before you can proceed, you need to solve this Captcha"),
                        List.of(),
                        ResultCode.CAPTCHA_REQUIRED));
    }
}
