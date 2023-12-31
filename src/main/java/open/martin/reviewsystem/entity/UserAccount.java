package open.martin.reviewsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name ="app_user", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
})
public class UserAccount implements Serializable {
    @Serial
    @Transient
    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "first_name")
    @Size(min = 1, max = 40)
    @Basic
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 1, max = 40)
    @Basic
    private String lastName;

    @Column(name = "username", nullable = false)
    @Size(min = 1, max = 30)
    @Basic
    private String username;

    @Column(name = "email", nullable = false)
    @Size(min = 1, max = 30)
    @Basic
    @Email
    private String email;

    @Column(name = "date_joined", nullable = false)
    private LocalDate dateJoined;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "account_locked", nullable = false)
    private Boolean accountLocked;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "account_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<SystemRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<ProductReview> productReviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<CompanyReview> companyReviews;
}
