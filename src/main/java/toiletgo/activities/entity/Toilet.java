package toiletgo.activities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toiletgo.activities.dto.ToiletDto;
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
    @Column(name="toilet_id", nullable = false)
    private Long toiletId;

    @Column(name="road_address", nullable = true)
    private String roadAddress;

    @Column(name="lot_address", nullable = true)
    private String lotAddress;

    @Column(nullable = false, precision = 11, scale = 8, name = "latitude")
    private BigDecimal latitude;

    @Column(nullable = false, precision = 11, scale = 8, name = "longitude")
    private BigDecimal longitude;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "toilet")
    @JsonIgnore
    private Set<Report> report;

    @Column(name="building_name", nullable = true)
    private String buildingName;

    @Column(name="gu_name", nullable = true)
    private String guName;

    @Column(name="tel_no", nullable = true)
    private String telNo;

    @Column(name="toilet_type", nullable = true)
    private String toiletType;

    @Column(name="open_time", nullable = true)
    private String openTime;

    @Column(name="toilet_usage", nullable = true)
    private String toiletUsage;

    @Column(name="toilet_status", nullable = true)
    private String toiletStatus;

    @Column(name="location_detail", nullable = true)
    private String locationDetail;


    @Lob
    @Column(name="facilities", nullable = true)
    private String facilities;

    @Column(name="sign_info", nullable = true)
    private String signInfo;

    @Lob
    @Column(name="note", nullable = true)
    private String note;

    @Column(precision = 3, scale = 2, name = "rating", nullable = true)
    private BigDecimal rating;

    @Column
    private Integer reviewCount;

    @Column(name="has_diaper_table", nullable = true)
    private Boolean hasDiaperTable;

    @Column(name="has_handicap_access", nullable = true)
    private Boolean hasHandicapAccess;

    @Column(name="has_bidet", nullable = true)
    private Boolean hasBidet;

    @Column(name="has_tissue", nullable = true)
    private Boolean hasTissue;

    // Test constructor
    public Toilet(String roadAddress, BigDecimal latitude, BigDecimal longitude, BigDecimal rating){
        this.roadAddress = roadAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
    }


    public ToiletDto toDto() {
        return ToiletDto.builder()
                .toiletId(this.toiletId)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .toiletStatus(this.toiletStatus)
                .note(this.note)
                .rating(this.rating)
                .reviewCount(this.reviewCount)
                .hasDiaperTable(this.hasDiaperTable)
                .hasHandicapAccess(this.hasHandicapAccess)
                .hasBidet(this.hasBidet)
                .hasTissue(this.hasTissue)
                .buildingName(this.buildingName)
                .build();
    }

}

