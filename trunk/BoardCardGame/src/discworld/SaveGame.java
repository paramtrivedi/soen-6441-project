package discworld;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The saveGame class saves the file of the game and loads the game.
 * 
 * @author Mohit Pujara
 * @version 2.00, 12 March 2015
 */

public class SaveGame {
	private static Scanner scan;

	/**
	 * 
	 * This method saves all the information about the game.
	 * 
	 * @param city
	 * @param gamer
	 */
	@SuppressWarnings("unchecked")
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

			JSONArray citycards = new JSONArray();

			for(int  i=0;i<city.size();i++)
			{
				JSONObject jo = new JSONObject();
				jo.put("id", city.get(i).getId());
				jo.put("name",city.get(i).getName());
				jo.put("owner",city.get(i).getOwner());
				JSONArray minions = new JSONArray();
				Object[] mi = city.get(i).getMinions().toArray();
				for(int j=0;j<mi.length;j++)
				{
					minions.add(mi[j]);
				}
				jo.put("minions",minions);
				jo.put("troublemaker", city.get(i).isTroubleMaker());
				jo.put("building", city.get(i).isBuilding());
				jo.put("demons", city.get(i).getDemons());
				jo.put("trolls", city.get(i).getTrolls());

				JSONArray near = new JSONArray();
				byte[] b = city.get(i).getNearestCity();
				for(int j=0;j<b.length;j++)
				{
					near.add(b[j]);
				}
				jo.put("nearestcity", near);
				citycards.add(jo);
			}
			JSONObject cityarea = new JSONObject();
			cityarea.put("cityarea", citycards);

			JSONArray player = new JSONArray();

			for(int j=0; j < gamer.size(); j++)
			{
				JSONObject obj = new JSONObject();
				obj.put("id", gamer.get(j).getId());
				obj.put("money", gamer.get(j).getMoney());
				obj.put("color", gamer.get(j).getColor());
				obj.put("minion", gamer.get(j).getMinion());
				obj.put("building", gamer.get(j).getBuilding());
				JSONObject pcObj = new JSONObject();
				pcObj.put("id", gamer.get(j).getPersonalityCard().ID());
				pcObj.put("name", gamer.get(j).getPersonalityCard().toString());
				obj.put("personalityCard", pcObj);
				JSONArray hArr = new JSONArray();
				for(int k = 0; k < gamer.get(j).getHoldingCards().size(); k++)
				{
					JSONObject hObj = new JSONObject();
					hObj.put("id", gamer.get(j).getHoldingCards().get(k).id);
					hObj.put("name", gamer.get(j).getHoldingCards().get(k).name);
					hObj.put("ability", gamer.get(j).getHoldingCards().get(k).ability);
					hObj.put("money", gamer.get(j).getHoldingCards().get(k).money);
					hObj.put("description", gamer.get(j).getHoldingCards().get(k).des);
					hArr.add(hObj);
				}
				obj.put("holdingCards",hArr);
				player.add(obj);
			}

			//JSONObject players = new JSONObject();
			cityarea.put("players", player);
			cityarea.put("bank", Master.bank());

			// end
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			/*output.write(String.format("%20s", "City Area")+"\tOwner\tPlayer1\tPlayer2\tPlayer3\tPlayer4\t  Trouble Maker\tBuilding\tDemon\tTroll\n");
			for(int i=0; i < 12; i++){
				output.write(city.get(i).toString()+"\n");
			}
			for(int j=0; j < gamer.size(); j++){
				output.write(gamer.get(j)+"\n");
			}
			int bank = Master.bank();
			output.write("Total Bank have " + bank + " Ankh-Morpork dollars.");*/

			output.write(SaveGame.base64Encode(cityarea.toString()));
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
		Vector<CityCard> city = new Vector<CityCard>();
		
