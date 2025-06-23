package toiletgo.activities.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import toiletgo.activities.dto.ReviewDto;
import toiletgo.activities.entity.Review;
import toiletgo.activities.entity.Toilet;
import toiletgo.activities.repository.ReviewRepository;
import toiletgo.user.entity.User;
import toiletgo.user.service.JwtService;
import toiletgo.user.service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final ToiletService toiletService;

    // 생성자 주입: 순환 고리 아닌 의존성만
    @Autowired
    public ReviewService(ReviewRepository reviewRepository,
                         UserService userService,
                         ToiletService toiletService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.toiletService = toiletService;
    }

    // 순환 고리인 MissionService 만 @Lazy 세터 주입
    private MissionService missionService;

    @Autowired
    public void setMissionService(@Lazy MissionService missionService) {
        this.missionService = missionService;
    }

    public void createReview(ReviewDto dto) {
        User user = userService.getUserEntity(dto.getUserId());
        Toilet toilet = toiletService.getToiletEntity(dto.getToiletId());

        Review review = dto.toEntity(user, toilet);
        reviewRepository.save(review);

        user.setUserPoint(user.getUserPoint()+10);

        List<Review> all = reviewRepository.findByToilet_ToiletId(dto.getToiletId());
        double total = all.stream().mapToDouble(Review::getRating).sum();
        int count = all.size();
        BigDecimal avg = BigDecimal.valueOf(total / count);

        toiletService.updateRatingAndCount(dto.getToiletId(), avg, count);

        missionService.updateMission1Progress(user);
        if (toilet.getRating().compareTo(BigDecimal.valueOf(3)) >= 0) {
            missionService.updateMission4Progress(user);
        } else {
            missionService.updateMission5Progress(user);
        }
        if (toilet.getHasBidet()) {
            missionService.updateMission9Progress(user);
        }
    }

    public List<ReviewDto> getReviewsByToiletId(Long toiletId) {
        return reviewRepository.findByToilet_ToiletId(toiletId).stream()
                .map(ReviewDto::toDto)
                .collect(Collectors.toList());
    }

    public void deleteReview(Long reviewId) {
        Review r = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("해당 리뷰가 없습니다."));
        reviewRepository.delete(r);
    }

    public int countReviewsByUserId(String userId) {
        return reviewRepository.countByUser_UserId(userId);
    }
}
