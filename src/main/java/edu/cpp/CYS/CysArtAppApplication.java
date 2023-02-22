package edu.cpp.CYS;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CysArtAppApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(CysArtAppApplication.class, args);
	}
	
	@GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) 
	{
      return String.format("Hello %s!", name);
    }
	
	@GetMapping("/roll")
	public String roll(@RequestParam(value = "roll", defaultValue = "1d6") String roll)
	{
		String [] rolls = new String[2];
		long sum = 0;
		try {
			rolls = roll.toLowerCase().split("d");
			
			if (Integer.parseInt(rolls[0])> 100 )
			{
				rolls[0] = "100";
			}
			if (Integer.parseInt(rolls[1])> 100 )
			{
				rolls[1] = "100";
			}
			
			Random random = new Random();
			for(int i = 0; i < Integer.parseInt(rolls[0]); i++)
			{
				sum += random.nextInt(1, Integer.parseInt(rolls[1]) + 1);
			}
			return String.format("The sum of your dice rolls is %d", sum);
		}
		catch(Exception e){
			return String.format("%s Not a valid roll. Should be in 'ndn' format. Example 1d6 ", roll);
			
		}
	}

	@GetMapping("/subscribe")
	public String sayHello(@RequestParam(value = "email", defaultValue = "n/a") String email) {
		return String.format("Thank you for joining, %s.", email);
	}

}
