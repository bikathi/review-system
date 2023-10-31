package open.martin.reviewsystem.repository;

import open.martin.reviewsystem.entity.CompanyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyReviewRepository extends JpaRepository<CompanyReview, Long> {
}
