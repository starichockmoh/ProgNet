package tech.vinc3nzo.prognet.jwtutils.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.vinc3nzo.prognet.exception.CaptchaCheckFailedException;
import tech.vinc3nzo.prognet.exception.CaptchaRequiredException;
import tech.vinc3nzo.prognet.jpa.models.User;
import tech.vinc3nzo.prognet.jpa.repositories.UserRepository;
import tech.vinc3nzo.prognet.jwtutils.*;
import tech.vinc3nzo.prognet.jwtutils.config.ServiceSettings;
import tech.vinc3nzo.prognet.jwtutils.models.*;
import tech.vinc3nzo.prognet.rspentities.CommonResponseObject;
import tech.vinc3nzo.prognet.rspentities.FieldErrorObject;
import tech.vinc3nzo.prognet.rspentities.util.ResultCode;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@CrossOrigin
@RequestMapping(path = "/api")
public class JwtController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    private static final Logger logger = LoggerFactory.getLogger(JwtController.class);

    @PostMapping("/auth/login")
    public ResponseEntity<CommonResponseObject> createToken(@RequestBody JwtRequestModel request)
    {
        if (request.getUsername() == null) {
            return ResponseEntity.ok(
                    new CommonResponseObject(
                            Map.of(),
                            List.of("The username field is missing"),
                            List.of(new FieldErrorObject("username", "The username field is missing")),
                            ResultCode.REQUEST_FAILED));
        }
        if (request.getPassword() == null) {
            return ResponseEntity.ok(
                    new CommonResponseObject(
                            Map.of(),
                            List.of("The password field is missing"),
                            List.of(new FieldErrorObject("password", "The password field is missing")),
                            ResultCode.REQUEST_FAILED));
        }

        User user;
        try {
            user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }
        catch (UsernameNotFoundException e) {
            return ResponseEntity.ok(
                    new CommonResponseObject(
                            Map.of(),
                            List.of(e.getMessage()),
                            List.of(new FieldErrorObject("username", e.getMessage())),
                            ResultCode.AUTH_REJECTED));
        }

        try {
            // a Captcha check is required
            if (user.getFailedLoginAttempts() > ServiceSettings.MAX_FAILED_ATTEMPTS) {
                if (request.getCaptcha() != null) { // if Captcha is provided
                    if (!Objects.equals(request.getCaptcha(), "1234")) { // if Captcha is not OK
                        throw new CaptchaCheckFailedException();
                    }
                }
                else {
                    throw new CaptchaRequiredException();
                }
            }

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()));
        }
        catch (BadCredentialsException e) {
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
            userRepository.save(user);
            return ResponseEntity.ok(
                    new CommonResponseObject(
                            Map.of(),
                            List.of("The credentials are invalid"),
                            List.of(new FieldErrorObject("password", "The password is incorrect")),
                            ResultCode.AUTH_REJECTED));
        }
        catch (CaptchaCheckFailedException | CaptchaRequiredException e) {
            return ResponseEntity.ok(
                    new CommonResponseObject(
                            Map.of(),
                            List.of(e.getMessage()),
                            List.of(),
                            ResultCode.CAPTCHA_REQUIRED));
        }

        user.setFailedLoginAttempts(0);
        user.getLastLogin().setTime(System.currentTimeMillis());
        user = userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new CommonResponseObject(
                Map.of("token", jwtToken,
                        "userId", user.getId()),
                List.of(),
                List.of(),
                ResultCode.SUCCESS));
    }

    @GetMapping("/auth/me")
    public ResponseEntity<CommonResponseObject> fetchCurrentUserData(Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(
                new CommonResponseObject(
                        Map.of("id", currentUser.getId(),
                                "username", currentUser.getUsername(),
                                "email", currentUser.getEmail()),
                        List.of(),
                        List.of(),
                        ResultCode.SUCCESS));
    }
}
