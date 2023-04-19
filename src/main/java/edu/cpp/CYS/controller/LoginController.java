package edu.cpp.CYS.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.cpp.CYS.PhotoRepository;
import edu.cpp.CYS.UserDto;
import edu.cpp.CYS.model.Photo;
import edu.cpp.CYS.model.U;
import edu.cpp.CYS.service.UserS;

@Controller
public class LoginController {
    private UserS userService;
    private PhotoRepository photoRepository;

    public LoginController(UserS userService, PhotoRepository photoRepository) {
        this.userService = userService;
        this.photoRepository = photoRepository;
    }
    // handler method to handle home page request
    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        U existingUser = userService.findUserByUsername(userDto.getUsername());

        if(existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
  // handler method to handle list of users
  @GetMapping("/us/{username}")
  public String users(@PathVariable String username, Model model) {
      U user = userService.findUserByUsername(username);
      if(user != null) {
          model.addAttribute("username", username);
          model.addAttribute("user", user);
          List<Photo> photos = photoRepository.findByUser(user);
          model.addAttribute("photos", photos);
          model.addAttribute("photo", new Photo());
          model.addAttribute("welcomeMessage", "Welcome, " + user.getUsername() + "!");
          return "us";
      }
      return "redirect:/login";
  }

  @PostMapping("/us/{username}/photos/upload")
public String uploadPhoto(@RequestParam("file") MultipartFile file,
                          @RequestParam("title") String title,
                          @RequestParam("description") String description,
                          @PathVariable("username") String username) throws IOException {

    // Get the current user from the session
    U currentUser = userService.findUserByUsername(username);

    // Create a new Photo object and set its properties
    Photo photo = new Photo();
    photo.setTitle(title);
    photo.setDescription(description);
    photo.setUser(currentUser);

    // Set the photo data from the uploaded file
    photo.setImage(file.getBytes());

    // Save the photo to the database
    photoRepository.save(photo);

    // Redirect to the user page
    return "redirect:/us/" + username;
}

     // handler method to handle login request
     @GetMapping("/login")
     public String login(){
         return "login";
     }

     @GetMapping("/logout")
     public String logout(HttpServletRequest request) throws ServletException {
         request.logout();
         return "redirect:/login?logout";
     }
}