package toiletgo.activities.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import toiletgo.activities.dto.ReportDto;
import toiletgo.activities.entity.Report;
import toiletgo.activities.entity.Review;
import toiletgo.activities.entity.Toilet;
import toiletgo.activities.repository.ReportRepository;
import toiletgo.activities.repository.ReviewRepository;
import toiletgo.user.entity.User;
import toiletgo.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserService userService;            // 변경
    private final ToiletService toiletService;        // 변경
    private final MissionService missionService;
    private final ReviewRepository reviewRepository;

    /**
     * 새로운 신고를 생성하고 저장
     */
    public void createReport(ReportDto reportDto) {

        User user = userService.getUserEntity(reportDto.getUserId());

        Toilet toilet = null;
        Review review = null;

        if (reportDto.getReviewId() != null && reportDto.getToiletId() != null) {
            throw new IllegalArgumentException("리뷰 신고와 화장실 신고는 동시에 할 수 없습니다.");
        }

        if (reportDto.getReviewId() != null) {
            review = reviewRepository.findById(reportDto.getReviewId())
                    .orElseThrow(() -> new EntityNotFoundException("해당 리뷰가 존재하지 않습니다."));
        } else if (reportDto.getToiletId() != null) {
            toilet = toiletService.getToiletEntity(reportDto.getToiletId());
        } else {
            throw new IllegalArgumentException("리뷰 ID나 화장실 ID 중 하나는 반드시 존재해야 합니다.");
        }

        // DTO → Entity 변환 후 저장
        Report report = reportDto.toEntity(user, toilet, review);
        if (toilet != null) {
            userService.minusUserPoint(toilet.getUserId());
            missionService.completeMission10(user.getUserId());
        } else{
            userService.minusUserPoint(review.getUser().getUserId());
        }
        reportRepository.save(report);
    }

    /**
     * 모든 신고 내역 조회
     */
    public List<ReportDto> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream()
                .map(Report::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 특정 신고를 삭제
     */
    public void deleteReport(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("해당 신고내역이 존재하지 않습니다."));
        reportRepository.delete(report);
    }
}