package toiletgo.activities.dto;

import lombok.Builder;
import lombok.Data;
import toiletgo.activities.entity.*;
import toiletgo.user.entity.*;

import java.time.LocalDateTime;

@Data
@Builder
public class ReviewDto {

    private Long reviewId;

    private String userId;
    private Long toiletId;

    private Integer rating;
    private String comment;
    private Integer report;
    private LocalDateTime reviewAt;

    public static ReviewDto toDto(Review review) {
        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .userId(review.getUser() != null ? review.getUser().getUserId() : null)
                .toiletId(review.getToilet() != null ? review.getToilet().getToiletId() : null)
                .rating(review.getRating())
                .comment(review.getComment())
                .report(review.getReport())
                .reviewAt(review.getReviewAt())
                .build();
    }

    public Review toEntity(User user, Toilet toilet) {
        return Review.builder()
                .user(user)  // 이미 존재하는 유저 객체 주입
                .toilet(toilet)  // 이미 존재하는 화장실 객체 주입
                .rating(this.rating)
                .comment(this.comment)
                .report(this.report != null ? this.report : 0)
                .reviewAt(LocalDateTime.now())
                .build();
    }
}
