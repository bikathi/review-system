package open.martin.reviewsystem.contract.service;

import open.martin.reviewsystem.entity.UserAccount;

public interface UserAccountServiceContract {
    UserAccount createUserAccount(UserAccount userAccount);
    void updateUserAccountDetails(UserAccount userAccount);
    UserAccount getUserAccountById(Long accountId);
    void deleteUserAccountById(Long accountId);
    void deleteUserAccountByUsername(String username);
}
