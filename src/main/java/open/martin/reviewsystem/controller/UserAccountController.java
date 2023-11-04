package open.martin.reviewsystem.controller;

import lombok.extern.slf4j.Slf4j;
import open.martin.reviewsystem.contract.controller.UserAccountControllerContract;
import open.martin.reviewsystem.contract.mapper.UserAccountModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user-account")
@Slf4j
public final class UserAccountController extends UserAccountModelMapper implements UserAccountControllerContract {
    @Override
    public ResponseEntity<?> createNewUserAccount() {
        return null;
    }

    @Override
    public ResponseEntity<?> getUserAccountByUserName() {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUserAccountDetails() {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteUserAccountById() {
        return null;
    }
}
