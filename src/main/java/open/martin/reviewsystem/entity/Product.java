package open.martin.reviewsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import open.martin.reviewsystem.type.ReviewEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="product_entity", uniqueConstraints = {
    @UniqueConstraint(columnNames = "entity_name"),
    @UniqueConstraint(columnNames = "preview_url")
})
@EqualsAndHashCode(callSuper = true)
public class Product extends ReviewEntity implements Serializable {
    @Serial
    @Transient
    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductReview> productReviews;
}
