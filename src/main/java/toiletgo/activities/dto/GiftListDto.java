package toiletgo.activities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GiftListDto {
    private Long giftId;
    private String giftType;
    private Integer point;
    private String url;
    private Boolean isAssigned;
}
