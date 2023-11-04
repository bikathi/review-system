package open.martin.reviewsystem.type;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Long entityId;

    @Column(name = "entity_name", unique = true, nullable = false)
    @Size(min = 1, max = 255)
    @Basic
    private String entityName;

    @Column(name = "entity_description", nullable = false)
    @Basic
    @Size(min = 1, max = 2000)
    private String entityDescription;

    @Temporal(TemporalType.DATE)
    private LocalDate datePosted;

    @Column(name = "preview_url", unique = true, nullable = false)
    @Size(min = 1, max = 200)
    private String previewUrl;
}
