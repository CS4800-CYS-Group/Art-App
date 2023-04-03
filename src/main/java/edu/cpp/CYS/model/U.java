package edu.cpp.CYS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class U
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public String getEmail()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassword()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Role> getRoles()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String string)
	{
		// TODO Auto-generated method stub
		
	}

	public void setEmail(String email2)
	{
		// TODO Auto-generated method stub
		
	}

	public void setRoles(List<Role> asList)
	{
		// TODO Auto-generated method stub
		
	}

	public void setPassword(String encode)
	{
		// TODO Auto-generated method stub
		
	}

	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
