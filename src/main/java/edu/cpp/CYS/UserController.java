package edu.cpp.CYS;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;



@Controller

public class UserController
{
	@GetMapping("/user/{id}")
	public String getById(@PathVariable String id, Model model)
	{
		
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
