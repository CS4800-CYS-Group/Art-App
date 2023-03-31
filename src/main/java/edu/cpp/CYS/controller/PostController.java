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
public class PostController
{
	//TODO: Get Post through SQL database, once user can make posts
}
