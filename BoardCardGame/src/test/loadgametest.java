package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Before;
import org.junit.Test;

public class loadgametest

{

	


	@Test
	public void test()
	{
		try {
		    BufferedReader br= new BufferedReader ( new FileReader ( "PlayerSave.txt" ));
		    
		    //System.out.println("File Found");
		    // other reading code here
		    }
		catch (FileNotFoundException e)
		{
		    System.out.println("File Not Found");
		}
	}
}
