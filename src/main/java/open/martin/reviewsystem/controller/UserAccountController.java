package open.martin.reviewsystem.controller;

import lombok.extern.slf4j.Slf4j;
import open.martin.reviewsystem.contract.controller.UserAccountControllerContract;
import open.martin.reviewsystem.contract.mapper.UserAccountModelMapper;
import open.martin.reviewsystem.contract.service.UserAccountServiceContract;
import open.martin.reviewsystem.entity.UserAccount;
import open.martin.reviewsystem.payload.UserAccountPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user-account")
@Slf4j
public final class UserAccountController extends UserAccountModelMapper implements UserAccountControllerContract {
    @Autowired
    private UserAccountServiceContract userAccountService;

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewUserAccount(@RequestBody UserAccountPayload payload) {
        UserAccount userAccount = convertPayloadToUserAccountEntity(payload);
        userAccount = userAccountService.createUserAccount(userAccount);

        UserAccountPayload resposePayload = convertUserAccountEntityToPayload(userAccount);
        return ResponseEntity.ok().body(resposePayload);
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
