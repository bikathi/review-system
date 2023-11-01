package open.martin.reviewsystem.repository;

import open.martin.reviewsystem.entity.SystemRole;
import open.martin.reviewsystem.type.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemRoleRepository extends JpaRepository<SystemRole, Integer> {
    Optional<SystemRole> findByName(Role role);
}
