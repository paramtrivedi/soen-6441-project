import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Master {

	private static Scanner scan;
	private static String [] colors;
	private static String[] cards = {"Lord Selachii", "Lord Rust", "Lord de Worde", "Lord Vetinari", "Commander Vimes", "Dragon King of Arms", "Chrysopsase"};
	private static Vector<PersonalityCard> card = new Vector<PersonalityCard>(7);
	private static Vector<boardCard> greenCard = new Vector<boardCard>(48);
	private static Vector<boardCard> brownCard = new Vector<boardCard>(53);
	private static Vector<CityCard> cityCards=new Vector<CityCard>(12);
	private static int bank = 2000;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int input = 0;
		
		scan = new Scanner(System.in);
		boolean quit = false;
		for (int i=0; i<7; i++){
			String dummy = cards[i];
			PersonalityCard temp = new PersonalityCard (dummy);
			card.add(temp);
		}
		
		cityCards.add(new CityCard(1,"Dolly Sister"));
		cityCards.add(new CityCard(2,"Unreal Estate"));
		cityCards.add(new CityCard(3,"Dragon's Landing"));
		cityCards.add(new CityCard(4,"Small Gods"));
		cityCards.add(new CityCard(5,"The Scours"));
		cityCards.add(new CityCard(6,"The Hippo"));
		cityCards.add(new CityCard(7,"The Shades"));
		cityCards.add(new CityCard(8,"Dimwell"));
		cityCards.add(new CityCard(9,"Longwall"));
		cityCards.add(new CityCard(10,"isle of Gods"));
		cityCards.add(new CityCard(11,"Seven Sleepers"));
		cityCards.add(new CityCard(12,"Nap Hill"));
		
		BufferedReader br=new BufferedReader(new FileReader("BoardCard.txt"));
		String line;
		String[] info;
		boardCard bc;
		int counter=0;
		while((line=br.readLine())!=null)
		{
			info=line.split(" ");
			bc=new boardCard(counter,info[0],Integer.parseInt(info[1]),info[2]);
			if(counter < 48){
				greenCard.add(bc);
			} else {
				brownCard.add(bc);
			}
			counter++;
		}
		br.close();
		do{
			System.out.println("Select the one of the options:");
			System.out.println("0. Exit");
			System.out.println("1. New Game");
			System.out.println("2. Load Game");
			do{	
				while (!scan.hasNextInt()){
					System.out.println("Invalid Input!! Please try again...");
					scan.next();
				}
				input = scan.nextInt();
				if (input < 0){
					System.out.println("Please enter a positive number!!!");
				}
			} while (input <= -1);
			switch (input) {
			case 1:
				System.out.println("Enter number of players: (WARNING: Minimum 2 players and Maximum 4 players)");
				Master.newGame();
				break;

			case 2:
				saveGame.load();
				break;

			case 0:
				quit = true;
				break;

			default:
				System.out.println("Invalid input!!! Please do right selection...");
				break;
			}
		}while(!quit);
		System.out.println("Bye-bye!!!");
	}

	/*
	 * newGame method functionality is to take valid no. of players and
	 * chooses their color 
	 */
	public static void newGame(){
		ArrayList<Player> playerList=new ArrayList<Player>();
		int players = 0;
		String first;
		String second;
		String third;
		String fourth;
		do{	
			while (!scan.hasNextInt()){
				System.out.println("Invalid Input!! Please try again...");
				scan.next();
			}
			players = scan.nextInt();
			if (players < 0){
				System.out.println("Please enter a positive number!!!");
			}
		} while (players <= -1);

		if (players != 2 && players != 3 && players != 4){
			System.out.println("Please enter number between 2 and 4!!!");
			Master.newGame();
		} else {
			colors = new String [players];
			for (int i = 0; i < players; i++){
				if (i == 0){
					System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue, Red, Green and Yellow)");
					scan.nextLine();
					first = scan.nextLine();
					while (!first.equalsIgnoreCase("blue") && !first.equalsIgnoreCase("red") && !first.equalsIgnoreCase("green") && !first.equalsIgnoreCase("yellow")){
						System.out.println("Please choose color from Blue, Red, Green and Yellow!!! Please try again...");
						first = scan.nextLine();
					}
					colors[i] = "player" + (i+1) + "-" + first;
					Player gamer1 = new Player (colors[i], card, greenCard, brownCard);
					playerList.add(gamer1);
					bank = bank - 50;
					System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
				} else if(i == 1){
					String[] values = colors[i-1].split("-");
					if (values[1].equalsIgnoreCase("blue")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Red, Green and Yellow)");
						second = scan.nextLine();
						while (!second.equalsIgnoreCase("red") && !second.equalsIgnoreCase("green") && !second.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Red, Green and Yellow!!! Please try again...");
							second = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + second;
						Player gamer2 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer2);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (values[1].equalsIgnoreCase("red")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue, Green and Yellow)");
						second = scan.nextLine();
						while (!second.equalsIgnoreCase("blue") && !second.equalsIgnoreCase("green") && !second.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Blue, Green and Yellow!!! Please try again...");
							second = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + second;
						Player gamer2 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer2);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue, Red and Yellow)");
						second = scan.nextLine();
						while (!second.equalsIgnoreCase("blue") && !second.equalsIgnoreCase("red") && !second.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Blue, Red and Yellow!!! Please try again...");
							second = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + second;
						Player gamer2 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer2);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue, Red and Green)");
						second = scan.nextLine();
						while (!second.equalsIgnoreCase("blue") && !second.equalsIgnoreCase("red") && !second.equalsIgnoreCase("green")){
							System.out.println("Please choose color from Blue, Red and Green!!! Please try again...");
							second = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + second;
						Player gamer2 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer2);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					}
				} else if (i == 2){
					String[] values = colors[i-1].split("-");
					String[] parts = colors[i-2].split("-");
					if (parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("red")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Green and Yellow)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("green") && !third.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Green and Yellow!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
						Player gamer3 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer3);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Red and Yellow)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("red") && !third.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Red and Yellow!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
						Player gamer3 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer3);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Red and Green)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("red") && !third.equalsIgnoreCase("green")){
							System.out.println("Please choose color from Red and Green!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
						Player gamer3 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer3);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue and Yellow)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("blue") && !third.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Blue and Yellow!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
						Player gamer3 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer3);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue and Green)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("blue") && !third.equalsIgnoreCase("green")){
							System.out.println("Please choose color from Blue and Green!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
						Player gamer3 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer3);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("blue")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Green and Yellow)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("green") && !third.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Green and Yellow!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
						Player gamer3 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer3);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue and Red)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("blue") && !third.equalsIgnoreCase("red")){
							System.out.println("Please choose color from Blue and Red!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
						Player gamer3 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer3);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("blue")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Red and Yellow)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("red") && !third.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Red and Yellow!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
						Player gamer3 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer3);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("red")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue and Yellow)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("blue") && !third.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Blue and Yellow!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
						Player gamer3 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer3);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("blue")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Red and Green)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("red") && !third.equalsIgnoreCase("green")){
							System.out.println("Please choose color from Red and Green!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
						Player gamer3 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer3);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("red")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue and Green)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("blue") && !third.equalsIgnoreCase("green")){
							System.out.println("Please choose color from Blue and Green!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
						Player gamer3 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer3);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue and Red)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("blue") && !third.equalsIgnoreCase("red")){
							System.out.println("Please choose color from Blue and Red!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
						Player gamer3 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer3);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					}
				} else if (i == 3){
					String[] values = colors[i-1].split("-");
					String[] parts = colors[i-2].split("-");
					String[] dummy = colors[i-3].split("-");
					if (dummy[1].equalsIgnoreCase("blue") && parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("green")){
						System.out.println("\nPlayer "+ (i+1) + " left with Yellow color...");
						fourth = "yellow";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("blue") && parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("\nPlayer "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("blue") && parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("\nPlayer "+ (i+1) + " left with Red color...");
						fourth = "red";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("blue") && parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("red")){
						System.out.println("\nPlayer "+ (i+1) + " left with Yellow color...");
						fourth = "yellow";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("blue") && parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("red")){
						System.out.println("\nPlayer "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("blue") && parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("green")){
						System.out.println("\nPlayer "+ (i+1) + " left with Red color...");
						fourth = "red";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("red") && parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("\nPlayer "+ (i+1) + " left with Blue color...");
						fourth = "blue";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("red") && parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("blue")){
						System.out.println("\nPlayer "+ (i+1) + " left with Yellow color...");
						fourth = "yellow";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("red") && parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("blue")){
						System.out.println("\nPlayer "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("red") && parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("green")){
						System.out.println("\nPlayer "+ (i+1) + " left with Blue color...");
						fourth = "blue";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("red") && parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("green")){
						System.out.println("\nPlayer "+ (i+1) + " left with Yellow color...");
						fourth = "yellow";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("red") && parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("\nPlayer "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("green") && parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("blue")){
						System.out.println("\nPlayer "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("green") && parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("red")){
						System.out.println("\nPlayer "+ (i+1) + " left with Blue color...");
						fourth = "blue";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("green") && parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("red")){
						System.out.println("\nPlayer "+ (i+1) + " left with Yellow color...");
						fourth = "yellow";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("green") && parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("\nPlayer "+ (i+1) + " left with Red color...");
						fourth = "red";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("green") && parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("\nPlayer "+ (i+1) + " left with Blue color...");
						fourth = "blue";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("green") && parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("blue")){
						System.out.println("\nPlayer "+ (i+1) + " left with Yellow color...");
						fourth = "yellow";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("yellow") && parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("red")){
						System.out.println("\nPlayer "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("yellow") && parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("green")){
						System.out.println("\nPlayer "+ (i+1) + " left with Red color...");
						fourth = "red";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("yellow") && parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("green")){
						System.out.println("\nPlayer "+ (i+1) + " left with Blue color...");
						fourth = "blue";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("yellow") && parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("blue")){
						System.out.println("\nPlayer "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("yellow") && parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("red")){
						System.out.println("\nPlayer "+ (i+1) + " left with Blue color...");
						fourth = "blue";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					} else if (dummy[1].equalsIgnoreCase("yellow") && parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("blue")){
						System.out.println("\nPlayer "+ (i+1) + " left with Red color...");
						fourth = "red";
						colors[i] = "player" + (i+1) + "-" + fourth;
						Player gamer4 = new Player (colors[i], card, greenCard, brownCard);
						playerList.add(gamer4);
						bank = bank - 50;
						System.out.println("Total Bank holds: "+bank+" Ankh-Morpork dollars.");
					}
				}
			}
			int maxPlayer=-1,max=0;
			dice rollDice = new dice();
			for(int i=0; i<players; i++){
				System.out.println("Player "+(i+1)+ " rolls the dice: ");
				int num = rollDice.roll();
				System.out.println("Player "+(i+1)+ " got the number: "+num);
				if(num > max) 
				{
					max=num;
					maxPlayer=i;
				}
			}
			System.out.println("Player "+(maxPlayer+1)+" got highest number "+max+" so he can play first.");
			playGames(maxPlayer,playerList);
		}
	}
	
	public static int Menu(){
		int input=0;
		System.out.println("Select the one of the options(0-12):");
		System.out.println(" 0. Exit");
		System.out.println(" 1. Save Game");
		System.out.println(" 2. Load Game");
		System.out.println(" 3. Put Minion");
		System.out.println(" 4. Put Building");
		System.out.println(" 5. Put Trouble Maker");
		System.out.println(" 6. Put Demon");
		System.out.println(" 7. Put Troll");
		System.out.println(" 8. Remove Minion");
		System.out.println(" 9. Remove Building");
		System.out.println("10. Remove Trouble Maker");
		System.out.println("11. Remove Demon");
		System.out.println("12. Remove Troll");
		do{	
			while (!scan.hasNextInt()){
				System.out.println("Invalid Input!! Please try again...");
				scan.next();
			}
			input = scan.nextInt();
			if (input < 0){
				System.out.println("Please enter a positive number!!!");
			}
		} while (input <= -1);
		return input;
	}
	public static int ChooseCity(){
		int input=0;
		System.out.println("Select the one city(1-12):");
		System.out.println(" 1. "+cityCards.get(1).Name());
		System.out.println(" 2. "+cityCards.get(2).Name());
		System.out.println(" 3. "+cityCards.get(3).Name());
		System.out.println(" 4. "+cityCards.get(4).Name());
		System.out.println(" 5. "+cityCards.get(5).Name());
		System.out.println(" 6. "+cityCards.get(6).Name());
		System.out.println(" 7. "+cityCards.get(7).Name());
		System.out.println(" 8. "+cityCards.get(8).Name());
		System.out.println(" 9. "+cityCards.get(9).Name());
		System.out.println("10. "+cityCards.get(10).Name());
		System.out.println("11. "+cityCards.get(11).Name());
		System.out.println("12. "+cityCards.get(12).Name());
		do{	
			while (!scan.hasNextInt()){
				System.out.println("Invalid Input!! Please try again...");
				scan.next();
			}
			input = scan.nextInt();
			if (input <=0){
				System.out.println("Please enter a positive number!!!");
			}else if(input>12)
				System.out.println("Please enter a number less than 13!!!");
			
		} while (input <= 0 || input > 12);
		return input;
	}
	public static void playGames(int num,ArrayList<Player> playerList){
		
		int input = 0;
		int numCity;
		CityCard tempCityCard;
		boolean quit = false;
		do{
			System.out.println("Player"+num+" Start to Play:");
			input=Menu();
		
		switch (input) {
		case 1:
			System.out.println("Saving...");
			saveGame.save();
			break;

		case 2:
			saveGame.load();
			break;
		case 3:
			numCity=ChooseCity();
			tempCityCard=cityCards.get(numCity);
			tempCityCard.putMinion(playerList.get(num));
			break;

		case 4:
			numCity=ChooseCity();
			tempCityCard=cityCards.get(numCity);
			tempCityCard.build(playerList.get(num));
			break;
		case 5:
			numCity=ChooseCity();
			tempCityCard=cityCards.get(numCity);
			tempCityCard.putTM();
			break;

		case 6:
			numCity=ChooseCity();
			tempCityCard=cityCards.get(numCity);
			tempCityCard.putDemon();
			break;
		case 7:
			numCity=ChooseCity();
			tempCityCard=cityCards.get(numCity);
			tempCityCard.putTrolls();
			break;

		case 8:
			numCity=ChooseCity();
			tempCityCard=cityCards.get(numCity);
			tempCityCard.removeMinion(playerList.get(num));
			break;
		case 9:
			numCity=ChooseCity();
			tempCityCard=cityCards.get(numCity);
			tempCityCard.destory();
			break;

		case 10:
			numCity=ChooseCity();
			tempCityCard=cityCards.get(numCity);
			tempCityCard.removeTM();
			break;
		case 11:
			numCity=ChooseCity();
			tempCityCard=cityCards.get(numCity);
			tempCityCard.removeDemon();
			break;

		case 12:
			numCity=ChooseCity();
			tempCityCard=cityCards.get(numCity);
			tempCityCard.removeTrolls();
		case 0:
			quit = true;
			break;

		default:
			System.out.println("Invalid input!!! Please do right selection...");
			break;
		}
		num=num%playerList.size();
	}while(!quit);
	System.out.println("Bye-bye!!!");
	}
}