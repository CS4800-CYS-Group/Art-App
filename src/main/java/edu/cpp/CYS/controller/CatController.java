package edu.cpp.CYS.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;



@Controller
public class CatController
{
	private final static String API_ROOT_CAT = "http://aws.random.cat/meow";
	
	@GetMapping("/cat")
	public String cat(Model model)
	{
		String link = "http://lightwidget.com/wp-content/uploads/local-file-not-found.png";
		Gson g = new Gson();
		URL u;
		try
		{
			u = new URL(API_ROOT_CAT);
			HttpURLConnection c = (HttpURLConnection) u.openConnection();
			InputStreamReader r = new InputStreamReader(c.getInputStream());
			catData cat = g.fromJson(r,  catData.class);
			r.close();
			link = cat.file;
		}
		catch(IOException | NullPointerException e)
		{
			e.printStackTrace();
			
		}
		model.addAttribute("cat", link);
		return "cat";
	}
	
	private static class catData
	{
		String file = "http://lightwidget.com/wp-content/uploads/local-file-not-found.png";
	}
}
