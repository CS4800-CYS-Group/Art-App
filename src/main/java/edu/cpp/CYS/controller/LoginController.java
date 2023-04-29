package edu.cpp.CYS.controller;

import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.cpp.CYS.PhotoRepository;
import edu.cpp.CYS.UserDto;
import edu.cpp.CYS.model.Photo;
import edu.cpp.CYS.model.U;
import edu.cpp.CYS.service.UserS;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {

    private UserS userService;
    private PhotoRepository photoRepository;

   public LoginController(UserS userService, PhotoRepository photoRepository){
    this.userService = userService;
    this.photoRepository = photoRepository;
   }
    // handler method to handle home page request
    @GetMapping("/")
    public String home(Model model, HttpSession session){
    	@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
    	if(messages == null)
    	{
    		messages = new ArrayList<>();
    	}
    	model.addAttribute("sessionMessages", messages);
        return "index";
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam String query, Model model) {
        List<U> users = userService.searchUsers(query);
        model.addAttribute("users", users);
        return "search";
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
  public String users(@PathVariable String username, Model model, HttpServletRequest request) {
	  boolean isUser = false;
	  
	  String users = (String) request.getSession().getAttribute("username");
	  
	  if(users == null)
	  {
	   		request.getSession().setAttribute("username", "Guest");
	  }
	  
      U user = userService.findUserByUsername(username);
      if(users.equals(user.getUsername()))
      { 
    	  isUser = true;
      } 
      if(user != null) {
          model.addAttribute("username", username);
          model.addAttribute("user", user);
          List<Photo> photos = photoRepository.findByUser(user);
          model.addAttribute("profilePicture", user.getProfilePicture());
          model.addAttribute("photos", photos);
          model.addAttribute("photo", new Photo());
          model.addAttribute("welcomeMessage", "Welcome, " + user.getUsername() + "!");
          model.addAttribute("isUser", isUser);
          List<U> following = user.getFollowing();
          model.addAttribute("following", following);
          
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

    @PostMapping("/us/{username}/saveProfilePicture")
public String saveProfilePicture(@RequestParam("profilePicture") MultipartFile file,
                                 @PathVariable("username") String username)  throws IOException {
    U currentUser = userService.findUserByUsername(username);
    if(currentUser == null) {
        throw new RuntimeException("User not found");
    }

    if(!file.isEmpty()) {
        byte[] bytes = file.getBytes();
        currentUser.setProfilePicture(bytes);
        userService.saveUser(currentUser);
    }

    return "redirect:/us/" + username;
}

@GetMapping("/us/{username}/profile-picture")
public ResponseEntity<byte[]> getProfilePicture(@PathVariable("username") String username) {
    U currentUser = userService.findUserByUsername(username);
    byte[] profilePicture = currentUser.getProfilePicture();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_JPEG); // change to the correct MIME type for your image
    headers.setContentLength(profilePicture.length);
    return new ResponseEntity<>(profilePicture, headers, HttpStatus.OK);
}


    @PostMapping("/us/{username}/follow")
    public String followUser(@PathVariable String username, HttpServletRequest request) {
        U follower = userService.findUserByUsername((String) request.getSession().getAttribute("username"));
        U following = userService.findUserByUsername(username);
        userService.follow(follower, following);
        return "redirect:/us/" + username;
    }

    @PostMapping("/us/{username}/unfollow")
    public String unfollowUser(@PathVariable String username, HttpServletRequest request) {
        U follower = userService.findUserByUsername((String) request.getSession().getAttribute("username"));
        U following = userService.findUserByUsername(username);
        userService.unfollow(follower, following);
        return "redirect:/us/" + username;
    }
    

     // handler method to handle login request
     @RequestMapping(value = "/login", produces = "application/json", 
    		 						   method = {RequestMethod.GET, RequestMethod.POST})
     public String login(@ModelAttribute("user") U user, HttpServletRequest request){
    	
    	String users = (String) request.getSession().getAttribute("username");
     	if(users == null)
     	{
     		request.getSession().setAttribute("username", "Guest");
     	}
    	U usher = userService.findUserByUsername(user.getUsername());
    	if(usher == null)
    	{
    		return "login";
    	}
        if( usher.getPassword() == user.getPassword())
        { 
        	request.getSession().setAttribute("username", usher.getUsername());
        	return "login";
        }
        return "login";
     }

     @GetMapping("/logout")
     public String logout(HttpServletRequest request) throws ServletException {
         request.logout();
         return "redirect:/login?logout";
     }
}