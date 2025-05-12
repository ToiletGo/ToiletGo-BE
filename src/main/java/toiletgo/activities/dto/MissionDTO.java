package toiletgo.activities.dto;


import toiletgo.activities.entity.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MissionDTO {
    private Long missionId;
    private String title;
    private String description;
    private Integer rewardPoint;
    private String missionType;
    private Boolean isActive;

    public MissionDTO toDto(Mission mission) {
        return MissionDTO.builder()
                .missionId(mission.getMissionId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .rewardPoint(mission.getRewardPoint())
                .missionType(mission.getMissionType())
                .isActive(mission.getIsActive())
                .build();
    }
}