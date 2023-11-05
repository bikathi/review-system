package open.martin.reviewsystem.contract.controller;

import open.martin.reviewsystem.payload.UserAccountPayload;
import org.springframework.http.ResponseEntity;

public interface UserAccountControllerContract {
    ResponseEntity<?> createNewUserAccount(UserAccountPayload payload);
    ResponseEntity<?> getUserAccountByUserName(String username);
    ResponseEntity<?> getUserAccountById(Long id);
    ResponseEntity<?> updateUserAccountDetails(UserAccountPayload payload);
    ResponseEntity<?> deleteUserAccountById(Long id);
}
