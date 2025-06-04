package toiletgo.activities.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import toiletgo.activities.dto.ReportDto;
import toiletgo.activities.entity.Report;
import toiletgo.activities.entity.Review;
import toiletgo.activities.entity.Toilet;
import toiletgo.activities.repository.ReportRepository;
import toiletgo.activities.repository.ReviewRepository;
import toiletgo.activities.repository.ToiletRepository;   // 이건 제거해도 됩니다.
import toiletgo.user.entity.User;
import toiletgo.user.service.UserService;
import toiletgo.activities.service.ToiletService;          // ToiletService를 주입
import toiletgo.activities.repository.ReviewRepository;
import toiletgo.activities.repository.ToiletRepository;
import toiletgo.activities.repository.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserService userService;            // 변경
    private final ToiletService toiletService;        // 변경
    private final ReviewRepository reviewRepository;

    /**
     * 새로운 신고를 생성하고 저장
     */
    public void createReport(ReportDto reportDto) {
        // User 조회 (UserService를 통해)
        User user = userService.getUserEntity(reportDto.getUserId());

        // Toilet 조회 (ToiletService를 통해)
        Toilet toilet = toiletService.getToiletEntity(reportDto.getToiletId());

        // Review 조회: 아직 ReviewService에 getReviewEntity 메소드가 없다면, 직접 reviewRepository 사용
        Review review = reviewRepository.findById(reportDto.getReviewId())
                .orElseThrow(() -> new EntityNotFoundException("해당 리뷰가 존재하지 않습니다."));

        // DTO → Entity 변환 후 저장
        Report report = reportDto.toEntity(user, toilet, review);
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