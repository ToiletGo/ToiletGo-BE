package toiletgo.activities.dto;

import toiletgo.activities.entity.MissionList;
import lombok.Builder;
import lombok.Data;
import toiletgo.activities.entity.MissionList;

import java.time.LocalDateTime;
import toiletgo.activities.entity.*;
import toiletgo.user.entity.*;

@Data
@Builder
public class MissionListDto {
    private Long missionId;           // 미션 식별자
    private String missionName;       // 미션 이름
    private String description;       // 미션 설명
    private Integer point;            // 포인트
    private Integer progress;         // 사용자 진행도
    private Boolean isCompleted;      // 완료 여부
    private LocalDateTime completedAt; // 완료 시간
}
