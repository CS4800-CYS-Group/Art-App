package edu.cpp.CYS.controller;
import java.io.FileReader;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import edu.cpp.CYS.model.User;
import edu.cpp.CYS.service.UserService;
import util.CachedTweets;
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
		CachedTweets cache = gson.fromJson(new FileReader(ResourceUtils.getFile("classpath:static/assets/demofiles/Sam.json")), CachedTweets.class);
		CachedTweets list = new CachedTweets();
		list.tweets = new ArrayList<Tweets>();
		String html = "";
		if(UserService.getInstance().hasUser(id))
		{
			User user = UserService.getInstance().getByName(id);
			model.addAttribute("userName", user.getUsername());
			model.addAttribute("landing", "Welcome to " + user.getUsername() + "'s page!");
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
			model.addAttribute("posts", list);
			//model.addAttribute("post", cache.tweets.get(0).full_text);
		}
		else
		{
			model.addAttribute("userName", "User Not Found.");
			model.addAttribute("landing", "This user does not exist.");
		}
		return "user";
	}
}
