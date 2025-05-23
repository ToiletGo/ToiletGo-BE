package toiletgo.user.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import toiletgo.activities.entity.Gift;
import toiletgo.activities.entity.Mission;
import toiletgo.activities.entity.Report;
import toiletgo.activities.entity.Review;
import toiletgo.user.entity.User;

import java.util.List;

@Data
@Builder
public class UserDto {

    private String userId;
    private String username;
    private String password;
    private Integer userPoint;
    private Integer userTrust;
    private String userProfileImg;

    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .username(this.username)
                .password(this.password)
                .userPoint(this.userPoint)
                .userTrust(this.userTrust)
                .userProfileImg(this.userProfileImg)
                .build();
    }
}
