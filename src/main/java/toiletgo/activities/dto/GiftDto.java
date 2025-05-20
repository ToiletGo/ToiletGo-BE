package toiletgo.activities.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class GiftDto {
    private Long giftNo;
    private String userId;

    private String giftType;
    private String giftSerial;
    private LocalDate expiration;
    private Boolean isAssigned;

    private Boolean isUsed;
    private Boolean isExpired;
}