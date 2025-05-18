package toiletgo.activities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import toiletgo.user.entity.*;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gift_id")
    private Long giftId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "giftList")
    private GiftList giftList;

    @Column(name = "is_used")
    private Boolean isUsed;

    @Column(name = "is_expired")
    private Boolean isExpired;

}
