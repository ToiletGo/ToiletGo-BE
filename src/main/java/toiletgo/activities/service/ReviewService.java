package toiletgo.activities.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import toiletgo.activities.dto.ReviewDto;
import toiletgo.activities.entity.Review;
import toiletgo.activities.repository.ReviewRepository;
import toiletgo.user.entity.User;
import toiletgo.activities.entity.Toilet;
import toiletgo.user.service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;          // UserService로 교체
    private final ToiletService toiletService;      // ToiletService로 교체
    private final MissionService missionService;

    /**
     * 리뷰 생성 및 연관된 화장실 정보 업데이트, 미션 진행도 갱신
     */
    public void createReview(ReviewDto reviewDto) {
        // 1) User 엔티티 조회 (UserService 사용)
        User user = userService.getUserEntity(reviewDto.getUserId());

        // 2) Toilet 엔티티 조회 (ToiletService 사용)
        Toilet toilet = toiletService.getToiletEntity(reviewDto.getToiletId());

        // 3) Review 엔티티로 변환 후 저장
        Review review = reviewDto.toEntity(user, toilet);
        reviewRepository.save(review);

        // 4) 해당 화장실의 모든 리뷰를 다시 불러와 Rating과 Count 계산
        List<Review> reviews = reviewRepository.findByToilet_ToiletId(reviewDto.getToiletId());
        double totalRating = reviews.stream()
                .mapToDouble(Review::getRating)
                .sum();
        int count = reviews.size();
        BigDecimal average = BigDecimal.valueOf(totalRating / count);

        // 5) ToiletRepository 직접 호출 대신, ToiletService의 메소드를 통해 업데이트
        toiletService.updateRatingAndCount(reviewDto.getToiletId(), average, count);

        // 6) 미션 진행도 갱신 (MissionService 사용)
        missionService.updateMission1Progress(user);
    }

    /**
     * 특정 화장실 ID에 대한 모든 리뷰 조회
     */
    public List<ReviewDto> getReviewsByToiletId(Long toiletId) {
        List<Review> reviews = reviewRepository.findByToilet_ToiletId(toiletId);
        return reviews.stream()
                .map(ReviewDto::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 리뷰 삭제
     */
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("해당 리뷰가 존재하지 않습니다."));
        reviewRepository.delete(review);
    }

    public int countReviewsByUserId(String userId) {
        return reviewRepository.countByUser_UserId(userId);
    }
}