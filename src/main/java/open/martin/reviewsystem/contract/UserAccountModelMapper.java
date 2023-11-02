package open.martin.reviewsystem.contract;

import open.martin.reviewsystem.entity.SystemRole;
import open.martin.reviewsystem.entity.UserAccount;
import open.martin.reviewsystem.payload.UserAccountPayload;
import open.martin.reviewsystem.type.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class UserAccountModelMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SystemRoleServiceContract systemRoleService;

    public final UserAccountPayload convertUserAccountEntityToPayload(UserAccount userAccount) {
        UserAccountPayload payload = modelMapper.map(userAccount, UserAccountPayload.class);
        payload.setDateJoined(userAccount.getDateJoined().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        return payload;
    }

    public final UserAccount convertPayloadToUserAccountEntity(UserAccountPayload payload) {
        UserAccount userAccount = modelMapper.map(payload, UserAccount.class);

        // assign roles if they exist
        if(payload.getRoles() != null) {
            for(String role : payload.getRoles()) {
                switch(role) {
                    case "ROLE_COMMENTER" -> {
                        SystemRole systemRole = systemRoleService.findSystemRoleByName(Role.ROLE_COMMENTER);
                        userAccount.getRoles().add(systemRole);
                    }

                    case "ROLE_REVIEWER" -> {
                        SystemRole systemRole = systemRoleService.findSystemRoleByName(Role.ROLE_REVIEWER);
                        userAccount.getRoles().add(systemRole);
                    }

                    case "ROLE_ADMIN" -> {
                        SystemRole systemRole = systemRoleService.findSystemRoleByName(Role.ROLE_ADMIN);
                        userAccount.getRoles().add(systemRole);
                    }
                }
            }
        }

        // set the date joined
        if(payload.getDateJoined() != null) {
            userAccount.setDateJoined(LocalDate.parse(payload.getDateJoined(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

        return userAccount;
    }
}
