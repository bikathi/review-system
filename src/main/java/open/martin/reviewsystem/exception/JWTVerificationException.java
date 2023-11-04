package open.martin.reviewsystem.exception;

import java.io.Serial;
import java.util.UUID;

public class JWTVerificationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public JWTVerificationException(String message) {
        super(message);
    }
}
