package toiletgo.activities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftPurchaseDto {
    private String userId;
    private Long giftId;
}
