package open.martin.reviewsystem.contract.service;

import open.martin.reviewsystem.entity.SystemRole;
import open.martin.reviewsystem.type.Role;

public interface SystemRoleServiceContract {
    SystemRole findSystemRoleByName(Role role);
}
