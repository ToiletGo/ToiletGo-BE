package toiletgo.user.entity;

import jakarta.persistence.*;
import lombok.Data;
// import toiletgo.activities.entity.Review;

import java.math.BigDecimal;
import java.util.*;


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


    @Column(name="building_name")
    private String buildingName;

    @Column(name="gu_name")
    private String guName;

    @Column(name="tel_no")
    private String telNo;

    @Column(name="toilet_type")
    private String toiletType;

    @Column(name="opne_time")
    private String openTime;

    @Column(name="toilet_usage")
    private String toiletUsage;

    @Column(name="toilet_status")
    private String toiletStatus;


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


}

