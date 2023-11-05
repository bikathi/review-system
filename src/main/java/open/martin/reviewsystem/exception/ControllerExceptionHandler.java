package open.martin.reviewsystem.exception;

import open.martin.reviewsystem.payload.ControllerExceptionPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = InvalidOperationException.class)
    public final ResponseEntity<ControllerExceptionPayload>  invalidOperationException(InvalidOperationException ex, WebRequest request) {
        ControllerExceptionPayload payload = new ControllerExceptionPayload(
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(true));
        return ResponseEntity.badRequest().body(payload);
    }

    @ExceptionHandler(value = JWTVerificationException.class)
    public final ResponseEntity<ControllerExceptionPayload> authenticationException(JWTVerificationException ex, WebRequest request) {
        ControllerExceptionPayload payload = new ControllerExceptionPayload(
            HttpStatus.UNAUTHORIZED.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(true));
        return new ResponseEntity<>(payload, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public final ResponseEntity<ControllerExceptionPayload> methodSecurityException(Exception ex, WebRequest request) {
        ControllerExceptionPayload payload = new ControllerExceptionPayload(
            HttpStatus.FORBIDDEN.value(),
            new Date(),
            "Insufficient permissions to perform this action",
            request.getDescription(true));
        return new ResponseEntity<>(payload, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class, UsernameNotFoundException.class })
    public final ResponseEntity<ControllerExceptionPayload> notFoundException(RuntimeException ex, WebRequest request) {
        ControllerExceptionPayload payload = new ControllerExceptionPayload(
            HttpStatus.FORBIDDEN.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(true));
        return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
    }
}
