package open.martin.reviewsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="app_user")
public class UserAccount implements Serializable {
    @Serial
    @Transient
    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "date_joined")
    @Temporal(TemporalType.DATE)
    private String dateJoined;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductReview> productReviews;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CompanyReview> companyReviews;
}
