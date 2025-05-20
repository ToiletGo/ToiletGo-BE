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
    private Integer userPoint;
    private Integer userTrust;
    private String userProfileImg;

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .userPoint(user.getUserPoint())
                .userTrust(user.getUserTrust())
                .userProfileImg(user.getUserProfileImg())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .username(this.username)
                .password(null) // 비밀번호는 보통 별도로 처리함
                .userPoint(this.userPoint)
                .userTrust(this.userTrust)
                .userProfileImg(this.userProfileImg)
                .build();
    }
}
