package toiletgo.activities.dto;

import lombok.Builder;
import lombok.Data;
import toiletgo.activities.entity.Report;

import java.time.LocalDateTime;

@Data
@Builder
public class ReportDto {

    private Integer reportId;

    private String userId;       // User → user.getUserId()
    private Long reviewId;       // Review → review.getReviewId()

    private String reportType;
    private String description;

    private boolean isProcessed;
    private LocalDateTime reportAt;

    public static ReportDto toDto(Report report) {
        return ReportDto.builder()
                .reportId(report.getReportId())
                .userId(report.getUser() != null ? report.getUser().getUserId() : null)
                .reviewId(report.getReview() != null ? report.getReview().getReviewId() : null)
                .reportType(report.getReportType())
                .description(report.getDescription())
                .isProcessed(report.isProcessed())
                .reportAt(report.getReportAt())
                .build();
    }
}