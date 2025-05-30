package toiletgo.activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.dto.ReviewDto;
import toiletgo.activities.entity.Review;
import toiletgo.activities.repository.ReviewRepository;
import toiletgo.activities.entity.Toilet;
import toiletgo.user.entity.User;
import toiletgo.activities.repository.ToiletRepository;
import toiletgo.user.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ToiletRepository toiletRepository;
    // 리뷰 남기기
    @PostMapping("/api/reviews/create")
    public ResponseEntity<String> createReview(@RequestBody ReviewDto reviewDto){
        try{
            User user = userRepository.findById(reviewDto.getUserId()).orElse(null);
            Toilet toilet = toiletRepository.findById(reviewDto.getToiletId()).orElse(null);
            if (toilet == null || user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 유저 또는 화장실 ID입니다.");
            }

            Review review = reviewDto.toEntity(user, toilet);
            reviewRepository.save(review);

            List<Review> reviews = reviewRepository.findByToilet_ToiletId(reviewDto.getToiletId());
            double count = 0;
            for(Review r : reviews){
                count += r.getRating();
            }
            toilet.setRating(BigDecimal.valueOf(count / reviews.size()));
            toiletRepository.save(toilet);
            return ResponseEntity.status(HttpStatus.CREATED).body("리뷰가 성공적으로 등록되었습니다.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록 중 오류가 발생했습니다.");
        }
    }

    @PostMapping("/api/reviews/get")
    public ResponseEntity<List<ReviewDto>> getReview(@RequestBody ReviewDto reviewDto) {
        List<Review> reviews = reviewRepository.findByToilet_ToiletId(reviewDto.getToiletId());
        if (reviews.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        List<ReviewDto> reviewDtos = reviews.stream()
                .map(ReviewDto::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(reviewDtos);
    }

    //admin
    @PostMapping("/api/admin/review/delete")
    public ResponseEntity<String> deleteReport(@RequestBody ReviewDto reviewDto) {
        try {
            Review review = reviewRepository.findById(reviewDto.getReviewId()).orElse(null);
            if (review == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 유저가 존재하지 않습니다.");
            }
            reviewRepository.delete(review);

            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료 처리되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("미션 완료 처리 중 오류 발생: " + e.getMessage());
        }

    }
}

