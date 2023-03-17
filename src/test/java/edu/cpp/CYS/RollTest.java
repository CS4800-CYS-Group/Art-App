package edu.cpp.CYS;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class RollTest
{
	@Test
	public void rollCausesException_ThenAssertsSucceeds()
	{
		CysArtAppApplication cys = new CysArtAppApplication();
		String actualMessage = cys.roll("notanumber");
		String expectedMessage = "notanumber Not a valid roll. Should be in 'ndn' format. Example 1d6";
        assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void rollSucceeds_ThenAssertsSucceeds()
	{
		CysArtAppApplication cys = new CysArtAppApplication();
		String actualMessage = cys.roll("1d6");
		String expectedMessage = "sum";
        assertTrue(actualMessage.contains(expectedMessage));
	}
}
