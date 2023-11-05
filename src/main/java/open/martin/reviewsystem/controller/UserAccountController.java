package open.martin.reviewsystem.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import open.martin.reviewsystem.contract.controller.UserAccountControllerContract;
import open.martin.reviewsystem.contract.mapper.UserAccountModelMapper;
import open.martin.reviewsystem.contract.service.UserAccountServiceContract;
import open.martin.reviewsystem.entity.UserAccount;
import open.martin.reviewsystem.exception.ResourceNotFoundException;
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

        UserAccountPayload responsePayload = convertUserAccountEntityToPayload(userAccount);
        return ResponseEntity.ok().body(responsePayload);
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/get-account/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserAccountByUserName(@PathVariable String username) {
        try {
            UserAccount userAccount = userAccountService.getUserAccountByUsername(username);
            UserAccountPayload responsePayload = convertUserAccountEntityToPayload(userAccount);

            return ResponseEntity.ok().body(responsePayload);
        } catch(EntityNotFoundException ex) {
            log.error("Error finding account by username {}", username);
            throw new ResourceNotFoundException("Could not find account by username " + username);
        }
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/get-account/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserAccountById(@PathVariable Long id) {
        try {
            UserAccount userAccount = userAccountService.getUserAccountById(id);
            UserAccountPayload responsePayload = convertUserAccountEntityToPayload(userAccount);

            return ResponseEntity.ok().body(responsePayload);
        } catch(EntityNotFoundException ex) {
            log.error("Error finding account by id {}", id);
            throw new ResourceNotFoundException("Could not find id by account " + id);
        }
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value = "/update-account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserAccountDetails(@RequestBody UserAccountPayload payload) {
        UserAccount userAccount = convertPayloadToUserAccountEntity(payload);
        userAccountService.updateUserAccountDetails(userAccount);
        return ResponseEntity.ok().body(payload);
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value = "/delete-account/{id}")
    public ResponseEntity<?> deleteUserAccountById(@PathVariable Long id) {
        userAccountService.deleteUserAccountById(id);
        return ResponseEntity.ok(null);
    }
}
