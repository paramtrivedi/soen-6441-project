package test;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;

public class saveGameTest
{
	
	
		@Test
		public void save()
		{
		File f = new File("PlayerDetails.txt");
			if (f.exists()){
				System.out.println("File exists. Please give another name!!!");				
			}
			else
			{
				save();
			}
		}
	}


