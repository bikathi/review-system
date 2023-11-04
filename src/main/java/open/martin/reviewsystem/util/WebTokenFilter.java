package open.martin.reviewsystem.util;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import open.martin.reviewsystem.exception.JWTVerificationException;
import open.martin.reviewsystem.service.UserDetailsServiceImplementation;
import open.martin.reviewsystem.type.UserDetailsImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class WebTokenFilter extends OncePerRequestFilter {
    @Autowired
    private WebTokenUtils webTokenUtils;

    @Autowired
    private UserDetailsServiceImplementation userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtString = retrieveJWT(request);
        if(jwtString != null && webTokenUtils.validateJwtToken(jwtString)) {
            String username = webTokenUtils.retrieveSubject(jwtString);
            Claims claims = webTokenUtils.retrieveClaimsFromJWT(jwtString);

            UserDetailsImplementation userDetails = (UserDetailsImplementation) userDetailsService.loadUserByUsername(username);
            // validate the subject
            if(!Objects.equals(username, userDetails.getUsername())) {
                throw new JWTVerificationException("Provided JWT has untrusted parameters");
            }

            // validate the userID claim
            if(!claims.get("userId").equals(userDetails.getAccountId())) {
                throw new JWTVerificationException("Provided JWT has untrusted parameters");
            }

            // validate the claim for user roles
            List<String> authoritiesClaimString = claims.get("authorities", List.class);
            List<? extends GrantedAuthority> authoritiesClaim = authoritiesClaimString.stream().map(SimpleGrantedAuthority::new).toList();
            authoritiesClaim.forEach((claim) -> {
                if(!userDetails.getAuthorities().contains(claim)) {
                    throw new JWTVerificationException("Provided JWT has untrusted parameters");
                }
            });

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            throw new JWTVerificationException("Missing JWT required for authentication");
        }

        filterChain.doFilter(request, response);
    }

    private final String retrieveJWT(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization").strip();

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")) {
            return authHeader.substring(7);
        }

        return null;
    }
}
