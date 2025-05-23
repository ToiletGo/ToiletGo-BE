package toiletgo.activities.dto;

import toiletgo.activities.entity.MissionList;
import lombok.Builder;
import lombok.Data;

import toiletgo.activities.entity.*;
import toiletgo.user.entity.*;

@Data
@Builder
public class MissionListDto {
    private Long id;
    private String userId;
    private Long missionId;
    private Boolean isCompleted;
    private String completedAt;

    public MissionListDto toDto(MissionList missionList) {
        return MissionListDto.builder()
                .id(missionList.getId())
                .userId(missionList.getUser().getUserId())
                .missionId(missionList.getMission().getMissionId())
                .isCompleted(missionList.getIsCompleted())
                .completedAt(missionList.getCompletedAt())
                .build();
    }
}
