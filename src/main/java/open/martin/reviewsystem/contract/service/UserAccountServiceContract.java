package open.martin.reviewsystem.contract.service;

public interface UserAccountServiceContract {
    void createUserAccount();
    void updateUserAccountDetails();
    void getUserAccountById();
    void deleteUserAccountById();
    void deleteUserAccountByName();
}
