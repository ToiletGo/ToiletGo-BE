package toiletgo.activities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import toiletgo.user.entity.User;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "report_id")
    private Integer reportId;

    @ManyToOne // optional = true
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne // optional = true
    @JsonIgnore
    @JoinColumn(name="review_id", nullable = false)
    private Review review;

    @Column(name = "report_type", nullable = false)
    private String reportType;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(nullable = false, name="is_processed")
    private boolean isProcessed;

    @Column(nullable = false, name="report_at")
    private LocalDateTime reportAt;

}

