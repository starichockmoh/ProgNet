package tech.vinc3nzo.prognet.jwtutils.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tech.vinc3nzo.prognet.jwtutils.*;
import tech.vinc3nzo.prognet.jwtutils.models.*;
import tech.vinc3nzo.prognet.models.CommonResponseObject;
import tech.vinc3nzo.prognet.models.EmptyObject;
import tech.vinc3nzo.prognet.models.FieldErrorObject;
import tech.vinc3nzo.prognet.models.util.ResultCode;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api")
public class JwtController {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/auth/login")
    public ResponseEntity<CommonResponseObject> createToken(@RequestBody JwtRequestModel request)
            throws Exception
    {
        if (request.getUsername() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new CommonResponseObject(
                            new EmptyObject(),
                            List.of("The username field is invalid"),
                            List.of(new FieldErrorObject("username", "The username field is invalid")),
                            ResultCode.REQUEST_FAILED));
        }
        if (request.getPassword() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new CommonResponseObject(
                            new EmptyObject(),
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
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new CommonResponseObject(
                            null,
                            List.of("The credentials are invalid"),
                            List.of(),
                            ResultCode.AUTH_REJECTED));
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new CommonResponseObject(
                new JwtResponseModel(jwtToken),
                List.of(),
                List.of(),
                ResultCode.SUCCESS));
    }
}
