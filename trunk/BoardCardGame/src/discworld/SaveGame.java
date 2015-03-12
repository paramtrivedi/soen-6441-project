package discworld;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The saveGame class saves the file of the game and loads the game.
 * 
 * @author Mohit Pujara
 * @version 2.00, 12 March 2015
 */

public class SaveGame {
	protected static Scanner scan;

	/**
	 * 
	 * This method saves all the information about the game.
	 * 
	 * @param city
	 * @param gamer
	 * @param greenCard
	 * @param brownCard
	 */
	@SuppressWarnings("unchecked")
	public static void save(Vector<CityCard> city, ArrayList<Player> gamer, Vector<BoardCard> greenCard, Vector<BoardCard> brownCard)
	{
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
				jo.put("troublemaker", city.get(i).containTroubleMaker());
				jo.put("building", city.get(i).isBuilding());
				jo.put("demons", city.get(i).getDemons());
				jo.put("trolls", city.get(i).getTrolls());
				jo.put("benefit", city.get(i).getBenefit());

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
				obj.put("color", gamer.get(j).getColor().toString());
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
					BoardCard bc = gamer.get(j).getHoldingCards().get(k);
					hObj.put("id", bc.id);
					hObj.put("name", bc.name);
					JSONArray gSymbol = new JSONArray();
					for(int a=0; a < bc.symbol.size(); a++)
					{
						gSymbol.add(bc.symbol.get(a).name());
					}
					hObj.put("symbol", gSymbol);
					hObj.put("money", gamer.get(j).getHoldingCards().get(k).dollar);
					hObj.put("description", gamer.get(j).getHoldingCards().get(k).des);
					hArr.add(hObj);
				}
				obj.put("holdingCards",hArr);
				player.add(obj);
			}

			cityarea.put("players", player);

			JSONArray gCard = new JSONArray();

			for(int g=0; g < greenCard.size(); g++)
			{
				JSONObject gObj = new JSONObject();
				gObj.put("id", greenCard.get(g).id);
				gObj.put("name", greenCard.get(g).name);
				JSONArray gSymbol = new JSONArray();
				for(int a=0; a < greenCard.get(g).symbol.size(); a++)
				{
					gSymbol.add(greenCard.get(g).symbol.get(a).name());
				}
				gObj.put("symbol", gSymbol);
				gObj.put("money", greenCard.get(g).dollar);
				gObj.put("description", greenCard.get(g).des);
				gCard.add(gObj);
			}

			cityarea.put("greenCard", gCard);

			JSONArray bCard = new JSONArray();

			for(int b=0; b < brownCard.size(); b++)
			{
				JSONObject bObj = new JSONObject();
				bObj.put("id", brownCard.get(b).id);
				bObj.put("name", brownCard.get(b).name);
				JSONArray bSymbol = new JSONArray();
				for(int a=0; a < brownCard.get(b).symbol.size(); a++)
				{
					bSymbol.add(brownCard.get(b).symbol.get(a).name());
				}
				bObj.put("symbol", bSymbol);
				bObj.put("money", brownCard.get(b).dollar);
				bObj.put("description", brownCard.get(b).des);
				bCard.add(bObj);
			}

			cityarea.put("brownCard", bCard);

			cityarea.put("bank", Master.bank());

			BufferedWriter output = new BufferedWriter(new FileWriter(f));

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
			Object obj;
			try
			{
				obj = parser.parse(line);
			}
			catch(Exception e)
			{
				System.out.println("This file is not proper. Please try again!!!");
				return;
			}

			JSONObject jsonObject = (JSONObject) obj;
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonOutput = gson.toJson(jsonObject);
			System.out.println(jsonOutput);
			JSONArray cityarea = (JSONArray)jsonObject.get("cityarea");

			for(int i = 0; i < cityarea.size(); i++)
			{	
				JSONObject cityObj = (JSONObject)cityarea.get(i);
				int owner = ((Long)cityObj.get("owner")).intValue();
				String name = (String)cityObj.get("name");
				int id = ((Long)cityObj.get("id")).intValue();
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
				int benefit = ((Long)cityObj.get("benefit")).intValue();
				JSONArray cArray = (JSONArray)cityObj.get("nearestcity");
				byte[] nCity = new byte[cArray.size()];
				for(int m=0; m < cArray.size(); m++)
				{
					nCity[m] = ((Long)cArray.get(m)).byteValue();
				}
				CityCard c = new CityCard(id, name, owner, minions, TM, build, demons, trolls, nCity, benefit);
				city.add(c);
			}

			JSONArray player = (JSONArray)jsonObject.get("players");
			ArrayList<Player> gamer = new ArrayList<Player>();
			for(int j=0; j < player.size(); j++)
			{
				JSONObject playerObj = (JSONObject)player.get(j);
				int money = ((Long)playerObj.get("money")).intValue();
				String color = (String)playerObj.get("color");
				CardColor pColor = CardColor.valueOf(color);
				int id = ((Long)playerObj.get("id")).intValue();
				int minion = ((Long)playerObj.get("minion")).intValue();
				JSONObject PC = (JSONObject)playerObj.get("personalityCard");
				PersonalityCard pCard = new PersonalityCard(((Long)PC.get("id")).intValue(),(String)PC.get("name"));
				int build = ((Long)playerObj.get("building")).intValue();
				JSONArray holdC = (JSONArray)playerObj.get("holdingCards");
				ArrayList<BoardCard> holdingCards=new  ArrayList<BoardCard>();
				for(int k=0;k<holdC.size();k++)
				{
					JSONObject hCardObj = (JSONObject)holdC.get(k);
					int hMoney = ((Long)hCardObj.get("money")).intValue();
					int hId = ((Long)hCardObj.get("id")).intValue();
					String hName = (String)hCardObj.get("name");
					String hDesc = (String)hCardObj.get("description");
					JSONArray hSymbol = (JSONArray)hCardObj.get("symbol");
					String symbolStr = "";
					if(hSymbol != null)
					{

						for(int m=0;m<hSymbol.size();m++)
						{
							symbolStr += ((String)hSymbol.get(m)).substring(0,1);
						}
					}
					BoardCard b = new BoardCard(hId, hName, symbolStr, hMoney, hDesc);
					holdingCards.add(b);
				}
				Player p = new Player(id, money, pColor, minion, build, pCard, holdingCards);
				gamer.add(p);
			}

			JSONArray gCard = (JSONArray)jsonObject.get("greenCard");
			Vector<BoardCard> greenCard = new Vector<BoardCard>();
			for(int g=0; g<gCard.size(); g++)
			{
				JSONObject gObj = (JSONObject)gCard.get(g);
				int gId = ((Long)gObj.get("id")).intValue();
				String gName = (String)gObj.get("name");
				JSONArray gSymbol = (JSONArray)gObj.get("symbol");
				String symbolStr = "";
				if(gSymbol != null)
				{
					for(int z=0; z < gSymbol.size(); z++)
					{
						symbolStr += ((String)gSymbol.get(z)).substring(0,1);
					}
				}
				int gMoney = ((Long)gObj.get("money")).intValue();
				String gDesc = (String)gObj.get("description");
				BoardCard b = new BoardCard(gId, gName, symbolStr, gMoney, gDesc);
				greenCard.add(b);
			}

			JSONArray bCard = (JSONArray)jsonObject.get("brownCard");
			Vector<BoardCard> brownCard = new Vector<BoardCard>();
			for(int b=0; b<bCard.size(); b++)
			{
				JSONObject bObj = (JSONObject)bCard.get(b);

				int bId = ((Long)bObj.get("id")).intValue();
				String bName = (String)bObj.get("name");
				JSONArray bSymbol = (JSONArray)bObj.get("symbol");
				String symbolStr = "";
				if(bSymbol != null)
				{
					for(int z=0; z < bSymbol.size(); z++)
					{
						symbolStr += ((String)bSymbol.get(z)).substring(0,1);
					}
				}
				int bMoney = ((Long)bObj.get("money")).intValue();
				String bDesc = (String)bObj.get("description");
				BoardCard a = new BoardCard(bId, bName, symbolStr, bMoney, bDesc);
				brownCard.add(a);
			}

			int bank = ((Long)jsonObject.get("bank")).intValue();
			Master.bank=bank;
			Master.cityCards=city;
			Master.playerList=gamer;
			Master.greenCard=greenCard;
			Master.brownCard=brownCard;
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * this method encodes the data
	 * @param token
	 */
	public static String base64Encode(String token) {
		byte[] encodedBytes = Base64.getEncoder().encode(token.getBytes());
		return new String(encodedBytes, Charset.forName("UTF-8"));
	}

	/**
	 * this method decodes the data
	 * @param token
	 */
	public static String base64Decode(String token) {
		byte[] decodedBytes = Base64.getDecoder().decode(token.getBytes());
		return new String(decodedBytes, Charset.forName("UTF-8"));
	}
}