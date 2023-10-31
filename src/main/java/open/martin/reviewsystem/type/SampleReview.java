package open.martin.reviewsystem.type;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class SampleReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(name = "date_posted", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datePosted;

    @Column(name = "date_modified")
    @Temporal(TemporalType.DATE)
    private Date dateModified;

    @Column(name = "review_comment", nullable = false)
    @Basic
    private String reviewComment;

    @Column(name = "rating", nullable = false)
    @PositiveOrZero
    @Max(5) // rating 0 - 5
    private Byte rating;
}
