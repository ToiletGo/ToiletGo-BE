package toiletgo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long giftId;

    @Column(nullable = false)
    private Long userId;

    @Column
    private boolean is_used = true;

    @Column
    private boolean is_expired;
}
