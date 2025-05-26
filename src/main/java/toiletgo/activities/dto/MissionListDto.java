package toiletgo.activities.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import toiletgo.activities.entity.MissionList;
import lombok.Builder;
import lombok.Data;
import toiletgo.activities.entity.MissionList;

import java.time.LocalDateTime;
import toiletgo.activities.entity.*;
import toiletgo.user.entity.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MissionListDto {
    private Long missionId;           // 미션 식별자
    private String missionName;       // 미션 이름
    private String description;       // 미션 설명
    private Boolean missionType;      // 미션 종류 (ex. 포인트형 true/false)
    private Integer point;            // 포인트 지급 여부
    private Integer progress;         // 사용자 진행도
    private Boolean isCompleted;      // 완료 여부
    private LocalDateTime completedAt; // 완료 시간
}
