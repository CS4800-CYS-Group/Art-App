package edu.cpp.CYS.controller;
import java.io.FileReader;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import edu.cpp.CYS.model.User;
import edu.cpp.CYS.service.UserService;
import util.CachedTweets;
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
		System.out.println(cache.tweets.get(0).full_text);
		if(UserService.getInstance().hasUser(id))
		{
			User user = UserService.getInstance().getByName(id);
			model.addAttribute("userName", user.getUsername());
			model.addAttribute("landing", "Welcome to " + user.getUsername() + "'s page!");
		}
		else
		{
			model.addAttribute("userName", "User Not Found.");
			model.addAttribute("landing", "This user does not exist.");
		}
		return "user";
	}


}
