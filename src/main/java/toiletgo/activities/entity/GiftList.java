package toiletgo.activities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class GiftList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @MapsId
    @Column(name = "gift_id")
    private Integer giftId;

    @Column(name = "gift_type")
    private String giftType;

    @Column(name = "serial_no")
    private String giftNo;

    @Column(name = "expiration")
    private LocalDate expiration;

    @Column(name = "is_assigned")
    private Boolean isAssigned;

    @JsonIgnore
    @OneToOne(mappedBy = "gift")
    private Gift gift;

}
