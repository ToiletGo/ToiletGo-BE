package toiletgo.activities.entity;

import jakarta.persistence.*;
import lombok.*;
import toiletgo.user.entity.*;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name="review_id")
    private Long reviewId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "review")
    private List<Report> reports;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "toilet_id", nullable = false)
    private Toilet toilet;



    @Column(nullable = true, name="rating")
    private Integer rating;  // 0.0 ~ 5.0

    @Column(length = 500, name = "comment")
    private String comment;

    @Column(name="report")
    private Integer report;

    @Column(nullable = false, name = "review_at")
    private LocalDateTime reviewAt;

    public Review(User user, Toilet toilet, Integer rating, String comment, LocalDateTime reviewAt) {
        this.user = user;
        this.toilet = toilet;
        this.rating = rating;
        this.comment = comment;
        this.reviewAt = reviewAt;
    }


}
