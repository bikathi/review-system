package open.martin.reviewsystem.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import open.martin.reviewsystem.type.UserDetailsImplementation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class WebTokenUtils {
    @Value("${app.security.secret}")
    private String secret;

    @Value("${app.security.expiration}")
    private Long expiration;

    private Key key() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    private String getUserNameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public final String generateJWT(Authentication authentication) {
        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();
        Map<String, Object> userClaims = new HashMap<>();
        userClaims.put("userId", userDetails.getAccountId());
        userClaims.put("authorities", userDetails.getAuthorities());

        return Jwts.builder()
            .setSubject(userDetails.getEmail())
            .setClaims(userClaims)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + expiration))
            .signWith(key(), SignatureAlgorithm.HS256)
            .compact();
    }

    public final boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch(MalformedJwtException ex) {
            log.error("Invalid JSON web token: {}", ex.getMessage());
        } catch(ExpiredJwtException ex) {
            log.error("JSON web token is expired: {}", ex.getMessage());
        } catch(UnsupportedJwtException ex) {
            log.error("JSON web token is unsupported: {}", ex.getMessage());
        } catch(IllegalArgumentException ex) {
            log.error("JSON web token claims string is empty: {}", ex.getMessage());
        }

        return false;
    }

    public final String retrieveUserNameFromJWT(String webTokenString) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(webTokenString).getBody().getSubject();
    }

    public final Claims retrieveClaimsFromJWT(String webTokenString) {
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(webTokenString);
        return claims.getBody();


    }
}
