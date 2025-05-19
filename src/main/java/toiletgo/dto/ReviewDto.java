package toiletgo.dto;

import lombok.Getter;
import lombok.Setter;
import toiletgo.entity.Review;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
public class ReviewDto {
    private Long reviewId;
    private Long toiletId;
    private String userId;
    private Double rating;
    private String comment;
    private Timestamp createdAt;

    public Review toEntity(){
        return Review.builder()
                .userId(userId)
                .toiletId(toiletId)
                .rating(rating)
                .comment(comment)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }



}
