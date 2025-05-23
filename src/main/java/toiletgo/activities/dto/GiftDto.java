package toiletgo.activities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GiftDto {
    private Long id;
    private String userId;
    private Long giftId;

    private String giftName;
    private String giftImageUrl;

}
