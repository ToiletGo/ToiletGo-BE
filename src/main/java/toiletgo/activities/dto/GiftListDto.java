package toiletgo.activities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiftListDto {
    private Long giftId;
    private String giftType;
    private Integer point;
    private String url;
    private Boolean isAssigned;
}
