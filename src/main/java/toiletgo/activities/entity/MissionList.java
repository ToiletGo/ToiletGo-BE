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

    @OneToOne(mappedBy = "missionList")
    private Mission mission;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name= "mission_name")
    private String mission_name;

    @Column(name = "mission_type", nullable = false)
    private Boolean missionType;

    @Column(name = "point", nullable = false)
    private Boolean point;

}


// @MapsId  // 이 필드가 missionId를 매핑하고 PK로 사용함