package edu.cpp.CYS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import edu.cpp.CYS.model.User;


@Controller
public class LoginSignController {
   @GetMapping("/login")
	public String getLogin(){
		return "login.html";
		
	}

   @GetMapping("/register")
   public String getSignin(){
      return "register.html";

   }
}
