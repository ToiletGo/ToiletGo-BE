package toiletgo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import toiletgo.dto.ToiletDto;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Toilet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toiletId;

    @Column
    private String roadAddress;
    @Column
    private String lotAddress;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal latitude;
    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal longitude;

    private String guName;
    private String buildingName;
    private String telNo;
    private String toiletType;
    private String openTime;
    private String toiletUsage;
    private String toiletStatus;

    @Lob
    private String facilities;

    private String signInfo;

    @Lob
    private String note;

    @Column(precision = 3, scale = 2)
    private BigDecimal rating;

    private Boolean hasDiaperTable;
    private Boolean hasHandicapAccess;
    private Boolean hasBidet;
    private Boolean hasTissue;

    public ToiletDto toDto() {
        return ToiletDto.builder()
                .toiletId(this.getToiletId())
                .roadAddress(this.getRoadAddress())
                .lotAddress(this.getLotAddress())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .buildingName(this.getBuildingName())
                .telNo(this.getTelNo())
                .toiletType(this.getToiletType())
                .openTime(this.getOpenTime())
                .toiletUsage(this.getToiletUsage())
                .toiletStatus(this.getToiletStatus())
                .facilities(this.getFacilities())
                .signInfo(this.getSignInfo())
                .note(this.getNote())
                .rating(this.getRating())
                .hasDiaperTable(this.getHasDiaperTable())
                .hasHandicapAccess(this.getHasHandicapAccess())
                .hasBidet(this.getHasBidet())
                .hasTissue(this.getHasTissue())
                .build();
    }
}
