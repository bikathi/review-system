package open.martin.reviewsystem.exception;

import java.io.Serial;
import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
