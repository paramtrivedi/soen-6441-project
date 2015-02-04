package discworld;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

/**
 * 
 * The saveGame class saves the file of the game and loads the game.
 * 
 * @author Jay Tanna
 * @version 1.00, 1 February 2015
 */

public class saveGame {
	private static Scanner scan;

	/**
	 * 
	 * This method saves all the information about the game.
	 * 
	 * @param city
	 * @param gamer
	 */
	public static void save(Vector<CityCard> city, ArrayList<Player> gamer){
		try{
			scan = new Scanner(System.in);
			File f;
			do
			{
				System.out.println("Enter file name to save the game state:");
				String file = scan.next();
				f = new File(file+".txt");
				if (f.exists()){
					System.out.println("File already exists. Please give another name!!!");
				}
			}while(f.exists());
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(String.format("%20s", "City Area")+"\tOwner\tPlayer1\tPlayer2\tPlayer3\tPlayer4\t  Trouble Maker\tBuilding\tDemon\tTroll\n");
			for(int i=0; i < 12; i++){
				output.write(city.get(i).toString()+"\n");
			}
			for(int j=0; j < gamer.size(); j++){
				output.write(gamer.get(j)+"\n");
			}
			int bank = Master.bank();
			output.write("Total Bank have " + bank + " Ankh-Morpork dollars.");
			output.close();
			System.out.println("file is created!!!");
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * This method loads the game.
	 * 
	 */
	public static void load(){
		try{
			scan = new Scanner(System.in);
			System.out.println("Enter file name to load the game:");
			String file = scan.next();
			File f = new File(file+".txt");
			if (!f.exists()){
				System.out.println("File not exists. Please give another name!!!");
				saveGame.load();
			}

			FileInputStream fstream = new  FileInputStream(f);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			System.out.println("Loading....");
			while ((strLine = br.readLine()) != null){
				System.out.println (strLine);
			}

			in.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
