package toiletgo.activities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import toiletgo.activities.dto.ReportDto;
import toiletgo.user.entity.Toilet;
import toiletgo.user.entity.User;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "report_id")
    private Long reportId;

    @ManyToOne // optional = true
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne // optional = true
    @JsonIgnore
    @JoinColumn(name = "toilet_id", nullable = false)
    private Toilet toilet;

    @ManyToOne // optional = true
    @JsonIgnore
    @JoinColumn(name="review_id", nullable = false)
    private Review review;

    @Column(name = "report_type", nullable = false)
    private String reportType;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(nullable = false, name="is_processed")
    private boolean isProcessed;

    @Column(nullable = false, name="report_at")
    private LocalDateTime reportAt;

    public ReportDto toDto() {
        return ReportDto.builder()
                .userId(this.user.getUserId())
                .toiletId(this.toilet != null ? this.toilet.getToiletId() : null)
                .reviewId(this.review != null ? this.review.getReviewId() : null)
                .reportType(this.reportType)
                .description(this.description)
                .isProcessed(this.isProcessed)
                .reportAt(this.reportAt)
                .build();
    }
}

