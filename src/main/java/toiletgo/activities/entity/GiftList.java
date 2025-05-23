package toiletgo.activities.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class GiftList {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gift_id")
    private Long giftId;

    //@OneToOne(mappedBy = "giftList")
    //@MapsId

    @OneToOne(mappedBy = "giftList")
    private Gift gift;

    @Column(name = "gift_type")
    private String giftType;

    @Column(name = "gift_url", length = 2000)
    private String url;

    @Column(name = "expiration")
    private LocalDate expiration;

    @Column(name = "is_assigned")
    private Boolean isAssigned;



    public GiftList(String giftType, String url, LocalDate expiration, Boolean isAssigned) {
        this.giftType = giftType;
        this.url = url;
        this.expiration = expiration;
        this.isAssigned = isAssigned;
    }
}