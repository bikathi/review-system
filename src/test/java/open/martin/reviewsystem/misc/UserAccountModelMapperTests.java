package open.martin.reviewsystem.misc;

import open.martin.reviewsystem.contract.service.SystemRoleServiceContract;
import open.martin.reviewsystem.controller.UserAccountController;
import open.martin.reviewsystem.entity.SystemRole;
import open.martin.reviewsystem.entity.UserAccount;
import open.martin.reviewsystem.payload.UserAccountPayload;
import open.martin.reviewsystem.type.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserAccountModelMapperTests {
    @InjectMocks
    private UserAccountController userAccountController;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private SystemRoleServiceContract systemRoleServiceContract;

    UserAccount testUserAccount;
    UserAccountPayload testPayload = new UserAccountPayload();

    @BeforeAll
    static void setup() {
        MockitoAnnotations.openMocks(UserAccountModelMapperTests.class);
    }

    @BeforeEach
    void preTest() {
        testUserAccount = new UserAccount(
            1L,
            "John",
            "Doe",
            "@johndoe",
            "johndoe@gmail.com",
            LocalDate.parse("01-11-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
            LocalDateTime.of(2023, 11, 5, 11, 58),
            "testPassword",
            false,
            new HashSet<>(List.of(new SystemRole(1, Role.ROLE_COMMENTER), new SystemRole(2, Role.ROLE_REVIEWER))),
            null,
            null
        );

        testPayload.setFirstName("John");
        testPayload.setLastName("Doe");
        testPayload.setUserName("@johndoe");
        testPayload.setEmail("johndoe@gmail.com");
        testPayload.setDateJoined("01-11-2023");
        testPayload.setRoles(new ArrayList<>(List.of("ROLE_COMMENTER", "ROLE_REVIEWER")));
    }

    @Test
    void testConversionFromEntityToPayload() {
        Mockito.when(modelMapper.map(testUserAccount, UserAccountPayload.class)).thenReturn(testPayload);

        UserAccountPayload outputPayload = userAccountController.convertUserAccountEntityToPayload(testUserAccount);

        Assertions.assertThat(outputPayload.getUserName()).isEqualTo(testPayload.getUserName());
        Assertions.assertThat(outputPayload.getDateJoined()).isEqualTo(testPayload.getDateJoined());
        Assertions.assertThat(outputPayload.getRoles().size()).isEqualTo(testPayload.getRoles().size());
        Assertions.assertThat(outputPayload.getRoles().get(0)).isEqualTo(testPayload.getRoles().get(0));
        Assertions.assertThat(outputPayload.getPassword()).isNull();
    }

    @Test
    void testConversionFromPayloadToEntity() {
        Mockito.when(modelMapper.map(testPayload, UserAccount.class)).thenReturn(testUserAccount);

        UserAccount outputAccount = userAccountController.convertPayloadToUserAccountEntity(testPayload);
        Assertions.assertThat(outputAccount.getFirstName()).isEqualTo(testUserAccount.getFirstName());
        Assertions.assertThat(outputAccount.getRoles().size()).isEqualTo(testUserAccount.getRoles().size());
        org.junit.jupiter.api.Assertions.assertTrue(outputAccount.getRoles().contains(new SystemRole(1, Role.ROLE_COMMENTER)));
    }
}
