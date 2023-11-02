package open.martin.reviewsystem.exception;

import open.martin.reviewsystem.payload.ControllerExceptionPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
