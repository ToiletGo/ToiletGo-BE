package toiletgo.activities.entity;

import jakarta.persistence.*;
import lombok.*;
import toiletgo.user.entity.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(name = "gift_id", nullable = false)
    private Long giftId;
}
