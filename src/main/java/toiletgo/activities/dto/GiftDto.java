package toiletgo.activities.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import toiletgo.activities.entity.*;
import toiletgo.user.entity.*;

@Data
@Builder
public class GiftDto {
    private Long giftNo;
    private String userId;
    private Long giftId;

    private String giftType;
    private String giftSerial;
    private LocalDate expiration;
    private Boolean isAssigned;

    private Boolean isUsed;
    private Boolean isExpired;
}
