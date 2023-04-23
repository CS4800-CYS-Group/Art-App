package edu.cpp.CYS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import edu.cpp.CYS.model.User;


@Controller
public class BrowseController {
    @GetMapping("/browse")
    public String getLogin(){
        return "socialMediaView.html";

    }
    
    @GetMapping("/home")
    public String getHome(){
        return "index.html";

    }

    @GetMapping("/test")
    public String getTest(){
        return "userHomePage.html";

    }
}
