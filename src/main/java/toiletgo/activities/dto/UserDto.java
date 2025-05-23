package toiletgo.activities.dto;

import toiletgo.activities.entity.*;
import toiletgo.user.entity.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String userId;
    private String username;
    private Integer userPoint;
    private Integer userTrust;
    private String userProfileImg;

    public UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .userPoint(user.getUserPoint())
                .userTrust(user.getUserTrust())
                .userProfileImg(user.getUserProfileImg())
                .build();
    }
}
