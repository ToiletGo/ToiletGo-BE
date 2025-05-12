package toiletgo.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

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
}

