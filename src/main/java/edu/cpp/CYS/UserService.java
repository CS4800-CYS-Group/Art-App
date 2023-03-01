package edu.cpp.CYS;

import java.util.ArrayList;
import java.util.List;

public class UserService
{
	private static UserService INSTANCE;
	//Wil be replaced by sql statement when login works
	private static List<User> userList = new ArrayList<User> (); 
	
	private UserService()
	{
		userList.add(new User(1, "selly"));
	}
	
	public static UserService getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new UserService();
		}
		
		return INSTANCE;
		
	}
	
	public static User getByName(String u)
	{
		for(User us: userList)
		{
			if(us.getUsername().equals(u))
			{
				return us;
			}
		}
		return null;
	}
	
	public static boolean hasUser(String u)
	{
		if(getByName(u) == null)
		{
			return false;
		}
		return true;
	}
	
	public static void getPosts(String u)
	{
		//this will get all the post from a user
	}
}
