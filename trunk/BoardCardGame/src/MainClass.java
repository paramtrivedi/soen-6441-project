import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;



public class MainClass {	
	
	private void updatePlayerStats()
	{
		
		playerid = saveInformation [pidLoc];
		playerName = saveInformation [plNLoc];
		randomcards = saveInformation [rdcarLoc];
		

		File f = new File("PlayerSave.txt");
		 if(f.exists()){
			  System.out.println("File existed.....Please Give another Name");
		  }else{
			  System.out.println(playerid);
				System.out.println(playerName);
				System.out.println(randomcards);
			 	}
		
		
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
		public void savePlayer(String filePath)
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
	
	


