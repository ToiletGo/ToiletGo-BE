package toiletgo.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toiletgo.activities.entity.Gift;
import toiletgo.activities.entity.Mission;
import toiletgo.activities.entity.Report;
import toiletgo.activities.entity.Review;
import toiletgo.user.dto.UserDto;
// import toiletgo.activities.entity.*;

import java.util.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_point", nullable = true)
    private Integer userPoint;

    @Column(name = "user_trust", nullable = true)
    private Integer userTrust;

    @Column(name = "user_profile_img", nullable = true)
    private String userProfileImg;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Report> reports;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Mission> missions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Gift> gifts;

    public User(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public UserDto toDto() {
        return UserDto.builder()
                .userId(this.userId)
                .userName(this.username)
                .userPoint(this.userPoint)
                .userTrust(this.userTrust)
                .userProfileImg(this.userProfileImg)
                .build();
    }

}

// @Column(name="user_role", nullable = false)
// private String userRole;
