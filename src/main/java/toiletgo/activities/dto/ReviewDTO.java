package toiletgo.activities.dto;

import toiletgo.activities.entity.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDTO {
    private Long reviewId;
    private String userId;
    private Long toiletId;
    private Double rating;
    private Boolean hasBidet;
    private Boolean hasTissue;
    private String comment;
    private String createdAt;

    public ReviewDTO toDto(ReviewDTO review) {
        return ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .userId(review.getUser().getUserId())
                .toiletId(review.getToilet().getToiletId())
                .rating(review.getRating())
                .hasBidet(review.getHasBidet())
                .hasTissue(review.getHasTissue())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
