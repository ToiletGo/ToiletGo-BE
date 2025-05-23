package toiletgo.activities.dto;

import toiletgo.entity.Mission;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MissionDto {
    private Long missionId;
    private String title;
    private String description;
    private Integer rewardPoint;
    private String missionType;
    private Boolean isActive;

    public MissionDto toDto(Mission mission) {
        return MissionDto.builder()
                .missionId(mission.getMissionId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .rewardPoint(mission.getRewardPoint())
                .missionType(mission.getMissionType())
                .isActive(mission.getIsActive())
                .build();
    }
}
