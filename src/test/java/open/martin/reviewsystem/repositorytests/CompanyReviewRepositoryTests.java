package open.martin.reviewsystem.repositorytests;

import open.martin.reviewsystem.entity.Company;
import open.martin.reviewsystem.entity.CompanyReview;
import open.martin.reviewsystem.entity.SystemRole;
import open.martin.reviewsystem.entity.UserAccount;
import open.martin.reviewsystem.repository.CompanyReviewRepository;
import open.martin.reviewsystem.type.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyReviewRepositoryTests {
    @Autowired
    CompanyReviewRepository companyReviewRepository;

    CompanyReview testCompanyReview;
    Company testCompany;

    UserAccount testUserAccount;

    SystemRole testUserRoles;

    @BeforeEach
    void initParams() {
        Set<CompanyReview> companyReviews = new HashSet<>();

        // create a sample review
        testCompanyReview = new CompanyReview();
        testCompanyReview.setReviewId(1L);
        testCompanyReview.setDatePosted(new Date());
        testCompanyReview.setDateModified(null);
        testCompanyReview.setReviewComment("Test review comment");
        testCompanyReview.setRating(3);
        testCompanyReview.setCompany(testCompany);

        // create a new company to review
        testCompany = new Company();
        testCompany.setEntityId(1L);
        testCompany.setEntityName("Test Company");
        testCompany.setDatePosted(new Date());
        testCompany.setPreviewUrl("test preview URL");
        testCompany.setReviews(new HashSet<>(List.of(testCompanyReview)));
        testCompany.setEntityDescription("Test description");

        // create a test account for the reviewer
        testUserAccount = UserAccount.builder()
            .accountId(1L)
            .firstName("John")
            .lastName("Doe")
            .username("@johndoe")
            .email("johndoe@gmail.com")
            .dateJoined(new Date())
            .password("testPassword")
            .roles(new HashSet<>(List.of(
                new SystemRole(1, Role.ROLE_REVIEWER),
                new SystemRole(2, Role.ROLE_COMMENTER))))
            .companyReviews(new HashSet<>(List.of(testCompanyReview)))
            .productReviews(null)
        .build();
    }

    @Test
    void confirmPersonMakingReviewHasCommenterRole() {
        Assertions.assertThat(testUserAccount.getRoles()).isNotNull();
        Assertions.assertThat(testUserAccount.getRoles().stream().toList()).extracting(SystemRole::getRoleName).contains(Role.ROLE_COMMENTER);
    }

    @Test
    @DisplayName(value = "Test company review repository CRD")
    void testCreateReadDeleteReview(TestInfo testInfo) {
        System.out.println("Running test: " + testInfo.getDisplayName());
    }

    @Test
    @DisplayName(value = "Test inserting malformed company review")
    void ensureSavingMalformedCompanyReviewThrowsException(TestInfo testInfo) {
        System.out.println("Running test: " + testInfo.getDisplayName());
    }
}
