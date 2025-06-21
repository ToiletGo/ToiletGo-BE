package toiletgo.activities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toiletgo.activities.entity.Toilet;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToiletDto {

    private Long toiletId; // nullable false

    private BigDecimal latitude; // nullable false
    private BigDecimal longitude; // nullable false

    //화장실현황 여 1, 남1
    private String buildingName;
    private String toiletStatus;
    private String note;
    private BigDecimal rating;
    private Integer reviewCount;
    private Boolean hasDiaperTable;
    private Boolean hasHandicapAccess;
    private Boolean hasBidet;
    private Boolean hasTissue;

    private String userId;

    public static ToiletDto toDto(Toilet toilet) {
        return ToiletDto.builder()
                .toiletId(toilet.getToiletId())
                .latitude(toilet.getLatitude())
                .longitude(toilet.getLongitude())
                .buildingName(toilet.getBuildingName())
                .toiletStatus(toilet.getToiletStatus())
                .note(toilet.getNote())
                .rating(toilet.getRating())
                .hasDiaperTable(toilet.getHasDiaperTable())
                .hasHandicapAccess(toilet.getHasHandicapAccess())
                .hasBidet(toilet.getHasBidet())
                .hasTissue(toilet.getHasTissue())
                .userId(null)
                .build();
    }

    public Toilet toEntity() {
        return Toilet.builder()
                .toiletId(this.toiletId) // 생성 시 생략 가능 (JPA가 자동 처리)
                .roadAddress(null)
                .lotAddress(null)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .buildingName(this.buildingName)
                .guName(null)
                .telNo(null)
                .toiletType(null)
                .openTime(null)
                .toiletUsage(null)
                .toiletStatus(this.toiletStatus)
                .locationDetail(null)
                .facilities(null)
                .signInfo(null)
                .note(this.note)
                .rating(this.rating)
                .hasDiaperTable(this.hasDiaperTable)
                .hasHandicapAccess(this.hasHandicapAccess)
                .hasBidet(this.hasBidet)
                .hasTissue(this.hasTissue)
                .build();
    }
}
