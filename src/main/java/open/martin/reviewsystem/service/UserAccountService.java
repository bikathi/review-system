package open.martin.reviewsystem.service;

import lombok.extern.slf4j.Slf4j;
import open.martin.reviewsystem.contract.service.UserAccountServiceContract;
import open.martin.reviewsystem.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserAccountService implements UserAccountServiceContract {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public void createUserAccount() {

    }

    @Override
    public void updateUserAccountDetails() {

    }

    @Override
    public void getUserAccountById() {

    }

    @Override
    public void deleteUserAccountById() {

    }

    @Override
    public void deleteUserAccountByName() {

    }
}
