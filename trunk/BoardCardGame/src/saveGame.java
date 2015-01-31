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

public class saveGame {
	private static Scanner scan;

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
			for(int i=0; i < 12; i++){
				output.write(city.get(i).toString()+"\n");
			}
			for(int j=0; j < gamer.size(); j++){
				output.write(gamer.toString()+"\n");
			}
			output.close();
			System.out.println("file is created");
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void load(){
		try{
			scan = new Scanner(System.in);
			System.out.println("Enter file name to load the game:");
			String file = scan.next();
			File f = new File(file+".txt");
			if (!f.exists()){
				System.out.println("File already exists. Please give another name!!!");
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
