package edu.cpp.CYS.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="users")

public class U
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
 
    @Column(nullable = false)
    private String lastName;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String username;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Photo> photos;

    private byte[] profilePicture;
    private String bio;
    @ManyToMany
    @JoinTable(name = "follow",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private List<U> followers = new ArrayList<>();
    @ManyToMany(mappedBy = "followers")
    private List<U> following = new ArrayList<>();

	public U() {
    }

	public U(Long id, String email, String username, String password, String firstName, String lastName, List<Photo> photos, byte[] profilePicture, String bio, List<U> followers, List<U> following) {
        this.id = id;
		this.email = email;
        this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
        this.photos = photos;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.followers = followers;
        this.following = following;
	}

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<U> getFollowers() {
        return followers;
    }

    public void setFollowers(List<U> followers) {
        this.followers = followers;
    }

    public List<U> getFollowing() {
        return following;
    }

    public void setFollowing(List<U> following) {
        this.following = following;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

}
