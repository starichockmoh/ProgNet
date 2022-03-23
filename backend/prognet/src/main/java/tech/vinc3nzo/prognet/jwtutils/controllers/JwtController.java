package tech.vinc3nzo.prognet.jwtutils.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tech.vinc3nzo.prognet.jpa.repositories.UserRepository;
import tech.vinc3nzo.prognet.jwtutils.*;
import tech.vinc3nzo.prognet.jwtutils.models.*;
import tech.vinc3nzo.prognet.models.CommonResponseObject;
import tech.vinc3nzo.prognet.models.FieldErrorObject;
import tech.vinc3nzo.prognet.models.util.ResultCode;

import java.util.List;
import java.util.Map;

@RestController
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

    @PostMapping("/auth/login")
    public ResponseEntity<CommonResponseObject> createToken(@RequestBody JwtRequestModel request)
    {
        if (request.getUsername() == null) {
            return ResponseEntity.ok(
                    new CommonResponseObject(
                            Map.of(),
                            List.of("The username field is invalid"),
                            List.of(new FieldErrorObject("username", "The username field is invalid")),
                            ResultCode.REQUEST_FAILED));
        }
        if (request.getPassword() == null) {
            return ResponseEntity.ok(
                    new CommonResponseObject(
                            Map.of(),
                            List.of("The password field is invalid"),
                            List.of(new FieldErrorObject("password", "The password field is invalid")),
                            ResultCode.REQUEST_FAILED));
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            return ResponseEntity.ok(
                    new CommonResponseObject(
                            Map.of(),
                            List.of("The credentials are invalid"),
                            List.of(),
                            ResultCode.AUTH_REJECTED));
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        var user = userRepository.findAll().parallelStream()
                .filter(u -> u.getUsername().equals(userDetails.getUsername()))
                .toList()
                .get(0);

        String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new CommonResponseObject(
                Map.of("token", jwtToken,
                        "userId", user.getId()),
                List.of(),
                List.of(),
                ResultCode.SUCCESS));
    }
}
