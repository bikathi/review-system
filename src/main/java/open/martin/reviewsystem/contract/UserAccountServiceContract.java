package open.martin.reviewsystem.contract;

public interface UserAccountServiceContract {
    void createUserAccount();
    void updateUserAccountDetails();
    void getUserAccountById();
    void deleteUserAccountById();
    void deleteUserAccountByName();
}
