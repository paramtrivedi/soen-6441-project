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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The saveGame class saves the file of the game and loads the game.
 * 
 * @author Mohit Pujara
 * @version 3.00, 2 April 2015
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
			String file;
			File f;
			File f2;
			do
			{
				System.out.println("Enter file name to save the game state:");
				file = scan.next();
				f = new File(file+".txt");
				if (f.exists())
				{
					System.out.println("File already exists. Please give another name!!!");
				}
			}while(f.exists());

			String file2 = file+"1";
			f2 = new File(file2+".txt");

			BufferedWriter output = new BufferedWriter(new FileWriter(f));

			output.write("Game State\n");
			output.write("----------\n");

			output.write("\nThere are "+ gamer.size() +" players:\n");

			for(int i=0; i<gamer.size(); i++)
			{
				output.write("Player "+ (i+1) +" ("+ gamer.get(i).getColor() +") is playing as "+ gamer.get(i).getPersonalityCard() +".\n");
			}

			output.write("\nCurrent state of the game board:\n");
			output.write("\n");
			output.write(String.format("%20s %15s %15s %15s %15s %15s %15s %15s", "area", "minions", "trouble?", "building?", "demons", "trolls", "benefit", "nearest City")+"\n");

			for(int j=0; j<12; j++)
			{
				output.write(String.format("%20s %15s %15s %15s %15s %15s %15s %15s", city.get(j).getName(), city.get(j).getMinions(), city.get(j).containTroubleMaker(), city.get(j).getOwner(), city.get(j).getDemons(), city.get(j).getTrolls(), city.get(j).getBenefit(), city.get(j).getNearestCitys())+"\n");
			}

			for(int k=0; k<gamer.size(); k++)
			{
				output.write("\nPlayer " + (k+1) + "'s current inventory:\n");
				output.write("\n- " + gamer.get(k).getMinion() + " minions, " + gamer.get(k).getBuilding() + " buildings, " + gamer.get(k).getMoney() + " Ankh-Morpork dollars\n");
				output.write("\n- Player cards:\n");
				for(int l=0; l<gamer.get(k).getHoldingCards().size(); l++)
				{
					output.write(gamer.get(k).getHoldingCards().get(l).Name());
					output.write("\n");
				}
			}

			output.write("The bank has "+ Master.bank() +" Ankh-Morpork dollars.");
			output.close();

			JSONObject cityarea = new JSONObject();

			JSONArray player = new JSONArray();

			for(int j=0; j < gamer.size(); j++)
			{
				JSONObject obj = new JSONObject();
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

			cityarea.put("numPlayer", Master.numPlayer());

			BufferedWriter output2 = new BufferedWriter(new FileWriter(f2));

			output2.write(SaveGame.base64Encode(cityarea.toString()));
			output2.close();
			System.out.println("file is created!!!");
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * This method loads the game and stores the citycard information in city, 
	 * players information in player, greencard information in greencard, browncard information in browncard.
	 * 
	 */
	public static boolean load(){
		Vector<CityCard> city = new Vector<CityCard>();
		ArrayList<Player> gamer = new ArrayList<Player>();

		JSONParser parser = new JSONParser();
		boolean valid = false;
		boolean building = false;
		int bank = 0;

		try{
			scan = new Scanner(System.in);
			System.out.println("Enter file name to load the game:");
			String file = scan.next();
			File f = new File(file+".txt");
			if (!f.exists()){
				System.out.println("File not exists. Please give another name!!!");
				SaveGame.load();
			}

			String file2 = file+"1";
			File f2 = new File(file2+".txt");
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String thisLine = "";
			int playerCount = 0;
			int players = 0;

			BufferedReader reader2 = new BufferedReader(new FileReader(f2));
			String line2 = SaveGame.base64Decode(reader2.readLine());
			reader2.close();
			Object obj = null;
			try
			{
				obj = parser.parse(line2);
			}
			catch(Exception e)
			{
				System.out.println(file2+".txt"+" is not proper. Please try again!!!");
				load();
			}

			JSONObject jsonObject = (JSONObject) obj;
			while ((thisLine = reader.readLine()) != null) 
			{
				if(Pattern.matches("There are [2-4] players:+", thisLine))
				{
					valid = true;
					Pattern p = Pattern.compile("[2-4]+");
					Matcher m2 = p.matcher(thisLine);

					if(m2.find())
					{
						players = Integer.parseInt(m2.group());
					}
					ArrayList<String> acolor = new ArrayList<String>();
					ArrayList<String> acard = new ArrayList<String>();
					for(int i=1; i <= players && ((thisLine = reader.readLine()) != null); i++) {
						String pattern = "Player [1-4]+ \\((blue|green|red|yellow)\\) is playing as .+";
						Pattern r = Pattern.compile(pattern);
						Matcher m = r.matcher(thisLine);
						if(m.find())
						{
							++playerCount;
							Pattern r1 = Pattern.compile("\\((blue|green|red|yellow)\\)");
							Matcher m1 = r1.matcher(thisLine);
							int ind = thisLine.lastIndexOf("as");
							acard.add(thisLine.substring(ind+2, thisLine.length()-1).trim());
							if(m1.find())
							{
								String color = m1.group().substring(1);
								color = color.substring(0,color.length()-1);
								acolor.add(color);
							}
						}
					}
					if(playerCount != players)
					{
						System.out.println("Invalid file content");
						return false;
					}
					reader.readLine();
					reader.readLine();
					reader.readLine();
					reader.readLine();
					for(int c = 0; c < 12; c++)
					{
						String line = reader.readLine();
						int id = (c+1);

						String area = line.substring(0, 20).trim();

						String minions = line.substring(21, 36).trim();

						String [] x = minions.split(",");
						Vector<Integer> minion = new Vector<Integer>();
						for(int w = 0; w < 4; w++)
						{
							x[w] = x[w].replaceAll("[\\[\\](){}]","");
							minion.add(Integer.parseInt(x[w].trim()));
						}

						String TM = line.substring(37, 52).trim();
						boolean troubleMaker = Boolean.parseBoolean(TM);

						String build = line.substring(53, 68).trim();
						int owner = Integer.parseInt(build);
						if(!build.equals("-1"))
						{
							building = true;
						}

						String demons = line.substring(69, 84).trim();
						int demon = Integer.parseInt(demons);

						String trolls = line.substring(85, 100).trim();
						int troll = Integer.parseInt(trolls);

						String benefit = line.substring(101, 116).trim();
						int ben = Integer.parseInt(benefit);

						String near = line.substring(117, 132).trim();
						String []nArr = near.split(",");
						byte[] nCity = new byte[nArr.length];
						for(int y=0; y<nArr.length; y++)
						{
							nCity[y] = Byte.valueOf(nArr[y]);
						}
						CityCard card = new CityCard(id, area, owner, minion, troubleMaker, building, demon, troll, nCity, ben);
						city.add(card);
					}
					reader.readLine();
					thisLine = reader.readLine();
					JSONArray player = (JSONArray)jsonObject.get("players");
					int min=0;
					int bu=0;
					int money=0;
					for(int i=1;i<=players;i++)
					{
						if(thisLine != null)
						{
							if(Pattern.matches("Player [1-4]'s current inventory:+", thisLine))
							{
								thisLine = reader.readLine();
								boolean firstTime = true;
								System.out.println(thisLine);

								while((thisLine != null) && !(Pattern.matches("Player [1-4]'s current inventory:+", thisLine)))
								{
									if(!(thisLine.trim().equals("")))
									{
										if((thisLine.trim().substring(0, 1).equals("-")) && firstTime)
										{
											thisLine = thisLine.trim().substring(1, thisLine.length()).trim();
											String arr[] = thisLine.split(",");
											for(int j=0;j<arr.length;j++)
											{
												String abc = arr[j].trim();
												if(abc.contains("minions"))
												{
													String a = abc.substring(0, 1);
													min = Integer.parseInt(a);
												}
												else if(abc.contains("buildings"))
												{
													String b = abc.substring(0, 1);
													bu = Integer.parseInt(b);
												}
												else if(abc.contains("Ankh-Morpork dollars"))
												{
													String c = abc.substring(0, 2);
													money = Integer.parseInt(c);
												}
											}
											firstTime = false;
										}
										else
										{
											if(!thisLine.equals("- Player cards:"))
											{
												String e = thisLine.trim();
												if(e.contains("Ankh-Morpork dollars"))
												{
													String []g = e.split(" ");
													bank = Integer.parseInt(g[3]);
												}
											}
										}
									}
									thisLine = reader.readLine();
								}
								JSONObject playerObj = (JSONObject)player.get(i-1);
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
								CardColor colorobj=null;
								if(acolor.get(i-1).equalsIgnoreCase("blue"))
									colorobj = CardColor.blue;
								else if(acolor.get(i-1).equalsIgnoreCase("red"))
									colorobj = CardColor.red;
								else if(acolor.get(i-1).equalsIgnoreCase("green"))
									colorobj = CardColor.green;
								else if(acolor.get(i-1).equalsIgnoreCase("yellow"))
									colorobj = CardColor.yellow;

								PersonalityCard pc = new PersonalityCard(i, acard.get(i-1));
								int id = (i-1);
								Player pl = new Player(id, money, colorobj, min, bu, pc, holdingCards);
								gamer.add(pl);
							}
							else
							{
								thisLine = reader.readLine();		
							}
						}
					}
				}
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

			int numPlayer = ((Long)jsonObject.get("numPlayer")).intValue();
			Master.numPlayer = numPlayer;
			Master.bank = bank;
			Master.cityCards = city;
			Master.playerList = gamer;
			Master.greenCard = greenCard;
			Master.brownCard = brownCard;
			reader.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return valid;
	}

	/**
	 * This method encodes the data.
	 * @param token
	 */
	public static String base64Encode(String token) {
		byte[] encodedBytes = Base64.getEncoder().encode(token.getBytes());
		return new String(encodedBytes, Charset.forName("UTF-8"));
	}

	/**
	 * This method decodes the data.
	 * @param token
	 */
	public static String base64Decode(String token) {
		byte[] decodedBytes = Base64.getDecoder().decode(token.getBytes());
		return new String(decodedBytes, Charset.forName("UTF-8"));
	}
}