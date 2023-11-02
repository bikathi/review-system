package open.martin.reviewsystem.contract;

import open.martin.reviewsystem.entity.SystemRole;
import open.martin.reviewsystem.type.Role;

public interface SystemRoleServiceContract {
    SystemRole findSystemRoleByName(Role role);
}
