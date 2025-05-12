package toiletgo.activities.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GiftDTO {
    private Long id;
    private String userId;
    private Long giftId;

    private String giftName;
    private String giftImageUrl;
}
