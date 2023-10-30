package open.martin.reviewsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import open.martin.reviewsystem.type.ReviewEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="company_entity")
@EqualsAndHashCode(callSuper = true)
public class CompanyEntity extends ReviewEntity implements Serializable {
    @Serial
    @Transient
    private static final long serialVersionUID = UUID.randomUUID().getLeastSignificantBits();
}
