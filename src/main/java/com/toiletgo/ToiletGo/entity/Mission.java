package com.toiletgo.ToiletGo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(name = "reward_point", nullable = false)
    private Integer rewardPoint;

    @Column(name = "mission_type", nullable = false)
    private String missionType;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
