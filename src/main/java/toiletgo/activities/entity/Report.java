package toiletgo.activities.entity;

import jakarta.persistence.*;
import lombok.*;
import toiletgo.activities.dto.ReportDto;
import toiletgo.user.entity.User;

import java.time.LocalDateTime;

/**
 * Report Entity 클래스
 */

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

    public Report(User user, Review review, Toilet toilet, String reportType, String description, boolean isProcessed, LocalDateTime reportAt ) {
        this.user = user;
        this.review = review;
        this.toilet = toilet;
        this.reportType = reportType;
        this.description = description;
        this.isProcessed = isProcessed;
        this.reportAt = reportAt;
    }

    @ManyToOne // optional = true
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne // optional = true
    @JoinColumn(name="review_id", nullable = true)
    private Review review;

    @ManyToOne // optional = true
    @JoinColumn(name="toilet_id", nullable = true)
    private Toilet toilet;

    // 신고 사유
    @Column(name = "report_type", nullable = false)
    private String reportType;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name="is_processed")
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

