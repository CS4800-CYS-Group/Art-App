package edu.cpp.CYS.controller;
import java.io.FileReader;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import edu.cpp.CYS.model.User;
import edu.cpp.CYS.service.UserService;
import util.CachedTweets;
import util.Dogs;
import util.Tweets;

import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;

@Controller
public class UserController
{
	@GetMapping("/user/{id}")
	public String getById(@PathVariable String id, Model model)throws Exception
	{
		Gson gson = new Gson();
		int d = 0;
		CachedTweets cache = gson.fromJson(new FileReader(ResourceUtils.getFile("classpath:static/assets/demofiles/Sam.json")), CachedTweets.class);
		CachedTweets list = new CachedTweets();
		list.tweets = new ArrayList<Tweets>();
		Dogs dog = gson.fromJson(new FileReader(ResourceUtils.getFile("classpath:static/assets/demofiles/dog.json")), Dogs.class);
		if(UserService.getInstance().hasUser(id))
		{
			User user = UserService.getInstance().getByName(id); 
			model.addAttribute("userName", user.getUsername());
			model.addAttribute("landing", "Welcome to " + user.getUsername() + "'s page!");
			//Temp for presentation, we will only use one user.html in final vers.
			return "user2";
		}
		else if(cache.tweets.get(0).user.name.equals(id))
		{
			int j = 0; 
			for(int i = 0; i<cache.tweets.size(); i++)
			{
				if(cache.tweets.get(i).media.size() > 0 && !cache.tweets.get(i).full_text.contains("RT") && cache.tweets.get(i).user.name.equals("demonky"))
				{
					list.tweets.add(cache.tweets.get(i));
					j++;
				}
			}  
			for(int i = 0; i < list.tweets.size(); i++) 
			{
				
				list.tweets.get(i).full_text = list.tweets.get(i).full_text.replaceAll("http.*", "");
				for(int k = 0; k < list.tweets.get(i).media.size(); k++)
				{
					d += k;
					if(d >= dog.links.size())
					{
						d = 0;
					}
					list.tweets.get(i).media.get(k).media_url = dog.links.get(d);
				}
				d++;
			} 
			model.addAttribute("posts", list);
		}  
		else
		{
			model.addAttribute("userName", "User Not Found.");
			model.addAttribute("landing", "This user does not exist.");
		}
		return "user";
	}
}
