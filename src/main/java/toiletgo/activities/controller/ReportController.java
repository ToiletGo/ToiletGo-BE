package toiletgo.activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toiletgo.activities.dto.ReportDto;
import toiletgo.activities.entity.Report;
import toiletgo.activities.entity.Review;
import toiletgo.activities.repository.ReportRepository;
import toiletgo.activities.repository.ReviewRepository;
import toiletgo.user.entity.Toilet;
import toiletgo.user.entity.User;
import toiletgo.user.repository.ToiletRepository;
import toiletgo.user.repository.UserRepository;

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
}
