package toiletgo.activities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import toiletgo.activities.dto.MissionDto;
import toiletgo.activities.dto.MissionListDto;
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

    public MissionListDto toDto() {
        return MissionListDto.builder()
                .missionId(this.missionList.getMissionId())           // 미션 ID
                .missionName(this.missionList.getMission_name())      // 미션 이름
                .description(this.missionList.getDescription())       // 설명
                .missionType(this.missionList.getMissionType())       // 타입
                .point(this.missionList.getPoint())                   // 포인트 여부
                .progress(this.progress)                              // 사용자 진행도
                .isCompleted(this.isCompleted)                       // 완료 여부
                .completedAt(this.completedAt)                        // 완료 시간
                .build();
    }
}