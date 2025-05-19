package toiletgo.activities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import toiletgo.user.entity.User;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "no")
    private Long no;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "missionList")
    @JoinColumn(nullable = false)
    private MissionList missionList;

    @Column(name = "mission_name")
    private String missionName;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "mission_type", nullable = false)
    private String missionType;

    @Column(name = "point", nullable = false)
    private Boolean point;


    @ManyToOne // optional = true
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}