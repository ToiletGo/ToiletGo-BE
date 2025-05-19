package toiletgo.activities.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;
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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "toilet_id", nullable = false)
    private Toilet toilet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "review")
    private List<Report> reports;

    @Column(nullable = false, name="rating")
    private Integer rating;  // 0.0 ~ 5.0

    @Column(length = 500, name = "comment")
    private String comment;

    @Column(name="report")
    private Integer report;

    @Column(nullable = false, name = "review_at")
    private LocalDateTime reviewAt;
}
