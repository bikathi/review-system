package open.martin.reviewsystem.controller;

import lombok.extern.slf4j.Slf4j;
import open.martin.reviewsystem.contract.controller.UserAccountControllerContract;
import open.martin.reviewsystem.contract.mapper.UserAccountModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user-account")
@Slf4j
public final class UserAccountController extends UserAccountModelMapper implements UserAccountControllerContract {
    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewUserAccount() {

        return null;
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/get-account/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserAccountByUserName() {
        return null;
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value = "/update-account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserAccountDetails() {
        return null;
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value = "/delete-account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserAccountById() {
        return null;
    }
}
