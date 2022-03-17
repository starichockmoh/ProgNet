package tech.vinc3nzo.prognet.jwtutils.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tech.vinc3nzo.prognet.jwtutils.*;
import tech.vinc3nzo.prognet.jwtutils.models.*;

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
    public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel request)
            throws Exception
    {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword())
            );
        }
        catch (DisabledException e) {
            throw new Exception("the user is disabled", e);
        }
        catch (BadCredentialsException e) {
            throw new Exception("the credentials are invalid", e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }
}
