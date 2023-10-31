package open.martin.reviewsystem;

import lombok.extern.slf4j.Slf4j;
import open.martin.reviewsystem.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewSystemApplicationTests {
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	CompanyReviewRepository companyReviewRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductReviewRepository productReviewRepository;

	@Autowired
	SystemRoleRepository systemRoleRepository;

	@Autowired
	UserAccountRepository userAccountRepository;

	@Test@DisplayName(value = "Test application bootstrapping")
	void contextLoads(TestInfo testInfo) {
		System.out.println("Running test: " + testInfo.getDisplayName());

		// test that the repository layers work fine
		Assertions.assertThat(companyRepository).isNotNull();
		Assertions.assertThat(companyReviewRepository).isNotNull();
		Assertions.assertThat(productRepository).isNotNull();
		Assertions.assertThat(productReviewRepository).isNotNull();
		Assertions.assertThat(systemRoleRepository).isNotNull();
		Assertions.assertThat(userAccountRepository).isNotNull();
	}

}
