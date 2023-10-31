package open.martin.reviewsystem.repository;

import open.martin.reviewsystem.entity.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRoleRepository extends JpaRepository<SystemRole, Integer> {
}
