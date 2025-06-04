package toiletgo.activities.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.dto.ReviewDto;
import toiletgo.activities.service.ReviewService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * POST /api/reviews/create
     * 리뷰 남기기
     */
    @PostMapping("/api/reviews/create")
    public ResponseEntity<String> createReview(@RequestBody ReviewDto reviewDto) {
        try {
            reviewService.createReview(reviewDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("리뷰가 성공적으로 등록되었습니다.");
        }
        // userId 또는 toiletId가 유효하지 않을 때 400 Bad Request
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
        // 미션 업데이트 도중 에러가 발생했을 때 500 Internal Server Error
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("등록 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * POST /api/reviews/get
     * 특정 화장실 ID에 대한 모든 리뷰 조회
     */
    @PostMapping("/api/reviews/get")
    public ResponseEntity<List<ReviewDto>> getReview(@RequestBody ReviewDto reviewDto) {
        Long toiletId = reviewDto.getToiletId();
        List<ReviewDto> dtos = reviewService.getReviewsByToiletId(toiletId);

        if (dtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    /**
     * POST /api/admin/review/delete
     * 관리자용: 특정 리뷰 삭제
     */
    @PostMapping("/api/admin/review/delete")
    public ResponseEntity<String> deleteReview(@RequestBody ReviewDto reviewDto) {
        try {
            reviewService.deleteReview(reviewDto.getReviewId());
            return ResponseEntity.ok("삭제 완료 처리되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("삭제 중 오류 발생: " + e.getMessage());
        }
    }
}