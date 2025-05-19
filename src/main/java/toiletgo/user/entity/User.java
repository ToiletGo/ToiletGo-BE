package toiletgo.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import toiletgo.activities.entity.*;

import java.util.*;


@Data
@Entity
public class User {
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "user_point")
    private Integer userPoint;

    @Column(name = "user_trust")
    private Integer userTrust;

    @Column(name = "user_profile_img")
    private String userProfileImg;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Mission> missions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Report> reports;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<GiftList> giftLists;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Gift> gifts;

}

