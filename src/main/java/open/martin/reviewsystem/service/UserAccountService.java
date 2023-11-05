package open.martin.reviewsystem.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import open.martin.reviewsystem.contract.service.UserAccountServiceContract;
import open.martin.reviewsystem.entity.UserAccount;
import open.martin.reviewsystem.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserAccountService implements UserAccountServiceContract {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @Override
    public void updateUserAccountDetails(UserAccount userAccount) throws EntityNotFoundException {
        UserAccount existingUserAccount = userAccountRepository.findById(userAccount.getAccountId()).orElseThrow(
            () -> new EntityNotFoundException("Account with accountId " + userAccount.getAccountId() + " not found")
        );

        // TODO: update the user account with necessary details

        // save the user
        userAccountRepository.save(existingUserAccount);
    }

    @Override
    public UserAccount getUserAccountById(Long accountId) throws EntityNotFoundException {
        UserAccount existingUserAccount = userAccountRepository.findById(accountId).orElseThrow(
            () -> new EntityNotFoundException("User of accountId " + accountId + " not found")
        );

        return existingUserAccount;
    }

    @Override
    public void deleteUserAccountById(Long accountId) {
        userAccountRepository.deleteById(accountId);
    }

    @Override
    public void deleteUserAccountByUsername(String username) {
        userAccountRepository.deleteByUsername(username);
    }
}
