package open.martin.reviewsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerExceptionPayload {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
