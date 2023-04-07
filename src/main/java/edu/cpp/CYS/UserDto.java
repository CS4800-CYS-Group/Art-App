package edu.cpp.CYS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/**
 * Data Transfer Object (DTO) that represents a User in a way that is optimized for transferring
 * the data between layers of the application or between different applications, without exposing
 * the underlying implementation details.
 */

public class UserDto
{
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
	 @NotEmpty
	 private String username;

	public UserDto(@NotEmpty String firstName, @NotEmpty String lastName,
			@NotEmpty(message = "Email should not be empty") @Email String email,
			@NotEmpty(message = "Password should not be empty") String password, @NotEmpty String username) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.username = username;
	}

	public UserDto() {
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
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	

}
