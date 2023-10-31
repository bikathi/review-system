package open.martin.reviewsystem.repositorytests;

import open.martin.reviewsystem.entity.Company;
import open.martin.reviewsystem.repository.CompanyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyRepositoryTests {
    @Autowired
    CompanyRepository companyRepository;

    Company testCompany;

    @BeforeEach
    void initCompany() {
        testCompany = new Company();
        testCompany.setEntityId(1L);
        testCompany.setEntityName("Test Company");
        testCompany.setDatePosted(new Date());
        testCompany.setPreviewUrl("test preview URL");
        testCompany.setReviews(null);
        testCompany.setEntityDescription("Test description");
    }

    @Test
    @DisplayName(value = "Test company repository CRD")
    void testCreateReadDelete(TestInfo testInfo) {
        System.out.println("Running test: " + testInfo.getDisplayName());

        // confirm CREATE works
        companyRepository.save(testCompany);

        // test READ works
        List<Company> company = companyRepository.findAll();
        Assertions.assertThat(company).extracting(Company::getEntityName).contains("Test Company");

        // test DELETE works
        companyRepository.deleteAll();
        Assertions.assertThat(companyRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName(value = "Test inserting incomplete Company throws Exception")
    void ensureSavingIncompleteCompanyThrowsException(TestInfo testInfo) {
        System.out.println("Running test: " + testInfo.getDisplayName());

        testCompany.setEntityName(null);
        testCompany.setEntityDescription(null);

        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            companyRepository.save(testCompany);
        });
    }
}
