package toiletgo.activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.dto.ReportDto;
import toiletgo.activities.entity.Report;
import toiletgo.activities.entity.Review;
import toiletgo.activities.repository.ReportRepository;
import toiletgo.activities.repository.ReviewRepository;
import toiletgo.activities.entity.Toilet;
import toiletgo.user.entity.User;
import toiletgo.activities.repository.ToiletRepository;
import toiletgo.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ToiletRepository toiletRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @PostMapping("/api/report")
    public ResponseEntity<String> reportToilet(@RequestBody ReportDto reportDto){
        try{
            User user = userRepository.findByUserId(reportDto.getUserId()).orElse(null);
            Toilet toilet = toiletRepository.findById(reportDto.getToiletId()).orElse(null);
            Review review = reviewRepository.findById(reportDto.getReviewId()).orElse(null);
            Report report = reportDto.toEntity(user,toilet,review);
            Report saved = reportRepository.save(report);

            return ResponseEntity.status(HttpStatus.CREATED).body("신고가 성공적으로 등록되었습니다.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록 중 오류가 발생했습니다.");
        }
    }

    //Admin
    @GetMapping("/api/admin/reports")
    public ResponseEntity<List<ReportDto>> getReports() {
        List<Report> reports = reportRepository.findAll();
        if(reports.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        List<ReportDto> reportDtoList = reports.stream()
                .map(report -> report.toDto())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(reportDtoList);
    }

    @DeleteMapping("/api/admin/report/delete")
    public ResponseEntity<String> deleteReport(@RequestBody ReportDto reportDto) {
        try {
            Report report = reportRepository.findById(reportDto.getReportId()).orElse(null);
            if (report == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 신고내역이 존재하지 않습니다.");
            }
            reportRepository.delete(report);

            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료 처리되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("미션 완료 처리 중 오류 발생: " + e.getMessage());
        }

    }
}
