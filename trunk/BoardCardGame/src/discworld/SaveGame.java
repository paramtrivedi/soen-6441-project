package discworld;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import discworld.BoardCard.Symbols;

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

	public static void save(Vector<CityCard> city, ArrayList<Player> gamer, Vector<BoardCard> greenCard, Vector<BoardCard> brownCard)
	{
		try{
			scan = new Scanner(System.in);
			String file;
			File f;
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

			output.write("\nThe bank has "+ Master.bank() +" Ankh-Morpork dollars.\n");
			output.write("\nnumPlayer: "+Master.numPlayer());
			output.close();

			System.out.println("file is created!!!");
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * This method loads the game and stores the citycard information in city, 
	 * players information in player, greencard information in greencard, browncard information in browncard.
	 * @throws IOException 
	 * 
	 */
	public static boolean load() throws IOException{
		Vector<CityCard> city = new Vector<CityCard>();
		ArrayList<Player> gamer = new ArrayList<Player>();
		ArrayList<BoardCard> holdingCards=new  ArrayList<BoardCard>();

		boolean valid = false;
		boolean building = false;
		int bank = 0;
		int numPlayer=0;

		Vector<BoardCard> grCard = new Vector<BoardCard>();
		Vector<BoardCard> brCard = new Vector<BoardCard>();
		BufferedReader br=new BufferedReader(new FileReader("BoardCard.txt"));
		String li;
		String[] info;
		BoardCard board;
		int counter=0;
		while((li=br.readLine())!=null)
		{
			info=li.split(" ");
			board=new BoardCard(counter,info[0],info[1],Integer.parseInt(info[2]),info[3]);
			if(counter < 48){
				grCard.add(board);
			} else {
				brCard.add(board);
			}
			counter++;
		}
		br.close();

		try{
			scan = new Scanner(System.in);
			System.out.println("Enter file name to load the game:");
			String file = scan.next();
			File f = new File(file+".txt");
			if (!f.exists()){
				System.out.println("File not exists. Please give another name!!!");
				SaveGame.load();
			}

			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String thisLine = "";
			int playerCount = 0;
			int players = 0;

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

								holdingCards=new  ArrayList<BoardCard>();
								while((thisLine != null) && !(Pattern.matches("Player [1-4]'s current inventory:+", thisLine)))
								{
									if(!(thisLine.trim().equals("")))
									{
										if((thisLine.trim().substring(0, 1).equals("-")) && firstTime)
										{
											thisLine = thisLine.trim().substring(1, thisLine.length()).trim();
											String arr[] = thisLine.split(",");
											if(arr[0].contains("minions"))
											{
												String []a1 = arr[0].split(" ");
												int ab = Integer.parseInt(a1[0]);
												if(ab < 0 || ab > 12)
												{
													System.out.println("Invalid minion number!!! Please load the correct file!!!");
													load();
												}
												else
												{
													min = ab;
												}
											}
											if(arr[1].trim().contains("buildings"))
											{
												String []b1 = arr[1].trim().split(" ");
												int bc = Integer.parseInt(b1[0]);
												if(bc < 0 || bc > 6)
												{
													System.out.println("Invalid building number!!! Please load the correct file!!!");
													load();
												}
												else
												{
													bu = bc;
												}
											}
											if(arr[2].trim().contains("Ankh-Morpork dollars"))
											{
												String []c1 = arr[2].split(" ");
												money = Integer.parseInt(c1[1]);
											}
											firstTime = false;
										}
										else
										{
											if(!thisLine.equals("- Player cards:"))
											{
												String e = thisLine.trim();
												for(int k = 0; k < grCard.size(); k++)
												{
													if(e.equals(grCard.get(k).name))
													{
														int hMoney = grCard.get(k).dollar;
														int hId = grCard.get(k).id;
														String hName = grCard.get(k).name;
														String hDesc = grCard.get(k).des;
														ArrayList<Symbols> hSym = grCard.get(k).symbol;
														String symbolStr = "";
														if (hSym != null)
														{
															for (int v=0; v<hSym.size(); v++)
															{
																symbolStr += hSym.get(v).name();
															}
														}
														BoardCard b = new BoardCard(hId, hName, symbolStr, hMoney, hDesc);
														holdingCards.add(b);
														grCard.remove(k);
													}
												}
												for(int t = 0; t < brCard.size(); t++)
												{
													if(e.equals(brCard.get(t).name))
													{
														int hMoney = brCard.get(t).dollar;
														int hId = brCard.get(t).id;
														String hName = brCard.get(t).name;
														String hDesc = brCard.get(t).des;
														ArrayList<Symbols> hSym = brCard.get(t).symbol;
														String symbolStr = "";
														if (hSym != null)
														{
															for (int v=0; v<hSym.size(); v++)
															{
																symbolStr += hSym.get(v).name();
															}
														}
														BoardCard b = new BoardCard(hId, hName, symbolStr, hMoney, hDesc);
														holdingCards.add(b);
														grCard.remove(t);
													}
												}
												if(e.contains("Ankh-Morpork dollars"))
												{
													String []g = e.split(" ");
													bank = Integer.parseInt(g[3]);
												}
												if(e.contains("numPlayer:"))
												{
													String []h = e.split(" ");
													System.out.println(h[1]);
													numPlayer = Integer.parseInt(h[1]);
												}
											}
										}
									}
									thisLine = reader.readLine();
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
								Player pl = new Player(i, money, colorobj, min, bu, pc, holdingCards);
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

			Master.numPlayer = numPlayer;
			Master.bank = bank;
			Master.cityCards = city;
			Master.playerList = gamer;
			Master.greenCard = grCard;
			Master.brownCard = brCard;
			reader.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return valid;
	}
}