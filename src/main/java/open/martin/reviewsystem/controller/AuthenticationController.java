package open.martin.reviewsystem.controller;

import lombok.extern.slf4j.Slf4j;
import open.martin.reviewsystem.contract.controller.AuthenticationContract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user-account")
@Slf4j
public final class AuthenticationController implements AuthenticationContract {

    @Override
    public ResponseEntity<?> login() {
        return null;
    }
}
