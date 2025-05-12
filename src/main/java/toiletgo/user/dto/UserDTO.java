package toiletgo.user.dto;

/**
 * DTO Class
 **/
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String userId;
    private String username;
    private Integer userPoint;
    private Integer userTrust;
    private String userProfileImg;

    public UserDTO toDto(UserDTO user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .userPoint(user.getUserPoint())
                .userTrust(user.getUserTrust())
                .userProfileImg(user.getUserProfileImg())
                .build();
    }
}
