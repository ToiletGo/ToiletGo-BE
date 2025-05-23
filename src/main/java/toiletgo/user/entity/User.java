package toiletgo.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import toiletgo.activities.entity.Gift;
import toiletgo.activities.entity.Mission;
import toiletgo.activities.entity.Report;
import toiletgo.activities.entity.Review;
// import toiletgo.activities.entity.*;

import java.util.*;


@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // @Column(name="user_role", nullable = false)
    // private String userRole;

    @Column(name = "user_point")
    private Integer userPoint;

    @Column(name = "user_trust")
    private Integer userTrust;

    @Column(name = "user_profile_img")
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

}

