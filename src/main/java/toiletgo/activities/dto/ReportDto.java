package toiletgo.activities.dto;

import lombok.Builder;
import lombok.Data;
import toiletgo.activities.entity.Report;
import toiletgo.activities.entity.Review;
import toiletgo.activities.entity.Toilet;
import toiletgo.user.entity.User;

import java.time.LocalDateTime;

@Data
@Builder
public class ReportDto {

    private String userId;
    private Long toiletId;       // Toilet → toilet.getUserId()
    private Long reviewId;       // Review → review.getReviewId()

    private String reportType;
    private String description;

    private boolean isProcessed;
    private LocalDateTime reportAt;

    public Report toEntity(User user, Toilet toilet, Review review) {
        return Report.builder()
                .user(user)
                .toilet(toilet)
                .review(review)
                .reportType(this.reportType)
                .description(this.description)
                .isProcessed(this.isProcessed)
                .reportAt(this.reportAt != null ? this.reportAt : LocalDateTime.now()) // 현재 시각 기본값 처리
                .build();
    }
}