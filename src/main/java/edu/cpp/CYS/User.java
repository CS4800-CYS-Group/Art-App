package edu.cpp.CYS;

public class User
{
	private long id;
	private String username = "";
	
	User(long id, String username)
	{
		this.id = id;
		this.username = username;
	}
	
	public long getID()
	{
		return this.id;
	}
	
	public String getUsername()
	{
		return this.username;
	}

}
