
package edu.cpp.CYS.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Optional;

import edu.cpp.CYS.PhotoRepository;
import edu.cpp.CYS.UserRepository;
import edu.cpp.CYS.model.Photo;
import edu.cpp.CYS.model.U;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ImageController {
    private PhotoRepository photoRepository;

    public ImageController(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("id") Long id) {
        java.util.Optional<Photo> photo = photoRepository.findById(id);
        if (photo.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<byte[]>(photo.get().getImage(), headers, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}
    /*@Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/photos/upload")
public String uploadPhoto(@ModelAttribute("photo") @Valid Photo photo,
                          BindingResult result,
                          HttpSession session) {
    // Get the current user from the session
    U currentUser = (U) session.getAttribute("currentUser");

    if (result.hasErrors()) {
        return "redirect:/us/" + currentUser.getUsername();
    }

    // Set the user for the photo and save it to the database
    photo.setUser(currentUser);
    photoRepository.save(photo);

    // Redirect to the user page with the uploaded photo
    String username = currentUser.getUsername();
    return "redirect:/us/" + username;
}

@GetMapping("/us/{username}")
public String showUserPhotos(@PathVariable("username") String username, Model model) {
    // Find the user by username
    U user = userRepository.findByUsername(username);

    if (user == null) {
        return "redirect:/";
    }

    // Get the user's photos and add them to the model
    List<Photo> photos = photoRepository.findByUser(user);
    model.addAttribute("photos", photos);

    // Add the user to the model
    model.addAttribute("user", user);

    return "user_photos";
}
}*/


