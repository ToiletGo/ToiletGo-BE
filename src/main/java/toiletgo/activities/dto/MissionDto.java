package toiletgo.activities.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import toiletgo.activities.entity.*;
import toiletgo.user.entity.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MissionDto {
    private Long missionNo;
    private String userId;

    private Long missionId;
    private String missionName;
    private String description;
    private Boolean missionType; // 미션 완료 여부가 아님! 종류 (ex. 방문형, 리뷰형 등)

    private Integer progress;
    private Boolean isCompleted;
    private LocalDateTime completedAt;
}