		JSONParser parser = new JSONParser();
		try{
			scan = new Scanner(System.in);
			System.out.println("Enter file name to load the game:");
			String file = scan.next();
			File f = new File(file+".txt");
			if (!f.exists()){
				System.out.println("File not exists. Please give another name!!!");
				SaveGame.load();
			}
			
			
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line = SaveGame.base64Decode(reader.readLine());
			reader.close();
			

			Object obj = parser.parse(line);
			
			
			
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONArray cityarea = (JSONArray)jsonObject.get("cityarea");
			
			for(int i = 0; i < cityarea.size(); i++)
			{
				
				JSONObject cityObj = (JSONObject)cityarea.get(i);
				int owner = ((Long)cityObj.get("owner")).intValue();
				String name = (String)cityObj.get("name");
				int id = ((Long)cityObj.get("id")).intValue();
				//boolean TM = Boolean.getBoolean((String)cityObj.get("troublemaker"));
				boolean TM = (Boolean)cityObj.get("troublemaker");
				
				JSONArray cityArray = (JSONArray)cityObj.get("minions");
				Vector<Integer> minions = new Vector<Integer>();
				for(int k=0; k < cityArray.size(); k++)
				{
					minions.add(((Long)cityArray.get(k)).intValue());
				}
				boolean build = (Boolean)cityObj.get("building");
				int trolls = ((Long)cityObj.get("trolls")).intValue();
				int demons = ((Long)cityObj.get("demons")).intValue();
				JSONArray cArray = (JSONArray)cityObj.get("nearestcity");
				byte[] nCity = new byte[cArray.size()];
				for(int m=0; m < cArray.size(); m++)
				{
					nCity[m] = Byte.parseByte((String)cArray.get(m));
				}
				CityCard c = new CityCard(id, name, owner, minions, TM, build, demons, trolls,nCity);
				city.add(c);
			}
			
			JSONArray player = (JSONArray)jsonObject.get("players");
			ArrayList<Player> gamer = new ArrayList<Player>();
			for(int j=0; j < player.size(); j++)
			{
				JSONObject playerObj = (JSONObject)player.get(j);
				int money = ((Long)playerObj.get("money")).intValue();
				String color = (String)playerObj.get("color");
				int id = ((Long)playerObj.get("id")).intValue();
				int minion = ((Long)playerObj.get("minion")).intValue();
				JSONObject PC = (JSONObject)playerObj.get("personalityCard");
				PersonalityCard pCard = new PersonalityCard(Integer.parseInt((String)PC.get("id")),(String)PC.get("name"));
				int build = ((Long)playerObj.get("building")).intValue();
				JSONArray holdC = (JSONArray)player.get(j);
				ArrayList<BoardCard> holdingCards=new  ArrayList<BoardCard>();
				for(int k=0;k<holdC.size();k++)
				{
					JSONObject hCardObj = (JSONObject)holdC.get(k);
					int hMoney = ((Long)hCardObj.get("money")).intValue();
					int hId = ((Long)hCardObj.get("id")).intValue();
					String hName = (String)hCardObj.get("name");
					String hDesc = (String)hCardObj.get("description");
					JSONArray hAbility = (JSONArray)hCardObj.get("ability");
					Vector<Boolean> ability=new Vector<Boolean>(7);
					for(int l=0;l<hAbility.size();l++)
					{
						ability.add(Boolean.parseBoolean((String)hAbility.get(l)));
					}
					BoardCard b = new BoardCard(hName, hId, ability, hDesc, hMoney);
					holdingCards.add(b);
				}
				Player p = new Player(id, money, color, minion, build, pCard, holdingCards);
				gamer.add(p);
			}
			
			int bank = ((Long)jsonObject.get("bank")).intValue();
			/*FileInputStream fstream = new  FileInputStream(f);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			System.out.println("Loading....");
			while ((strLine = br.readLine()) != null){
				System.out.println (strLine);
			}

			in.close();*/
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public static String base64Encode(String token) {
	    byte[] encodedBytes = Base64.getEncoder().encode(token.getBytes());
	    return new String(encodedBytes, Charset.forName("UTF-8"));
	}


	public static String base64Decode(String token) {
	    byte[] decodedBytes = Base64.getDecoder().decode(token.getBytes());
	    return new String(decodedBytes, Charset.forName("UTF-8"));
	}
}
