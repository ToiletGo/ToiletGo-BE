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
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "mission_no")
    private Long missionNo;

    @JsonIgnore
    @JoinColumn(name="mission_id")
    @OneToOne(cascade = CascadeType.ALL)
    private MissionList missionList;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "mission_progress", length = 1024)
    private Integer progress;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt;

    //@ManyToOne // optional = true
   // @JsonIgnore
    //@JoinColumn(name = "user_id", nullable = false)
    //private User user;
}