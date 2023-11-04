package open.martin.reviewsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import open.martin.reviewsystem.type.ReviewEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="company_entity", uniqueConstraints = {
    @UniqueConstraint(columnNames = "entity_name"),
    @UniqueConstraint(columnNames = "preview_url")
})
@EqualsAndHashCode(callSuper = true)
public class Company extends ReviewEntity implements Serializable {
    @Serial
    @Transient
    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CompanyReview> reviews = new HashSet<>();
}
