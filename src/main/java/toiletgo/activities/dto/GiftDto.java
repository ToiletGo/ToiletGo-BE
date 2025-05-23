package toiletgo.activities.dto;

import lombok.Builder;
import lombok.Data;

import toiletgo.activities.entity.*;
import toiletgo.user.entity.*;

@Data
@Builder
public class GiftDto {
    private Long id;
    private String userId;
    private Long giftId;

    private String giftName;
    private String giftImageUrl;

}
