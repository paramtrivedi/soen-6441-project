import java.util.Scanner;
import java.util.Vector;

public class Master {

	private static Scanner scan;
	private static String [] colors;
	private static String[] cards = {"Lord Selachii", "Lord Rust", "Lord de Worde", "Lord Vetinari", "Commander Vimes", "Dragon King of Arms", "Chrysopsase"};
	private static Vector<PersonalityCard> card = new Vector<PersonalityCard>(7);

	public static void main(String[] args) {

		int input = 0;
		scan = new Scanner(System.in);
		boolean quit = false;
		for (int i=0; i<7; i++){
			String dummy = cards[i];
			PersonalityCard temp = new PersonalityCard (dummy);
			card.add(temp);
		}
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
				System.out.println("Case-2 yet to implement...");
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
					} else if (values[1].equalsIgnoreCase("red")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue, Green and Yellow)");
						second = scan.nextLine();
						while (!second.equalsIgnoreCase("blue") && !second.equalsIgnoreCase("green") && !second.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Blue, Green and Yellow!!! Please try again...");
							second = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + second;
					} else if (values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue, Red and Yellow)");
						second = scan.nextLine();
						while (!second.equalsIgnoreCase("blue") && !second.equalsIgnoreCase("red") && !second.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Blue, Red and Yellow!!! Please try again...");
							second = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + second;
					} else if (values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue, Red and Green)");
						second = scan.nextLine();
						while (!second.equalsIgnoreCase("blue") && !second.equalsIgnoreCase("red") && !second.equalsIgnoreCase("green")){
							System.out.println("Please choose color from Blue, Red and Green!!! Please try again...");
							second = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + second;
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
					} else if (parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Red and Yellow)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("red") && !third.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Red and Yellow!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
					} else if (parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Red and Green)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("red") && !third.equalsIgnoreCase("green")){
							System.out.println("Please choose color from Red and Green!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
					} else if (parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue and Yellow)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("blue") && !third.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Blue and Yellow!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
					} else if (parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue and Green)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("blue") && !third.equalsIgnoreCase("green")){
							System.out.println("Please choose color from Blue and Green!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
					} else if (parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("blue")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Green and Yellow)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("green") && !third.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Green and Yellow!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
					} else if (parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue and Red)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("blue") && !third.equalsIgnoreCase("red")){
							System.out.println("Please choose color from Blue and Red!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
					} else if (parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("blue")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Red and Yellow)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("red") && !third.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Red and Yellow!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
					} else if (parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("red")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue and Yellow)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("blue") && !third.equalsIgnoreCase("yellow")){
							System.out.println("Please choose color from Blue and Yellow!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
					} else if (parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("blue")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Red and Green)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("red") && !third.equalsIgnoreCase("green")){
							System.out.println("Please choose color from Red and Green!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
					} else if (parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("red")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue and Green)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("blue") && !third.equalsIgnoreCase("green")){
							System.out.println("Please choose color from Blue and Green!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
					} else if (parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " select your color: (Choose From Blue and Red)");
						third = scan.nextLine();
						while (!third.equalsIgnoreCase("blue") && !third.equalsIgnoreCase("red")){
							System.out.println("Please choose color from Blue and Red!!! Please try again...");
							third = scan.nextLine();
						}
						colors[i] = "player" + (i+1) + "-" + third;
					}
				} else if (i == 3){
					String[] values = colors[i-1].split("-");
					String[] parts = colors[i-2].split("-");
					String[] dummy = colors[i-3].split("-");
					if (dummy[1].equalsIgnoreCase("blue") && parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " left with Yellow color...");
						fourth = "yellow";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("blue") && parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("blue") && parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " left with Red color...");
						fourth = "red";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("blue") && parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("red")){
						System.out.println("Player "+ (i+1) + " left with Yellow color...");
						fourth = "yellow";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("blue") && parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("red")){
						System.out.println("Player "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("blue") && parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " left with Red color...");
						fourth = "red";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("red") && parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " left with Blue color...");
						fourth = "blue";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("red") && parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("blue")){
						System.out.println("Player "+ (i+1) + " left with Yellow color...");
						fourth = "yellow";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("red") && parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("blue")){
						System.out.println("Player "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("red") && parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " left with Blue color...");
						fourth = "blue";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("red") && parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " left with Yellow color...");
						fourth = "yellow";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("red") && parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("green") && parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("blue")){
						System.out.println("Player "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("green") && parts[1].equalsIgnoreCase("yellow") && values[1].equalsIgnoreCase("red")){
						System.out.println("Player "+ (i+1) + " left with Blue color...");
						fourth = "blue";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("green") && parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("red")){
						System.out.println("Player "+ (i+1) + " left with Yellow color...");
						fourth = "yellow";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("green") && parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " left with Red color...");
						fourth = "red";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("green") && parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("yellow")){
						System.out.println("Player "+ (i+1) + " left with Blue color...");
						fourth = "blue";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("green") && parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("blue")){
						System.out.println("Player "+ (i+1) + " left with Yellow color...");
						fourth = "yellow";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("yellow") && parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("red")){
						System.out.println("Player "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("yellow") && parts[1].equalsIgnoreCase("blue") && values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " left with Red color...");
						fourth = "red";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("yellow") && parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("green")){
						System.out.println("Player "+ (i+1) + " left with Blue color...");
						fourth = "blue";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("yellow") && parts[1].equalsIgnoreCase("red") && values[1].equalsIgnoreCase("blue")){
						System.out.println("Player "+ (i+1) + " left with Green color...");
						fourth = "green";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("yellow") && parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("red")){
						System.out.println("Player "+ (i+1) + " left with Blue color...");
						fourth = "blue";
						colors[i] = "player" + (i+1) + "-" + fourth;
					} else if (dummy[1].equalsIgnoreCase("yellow") && parts[1].equalsIgnoreCase("green") && values[1].equalsIgnoreCase("blue")){
						System.out.println("Player "+ (i+1) + " left with Red color...");
						fourth = "red";
						colors[i] = "player" + (i+1) + "-" + fourth;
					}
				}
			}
		}
	}
}