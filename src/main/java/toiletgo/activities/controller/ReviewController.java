package toiletgo.activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.dto.ReviewDto;
import toiletgo.activities.entity.Review;
import toiletgo.activities.repository.ReviewRepository;
import toiletgo.user.entity.Toilet;
import toiletgo.user.entity.User;
import toiletgo.user.repository.ToiletRepository;
import toiletgo.user.repository.UserRepository;

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
    @PostMapping("/api/reviews/{toiletId}")
    public ResponseEntity<String> createReview(@RequestBody ReviewDto reviewDto){
        try{
            User user = userRepository.findById(reviewDto.getUserId()).orElse(null);
            Toilet toilet = toiletRepository.findById(reviewDto.getToiletId()).orElse(null);
            if (toilet == null || user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 유저 또는 화장실 ID입니다.");
            }

            Review review = reviewDto.toEntity(user, toilet);
            reviewRepository.save(review);

            return ResponseEntity.status(HttpStatus.CREATED).body("리뷰가 성공적으로 등록되었습니다.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/api/toilets/{toiltId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReview(@PathVariable Long toiletId) {
        List<Review> reviews = reviewRepository.findByToilet_ToiletId(toiletId);
        if (reviews.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        List<ReviewDto> reviewDtos = reviews.stream()
                .map(ReviewDto::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(reviewDtos);
    }
}

