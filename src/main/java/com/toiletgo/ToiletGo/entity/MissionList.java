package com.toiletgo.ToiletGo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="mission_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;

    @Column(name = "completed_at")
    private String completedAt;
}
