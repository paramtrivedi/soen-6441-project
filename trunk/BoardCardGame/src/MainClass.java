import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class MainClass {
	
	String playerid= "11";
	String playerName = "JTanna";
	
	String randomcards = "3";
	
	String[] saveInformation = {playerid, playerName, randomcards};
	//String[] saveInformation1 ={playername, percard};
	
	int pidLoc= 0;
	int plNLoc= 1;
	int rdcarLoc= 2;
	
	
	public MainClass()
	{
		//readPlayer("PlayerSave.txt");
		updatePlayerStats();
		savePlayer("PlayerSave.txt");
	}
	
	private void updatePlayerStats()
	{
		
		playerid = saveInformation [pidLoc];
		playerName = saveInformation [plNLoc];
		randomcards = saveInformation [rdcarLoc];
		System.out.println(playerid);
		System.out.println(playerName);
		System.out.println(randomcards);
		
	}
	
		private void  readPlayer(String filePath)
		{
			File inputFile;
			BufferedReader inputReader;
			
			try
			{
				inputFile = new File(filePath);
				inputReader = new BufferedReader (new FileReader(filePath));
				
				for (int i = 0; i< saveInformation.length; i++)
				{
					saveInformation[i]=inputReader.readLine();
					
				}
				
				
				//String fileText = inputReader.readLine();
				//System.out.println(fileText);
				inputReader.close();
				}
			catch (Exception e)
			{
				e.printStackTrace();
				
			}
		}
		private void savePlayer(String filePath)
		{
			File outputFile;
			BufferedWriter outputWriter;
			
			try
			{
				outputFile = new File(filePath);
				
				outputWriter = new BufferedWriter(new FileWriter(outputFile));
				
				for (int i = 0; i< saveInformation.length; i++)
				{
					outputWriter.write(saveInformation[i] + "\t");
					
				}
				outputWriter.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				
			}
		}
		 public static void main(String args[])
		 {
			 new MainClass();
		 }
	}
	
	


