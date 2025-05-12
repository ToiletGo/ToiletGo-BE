package toiletgo.dto;

import toiletgo.entity.Toilet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class ToiletDto {
    private Long toiletId;
    private String roadAddress;
    private String lotAddress;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String buildingName;
    private String guName;
    private String telNo;
    private String toiletType;
    private String openTime;
    private String toiletUsage;
    private String toiletStatus;
    private String facilities;
    private String signInfo;
    private String note;
    private BigDecimal rating;
    private Boolean hasDiaperTable;
    private Boolean hasHandicapAccess;
    private Boolean hasBidet;
    private Boolean hasTissue;

    public Toilet toEntity() {
        return Toilet.builder()
                .roadAddress(roadAddress)
                .lotAddress(lotAddress)
                .latitude(latitude)
                .longitude(longitude)
                .buildingName(buildingName)
                .guName(guName)
                .telNo(telNo)
                .toiletType(toiletType)
                .openTime(openTime)
                .toiletUsage(toiletUsage)
                .toiletStatus(toiletStatus)
                .facilities(facilities)
                .signInfo(signInfo)
                .note(note)
                .rating(rating)
                .hasDiaperTable(hasDiaperTable)
                .hasHandicapAccess(hasHandicapAccess)
                .hasBidet(hasBidet)
                .hasTissue(hasTissue)
                .build();
    }
    public static ToiletDto toDto(Toilet toilet) {
       return ToiletDto.builder()
                .toiletId(toilet.getToiletId())
                .roadAddress(toilet.getRoadAddress())
                .lotAddress(toilet.getLotAddress())
                .latitude(toilet.getLatitude())
                .longitude(toilet.getLongitude())
                .buildingName(toilet.getBuildingName())
                .telNo(toilet.getTelNo())
                .toiletType(toilet.getToiletType())
                .openTime(toilet.getOpenTime())
                .toiletUsage(toilet.getToiletUsage())
                .toiletStatus(toilet.getToiletStatus())
                .facilities(toilet.getFacilities())
                .signInfo(toilet.getSignInfo())
                .note(toilet.getNote())
                .rating(toilet.getRating())
                .hasDiaperTable(toilet.getHasDiaperTable())
                .hasHandicapAccess(toilet.getHasHandicapAccess())
                .hasBidet(toilet.getHasBidet())
                .hasTissue(toilet.getHasTissue())
                .build();
    }
}


