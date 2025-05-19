package toiletgo.activities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import toiletgo.user.entity.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MissionList {

    @Id
    @Column(name = "mission_id")
    private Long missionId;

    @MapsId
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "mission_progress", length = 1024)
    private Integer progress;

    @Column(name = "is_completed", nullable = false)
    private Boolean missionType;

    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt;

}


// @MapsId  // 이 필드가 missionId를 매핑하고 PK로 사용함