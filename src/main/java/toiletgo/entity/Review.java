package toiletgo.entity;

import jakarta.persistence.*;
import lombok.*;
import toiletgo.dto.ReviewDto;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @JoinColumn(name = "user_id", nullable = false)
    private String userId;

    @JoinColumn(name = "toilet_id", nullable = false)
    private Long toiletId;

    @Column(nullable = false)
    private Double rating;  // 0.0 ~ 5.0

    @Column(length = 500)
    private String comment;

    @Column(nullable = false)
    private Timestamp createdAt;

    public ReviewDto toDto() {
        return ReviewDto.builder()
                .reviewId(this.getReviewId())
                .userId(this.getUserId())
                .toiletId(this.getToiletId())
                .rating(this.getRating())
                .comment(this.getComment())
                .createdAt(this.getCreatedAt())
                .build();
    }
}
