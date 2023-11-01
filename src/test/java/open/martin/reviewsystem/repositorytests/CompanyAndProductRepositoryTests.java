package open.martin.reviewsystem.repositorytests;

import open.martin.reviewsystem.entity.Company;
import open.martin.reviewsystem.repository.CompanyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
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
    void ensureSavingMalformedCompanyThrowsException(TestInfo testInfo) {
        System.out.println("Running test: " + testInfo.getDisplayName());

        testCompany.setEntityName(null);
        testCompany.setEntityDescription(null);

        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            companyRepository.save(testCompany);
        });
    }

    @Test
    @DisplayName(value = "Test updating an existing Company in DB")
    void testUpdatingCompany(TestInfo testInfo) {
        System.out.println("Running test: " + testInfo.getDisplayName());

        // first save a new Company
        companyRepository.save(testCompany);

        // find the Company in the DB, update something(s) about it and save it again
        List<Company> companyList = companyRepository.findAll();
        Company existingCompany = companyList.stream().findFirst().orElse(null);
        org.junit.jupiter.api.Assertions.assertNotNull(existingCompany); // there should be at least the company we saved

        Long companyId = existingCompany.getEntityId();

        // update sth in the company and save it to the DB
        existingCompany.setEntityName("Updated entity name");
        existingCompany.setEntityDescription("Updated entity description");
        companyRepository.save(existingCompany);

        // read from DB and confirm the changes
        Company updatedCompany = companyRepository.findById(companyId).orElse(null);
        org.junit.jupiter.api.Assertions.assertNotNull(updatedCompany);

        Assertions.assertThat(updatedCompany.getEntityId()).isEqualTo(companyId);
        Assertions.assertThat(updatedCompany.getEntityName()).isEqualTo("Updated entity name");
        Assertions.assertThat(updatedCompany.getEntityDescription()).isEqualTo("Updated entity description");
    }
}
