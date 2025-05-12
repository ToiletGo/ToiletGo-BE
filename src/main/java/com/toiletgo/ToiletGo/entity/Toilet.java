package com.toiletgo.ToiletGo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    private String buildingName;
    private String telNo;
    private String toiletType;
    private String openTime;
    private String usage;
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

    public Long getToiletId() {
        return toiletId;
    }

    public String getRoadAddress() {
        return roadAddress;
    }

    public String getLotAddress() {
        return lotAddress;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getTelNo() {
        return telNo;
    }

    public String getToiletType() {
        return toiletType;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getUsage() {
        return usage;
    }

    public String getToiletStatus() {
        return toiletStatus;
    }

    public String getFacilities() {
        return facilities;
    }

    public String getSignInfo() {
        return signInfo;
    }

    public String getNote() {
        return note;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public Boolean getHasDiaperTable() {
        return hasDiaperTable;
    }

    public Boolean getHasHandicapAccess() {
        return hasHandicapAccess;
    }

    public Boolean getHasBidet() {
        return hasBidet;
    }

    public Boolean getHasTissue() {
        return hasTissue;
    }
}
