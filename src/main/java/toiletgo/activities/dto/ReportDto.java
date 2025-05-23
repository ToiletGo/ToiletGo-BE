package toiletgo.activities.dto;

import toiletgo.activities.entity.Report;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportDto {
    private Long reportId;
    private String reporterId;
    private String targetType;
    private Long targetId;
    private String description;
    private String createdAt;

    public ReportDto toEntity(Report report) {
        return ReportDto.builder()
                .reportId(report.getReportId())
                .reporterId(report.getReporter().getUserId())
                .targetType(report.getTargetType())
                .targetId(report.getTargetId())
                .description(report.getDescription())
                .createdAt(report.getCreatedAt())
                .build();
    }
}
