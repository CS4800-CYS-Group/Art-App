/*package edu.cpp.CYS.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_follow")
public class UserFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private U follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false)
    private U following;

    public UserFollow() {}

    public UserFollow(U follower, U following) {
        this.follower = follower;
        this.following = following;
    }

   public U getFollower() {
      return follower;
   }

   public void setFollower(U follower) {
      this.follower = follower;
   }

   public U getFollowing() {
      return following;
   }

   public void setFollowing(U following) {
      this.following = following;
   }
} */
