package open.martin.reviewsystem.contract.controller;

import org.springframework.http.ResponseEntity;

public interface AuthenticationContract {
    public ResponseEntity<?> login();
}
