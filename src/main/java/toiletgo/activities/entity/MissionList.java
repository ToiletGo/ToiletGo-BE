package toiletgo.activities.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * MissionList : table mission_list
 * 미션 목록
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MissionList {

    @Id
    @Column(name = "mission_id")
    private Long missionId;

    @OneToOne(mappedBy = "missionList")
    private Mission mission;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name= "mission_name")
    private String missionName;

    @Column(name = "mission_type", nullable = false)
    private Boolean missionType;

    @Column(name = "point", nullable = false)
    private Integer point;

    public MissionList(Mission mission, String description, String missionName, String missionType, Integer point){
        this.mission = mission;
        this.description = description;
        this.missionName = missionName;
        this.missionType = Boolean.parseBoolean(missionType);
        this.point = point;
    }

}


// @MapsId  // 이 필드가 missionId를 매핑하고 PK로 사용함