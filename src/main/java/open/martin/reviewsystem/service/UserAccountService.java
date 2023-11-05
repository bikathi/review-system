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
        // should never actually happen but in case it does
        UserAccount existingUserAccount = userAccountRepository.findById(userAccount.getAccountId()).orElseThrow(
            () -> new EntityNotFoundException("Account with accountId " + userAccount.getAccountId() + " not found")
        );

        // update the user account with necessary details
        existingUserAccount.setFirstName(userAccount.getFirstName());
        existingUserAccount.setLastName(userAccount.getLastName());
        existingUserAccount.setEmail(userAccount.getEmail());
        existingUserAccount.setUsername(userAccount.getUsername());

        // save the user
        userAccountRepository.save(existingUserAccount);
    }

    @Override
    public UserAccount getUserAccountById(Long accountId) throws EntityNotFoundException {
        return userAccountRepository.findById(accountId).orElseThrow(
            () -> new EntityNotFoundException("Account by accountId " + accountId + " not found")
        );
    }

    @Override
    public UserAccount getUserAccountByUsername(String username) {
        return userAccountRepository.findByUsername(username).orElseThrow(
            () -> new EntityNotFoundException("Account by username " + username + " not found")
        );
    }

    @Override
    public void deleteUserAccountById(Long accountId) {
        userAccountRepository.deleteById(accountId);
    }
}
