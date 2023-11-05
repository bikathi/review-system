package open.martin.reviewsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountPayload {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String dateJoined; // format dd-MM-yyyy
    private String password;
    private List<String> roles;
    private String lastLogin;
}
