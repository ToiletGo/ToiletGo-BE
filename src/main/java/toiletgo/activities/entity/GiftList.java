package toiletgo.activities.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toiletgo.activities.dto.GiftListDto;
import toiletgo.activities.dto.MissionListDto;

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

    @Column(name = "point")
    private Integer point;

    @Column(name = "gift_url", length = 2000)
    private String url;

    @Column(name = "expiration")
    private LocalDate expiration;

    @Column(name = "is_assigned")
    private Boolean isAssigned;


    public GiftList(String giftType, String url, Integer point,  LocalDate expiration, Boolean isAssigned) {
        this.giftType = giftType;
        this.url = url;
        this.point = point;
        this.expiration = expiration;
        this.isAssigned = isAssigned;
    }

    public GiftListDto toDto(){
        return GiftListDto.builder()
                .giftId(this.giftId)           // 미션 ID
                .giftType(this.giftType)      // 미션 이름
                .point(this.point)       // 설명// 타입
                .url(this.url)                   // 포인트 여부
                .isAssigned(this.isAssigned)
                .build();// 사용자 진행도
    }


}