package toiletgo.activities.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import toiletgo.activities.entity.Report;
import toiletgo.activities.entity.Review;
import toiletgo.user.entity.Toilet;
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

    public static ReportDto toDto(Report report) {
        return ReportDto.builder()
                .userId(report.getUser() != null ? report.getUser().getUserId() : null)
                .toiletId(report.getToilet() != null ? report.getToilet().getToiletId() : null)
                .reviewId(report.getReview() != null ? report.getReview().getReviewId() : null)
                .reportType(report.getReportType())
                .description(report.getDescription())
                .isProcessed(report.isProcessed())
                .reportAt(report.getReportAt())
                .build();
    }  private Integer reportId;

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