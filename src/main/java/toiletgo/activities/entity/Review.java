package toiletgo.activities.entity;


import jakarta.persistence.*;
import lombok.*;
import toiletgo.user.entity.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "toilet_id", nullable = false)
    private Toilet toilet;

    @Column(nullable = false)
    private Double rating;  // 0.0 ~ 5.0

    @Column(nullable = false)
    private Boolean hasBidet;

    @Column(nullable = false)
    private Boolean hasTissue;

    @Column(length = 500)
    private String comment;

    @Column(nullable = false)
    private String createdAt;
}
