package open.martin.reviewsystem.exception;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class InvalidOperationException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public InvalidOperationException(String message) {
        super(message);
    }
}
