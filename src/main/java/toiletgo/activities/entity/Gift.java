package toiletgo.activities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import toiletgo.activities.dto.GiftDto;
import toiletgo.user.entity.*;

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

    @JsonIgnore
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "gift_id")
    private GiftList giftList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column(name = "is_used")
    private Boolean isUsed;

    @Column(name = "is_expired")
    private Boolean isExpired;

    public Gift(GiftList giftList, User user, Boolean isUsed, Boolean isExpired){
        this.giftList = giftList;
        this.user = user;
        this.isUsed = isUsed;
        this.isExpired = isExpired;
    }

    public GiftDto toDto() {
        return GiftDto.builder()
                .giftNo(this.giftNo)
                .userId(this.user != null ? this.user.getUserId() : null)
                .giftType(this.giftList != null ? this.giftList.getGiftType() : null)
                .giftSerial(this.giftList != null ? this.giftList.getUrl() : null)
                .expiration(this.giftList != null ? this.giftList.getExpiration() : null)
                .isAssigned(this.giftList != null ? this.giftList.getIsAssigned() : null)
                .isUsed(this.isUsed)
                .isExpired(this.isExpired)
                .build();
    }
}

