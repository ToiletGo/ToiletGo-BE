package toiletgo.activities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long missionId;

    @OneToOne(mappedBy = "missionList")
    @JsonIgnore
    private Mission mission;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name= "mission_name")
    private String missionName;


    @Column(name = "point", nullable = false)
    private Integer point;

    public MissionList(String description, String missionName, Integer point){
        this.description = description;
        this.missionName = missionName;
        this.point = point;
    }

}


// @MapsId  // 이 필드가 missionId를 매핑하고 PK로 사용함