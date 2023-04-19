package edu.cpp.CYS.model;

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

	public U() {
    }

	public U(Long id, String email, String username, String password, String firstName, String lastName, List<Photo> photos) {
        this.id = id;
		this.email = email;
        this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
        this.photos = photos;
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
