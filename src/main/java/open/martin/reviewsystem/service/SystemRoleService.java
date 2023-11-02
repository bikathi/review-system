package open.martin.reviewsystem.service;

import lombok.extern.slf4j.Slf4j;
import open.martin.reviewsystem.contract.SystemRoleServiceContract;
import open.martin.reviewsystem.entity.SystemRole;
import open.martin.reviewsystem.exception.InvalidOperationException;
import open.martin.reviewsystem.repository.SystemRoleRepository;
import open.martin.reviewsystem.type.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SystemRoleService implements SystemRoleServiceContract {
    @Autowired
    private SystemRoleRepository systemRoleRepository;

    @Override
    public SystemRole findSystemRoleByName(Role role) {
        return systemRoleRepository.findByRoleName(role).orElseThrow(() -> new InvalidOperationException("Operation on undefined role"));
    }
}
