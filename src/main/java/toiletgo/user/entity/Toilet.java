package toiletgo.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toiletgo.activities.dto.ToiletDto;
import toiletgo.activities.entity.Report;
// import toiletgo.activities.entity.Review;

import java.math.BigDecimal;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Toilet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toiletId;

    @Column(name="road_address")
    private String roadAddress;

    @Column(name="lot_address")
    private String lotAddress;

    @Column(nullable = false, precision = 11, scale = 2, name = "latitude")
    private BigDecimal latitude;

    @Column(nullable = false, precision = 11, scale = 2, name = "longitude")
    private BigDecimal longitude;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "toilet")
    @JsonIgnore
    private Set<Report> report;

    @Column(name="building_name")
    private String buildingName;

    @Column(name="gu_name")
    private String guName;

    @Column(name="tel_no")
    private String telNo;

    @Column(name="toilet_type")
    private String toiletType;

    @Column(name="open_time")
    private String openTime;

    @Column(name="toilet_usage")
    private String toiletUsage;

    @Column(name="toilet_status")
    private String toiletStatus;

    @Column(name="location_detail")
    private String locationDetail;


    @Lob
    @Column(name="facilities")
    private String facilities;

    @Column(name="sign_info")
    private String signInfo;

    @Lob
    @Column(name="note")
    private String note;

    @Column(precision = 3, scale = 2, name = "rating")
    private BigDecimal rating;

    @Column(name="has_diaper_table")
    private Boolean hasDiaperTable;

    @Column(name="has_handicap_access")
    private Boolean hasHandicapAccess;

    @Column(name="has_bidet")
    private Boolean hasBidet;

    @Column(name="has_tissue")
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

