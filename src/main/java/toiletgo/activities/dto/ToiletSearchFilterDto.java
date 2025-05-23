package toiletgo.activities.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToiletSearchFilterDto {
    private Double minLatitude;
    private Double maxLatitude;
    private Double minLongitude;
    private Double maxLongitude;

    private Boolean hasDiaperTable;
    private Boolean hasHandicapAccess;
    private Boolean hasBidet;
    private Boolean hasTissue;
}
