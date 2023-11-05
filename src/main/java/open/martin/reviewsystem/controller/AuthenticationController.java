package open.martin.reviewsystem.controller;

import lombok.extern.slf4j.Slf4j;
import open.martin.reviewsystem.contract.controller.AuthenticationContract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
@Slf4j
public final class AuthenticationController implements AuthenticationContract {
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping(value = "/login")
    public ResponseEntity<?> login() {
        return null;
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping(value = "/refresh-auth")
    public ResponseEntity<?> refreshAuthToken() {
        return null;
    }
}
