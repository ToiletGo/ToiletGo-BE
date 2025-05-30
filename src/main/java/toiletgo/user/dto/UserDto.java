package toiletgo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toiletgo.user.entity.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String userName;
    // private String password;
    private Integer userPoint;
    private Integer userTrust;
    private String userProfileImg;

    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .username(this.userName)
                // .password(this.password)
                .userPoint(this.userPoint)
                .userTrust(this.userTrust)
                .userProfileImg(this.userProfileImg)
                .build();
    }
}
