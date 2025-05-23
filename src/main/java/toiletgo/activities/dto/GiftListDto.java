package toiletgo.activities.dto;
package toiletgo.activities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toiletgo.activities.entity.Gift;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class GiftList {

    @Id
    @Column(name = "gift_id")
    private Long giftId;

    //@OneToOne(mappedBy = "giftList")
    //@MapsId

    @OneToOne(mappedBy = "giftList")
    private Gift gift;

    @Column(name = "gift_type")
    private String giftType;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "expiration")
    private LocalDate expiration;

    @Column(name = "is_assigned")
    private Boolean isAssigned;
}
public class GiftListDto {
    private Long giftId;
    private String giftType;
}
