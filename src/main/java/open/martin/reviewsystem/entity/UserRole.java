package open.martin.reviewsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import open.martin.reviewsystem.type.Role;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="system_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    @Size(min = 4, max = 25)
    private Role roleName;
}
