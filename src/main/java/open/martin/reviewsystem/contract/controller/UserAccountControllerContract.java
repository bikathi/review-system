package open.martin.reviewsystem.contract.controller;

import org.springframework.http.ResponseEntity;

public interface UserAccountControllerContract {
    ResponseEntity<?> createNewUserAccount();
    ResponseEntity<?> getUserAccountByUserName();
    ResponseEntity<?> updateUserAccountDetails();
    ResponseEntity<?> deleteUserAccountById();
}
