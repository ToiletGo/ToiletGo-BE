package toiletgo.api;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.dto.ReviewDto;
import toiletgo.dto.ToiletDto;
import toiletgo.entity.Review;
import toiletgo.entity.Toilet;
import toiletgo.repository.ReviewRepository;

@RestController
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;
    @PostMapping("/api/reviews/{toiletId}")
    public ResponseEntity<String> createReview(@PathVariable Long toiletId, @RequestBody ReviewDto reviewDto){
        try{
            reviewDto.setToiletId(toiletId);
            Review created = reviewDto.toEntity();
            Review saved = reviewRepository.save(created);
            return ResponseEntity.status(HttpStatus.CREATED).body("리뷰가 성공적으로 등록되었습니다.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/api/toilets/{toiltId}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable Long toiletId){
        Review review = reviewRepository.findById(toiletId).orElse(null);
        if(review != null){
            ReviewDto reviewDto = review.toDto();
            return ResponseEntity.status(HttpStatus.OK).body(reviewDto);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
