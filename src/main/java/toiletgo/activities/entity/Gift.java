package toiletgo.activities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import toiletgo.user.entity.*;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Gift {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gift_no")
    private Long giftNo;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "gift_id")
    private GiftList giftList;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_used")
    private Boolean isUsed;

    @Column(name = "is_expired")
    private Boolean isExpired;
}

