package tech.vinc3nzo.prognet.security.jwtutils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import tech.vinc3nzo.prognet.security.jwtutils.config.ServiceSettings;

import java.util.*;

/**
 * Utility class for generating and validating
 * users' JSON Web Tokens.
 */
@Component
public class TokenManager {
    @Value("${tech.vinc3nzo.jwtutils.secret}")
    private String jwtSecret;

    /**
     * Generates new JSON Web Token for the user, whose details are provided
     * in a {@link org.springframework.security.core.userdetails.UserDetails}
     * instance.
     * @param userDetails a set of necessary user's properties
     * @return a {@link java.lang.String} object representing the new compacted token
     */
    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ServiceSettings.TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Checks validity of the provided token for a particular user,
     * whose details are supplied in a {@link org.springframework.security.core.userdetails.UserDetails}
     * instance.
     * @param token a {@link java.lang.String} representing previously generated JWT
     * @param userDetails a set of necessary user's properties
     * @return true if the token is valid, false if it's not
     */
    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        boolean isTokenExpired = claims.getExpiration()
                .before(new Date(System.currentTimeMillis()));
        return username.equals(userDetails.getUsername()) && !isTokenExpired;
    }

    /**
     * Helper function, which takes JWT and fetches
     * a username from it.
     * @param token a previously generated JWT
     * @return a username, associated with the token
     */
    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
