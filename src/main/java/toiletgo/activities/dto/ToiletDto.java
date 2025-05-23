package toiletgo.activities.dto;

import lombok.Builder;
import lombok.Data;
import toiletgo.activities.entity.Toilet;

import java.math.BigDecimal;

@Data
@Builder
public class ToiletDto {

    private Long toiletId;

    private BigDecimal latitude;
    private BigDecimal longitude;

    //화장실현황 여 1, 남1
    private String toiletStatus;
    private String note;
    private BigDecimal rating;
    private Boolean hasDiaperTable;
    private Boolean hasHandicapAccess;
    private Boolean hasBidet;
    private Boolean hasTissue;

    public static ToiletDto toDto(Toilet toilet) {
        return ToiletDto.builder()
                .toiletId(toilet.getToiletId())
                .latitude(toilet.getLatitude())
                .longitude(toilet.getLongitude())
                .toiletStatus(toilet.getToiletStatus())
                .note(toilet.getNote())
                .rating(toilet.getRating())
                .hasDiaperTable(toilet.getHasDiaperTable())
                .hasHandicapAccess(toilet.getHasHandicapAccess())
                .hasBidet(toilet.getHasBidet())
                .hasTissue(toilet.getHasTissue())
                .build();
    }

    public Toilet toEntity() {
        return Toilet.builder()
                .toiletId(this.toiletId) // 생성 시 생략 가능 (JPA가 자동 처리)
                .roadAddress(null)
                .lotAddress(null)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .buildingName(null)
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
