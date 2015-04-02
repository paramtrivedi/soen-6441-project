package discworld;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * This class generates board cards.
 * 
 * @author Shu Liu and Zixi Quan
 * @version 3.00, 2 April 2015
 *
 */
public class BoardCard {
	protected String name;
	protected int id;
	public static enum Symbols{Minion,Building,Assassination,RemoveTroubleMaker,Dollar,Scroll,Event,PlayCard,Interrupt};
	protected ArrayList<Symbols> symbol=new ArrayList<Symbols>();
	protected String des;
	protected int dollar;
	/**
	 * Constructs an instance of boardCard class with the given parameters.
	 * 
	 * This constructor will create a particular board card with the id,
	 * the name, the ability and the description.
	 * 
	 * @param id ID for the boardcard.
	 * @param name Name for the boardcard.
	 * @param s_symbols A vector of symbols on the boardcard.
	 * @param dollar The money can get from the bank.
	 * @param des The description of boardcard.
	 */
	public BoardCard(int id, String name, String s_Symbols,int dollar,String des){
		this.id=id;
		this.name=name;
		
		for(int i=0;i<s_Symbols.length();i++)
		{
			char s=s_Symbols.toUpperCase().charAt(i);
			switch(s){
			case 'M':	symbol.add(Symbols.Minion);break;
			case 'B':	symbol.add(Symbols.Building);break;
			case 'A':	symbol.add(Symbols.Assassination);break;
			case 'R':	symbol.add(Symbols.RemoveTroubleMaker);break;
			case 'E':	symbol.add(Symbols.Event);break;
			case 'S':	symbol.add(Symbols.Scroll);break;
			case 'P':	symbol.add(Symbols.PlayCard);break;
			case 'I':	symbol.add(Symbols.Interrupt);break;
			case 'D':	symbol.add(Symbols.Dollar);break;		
			}		
			
		}
		this.dollar=dollar;
		this.des=des;
	}
	/**
	 * Constructs an instance of boardCard class with the given parameters.
	 * 
	 * This constructor will create a particular board card with the name,
	 * the id, the sym and the description.
	 * 
	 * @param name Name for the boardcard.
	 * @param id   ID for the boardcard.
	 * @param sym  The Arraylist of the symbols.
	 * @param des  The description of the boardcard.
	 * @param dollar  The money can get by player.
	 */
	
	public BoardCard(String name, int id,  ArrayList<Symbols> sym, String des,int dollar) {
		super();
		this.name = name;
		this.id = id;
		this.symbol = sym;
		this.des = des;
		this.dollar = dollar;
	}

	/*******************************************************************
	 * Getters 
	 *******************************************************************/
	/** 
	 * This method will assassinate a minion of another player
	 * @author Zixi Quan
	 * @param cityCard The citycard plays by the player.
	 * @param p The current player who plays the game.
	 * */
	public boolean Assassination(CityCard cityCard, Player p){
		if(cityCard.containTroubleMaker())
		{
			int numPlayer = p.getID();
			if(cityCard.getMinions().get(numPlayer-1)>0)
				cityCard.removeMinion(p);
			return true;
		}else {
			System.out.println(cityCard.getName()+" does not have any trouble maker.");
			return false;
		}
	}
	
	public int dollar()
	{	
			return dollar;
	}
	public boolean Scroll(){
		return symbol.contains(Symbols.Scroll);
	}
	public boolean Event(){
		return symbol.contains(Symbols.Event);
	}
	public boolean Play_Another_Card(){
		
		return symbol.contains(Symbols.PlayCard);
	}
	public boolean Interrupt(){
		return symbol.contains(Symbols.Interrupt);
	}
	/**
	 * Gets the description in the board card.
	 * 
	 * @return description of the board card
	 */
	public String Description(){
		return des;
	}
	/**
	 * Gets the name in the board card.
	 * 
	 * @return name of the board card
	 */
	public String Name(){
		return name;
	}
	/**
	 * Gets the id in the board card.
	 * 
	 * @return id of the board card
	 */
	public int Id(){
		return id;
	}
	public ArrayList<Symbols> allSymbols(){
		return symbol;
	}
	public void setSymbol(ArrayList<Symbols> sym){
		this.symbol=sym;
	}
	/**
	 * This method plays the function of the board card.
	 * 
	 * @param p The current player who plays the card.
	 * @return If the board card works well, then return true; otherwise return false.
	 */
	public boolean action(Player player){
		CityCard tempCity=null;
		int inputPlayer,inputCard,inputCity;
		int num;
		switch(id+1){
		case 1: case 48://
			for(int i=0;i<Master.playerList.size();i++){
				if(i!=player.getID()-1&&Master.playerList.get(i).getMoney()>=2 ){
					Master.playerList.get(i).setMoney(Master.playerList.get(i).getMoney()-2);
					player.setMoney(player.getMoney()+2);
					System.out.println("Get $2 from Player "+(i+1));
				}
			}
			break;
		case 2:case 26:
			player.setMoney(player.getMoney()+Master.cityCards.get(9).minionNum(player));
			Master.bank-=Master.cityCards.get(9).minionNum(player);
			System.out.println("Get $"+Master.cityCards.get(9).minionNum(player)+" from The isle of Gods");
			break;
		case 4: case 10:// loan $10
			player.setMoney(player.getMoney()+10);
			Master.bank-=10;
			System.out.println("Loan $10 form bank. ");
			player.setLoan(12);
			break;
		case 5:case 15: //move a minion of another player
			for(int i=0;i<Master.playerList.size();i++){
				if(i!=player.getID()-1){
					System.out.println((i+1)+":Player "+(i+1));
				}
			}
			System.out.println("Choos an owener of the minion:");
			do{
				inputPlayer=Master.scan.nextInt();
			}while(inputPlayer<0 || inputPlayer>=Master.playerList.size()|| player.getID()==inputPlayer);
			Player tempP=Master.playerList.get(inputPlayer-1);
			System.out.println("Moving from following city");
			for(int i=0;i<Master.cityCards.size();i++){
				if(Master.cityCards.get(i).minionNum(tempP)>0)
				System.out.println((i+1)+". "+Master.cityCards.get(i));
			}
			
			do{
				inputCity = Master.scan.nextInt();
			}while(inputCity<0 || inputCity>=Master.cityCards.size()|| Master.cityCards.get(inputCity).minionNum(tempP)==0);
			
			tempCity=Master.cityCards.get(inputCity-1);
			tempCity.removeMinion(tempP);
			System.out.println("Put to one of following cities:");
			for(int i=0;i<tempCity.getNearestCity().length;i++){
				System.out.println((i+1)+" "+Master.cityCards.get(i));
			
			}
			do{
				inputCity = Master.scan.nextInt();
			}while(inputCity<0 || inputCity>=tempCity.getNearestCity().length);
			
			Master.cityCards.get(tempCity.getNearestCity()[inputCity-1]).putMinion(tempP);
			break;
		case 7: case 39://select one player
			for(int i=0;i<Master.playerList.size();i++){
				if(i!=player.getID()-1){
					System.out.println((i+1)+":Player "+(i+1));
				}
			}
			do{
				inputPlayer=Master.scan.nextInt();
			}while(inputPlayer<=0 || inputPlayer>Master.playerList.size()|| player.getID()==inputPlayer);
			System.out.println("Player "+inputPlayer+ " choose two card in your hand.");
			for(int i=0;i<2;i++){
				ArrayList<BoardCard> tempList=Master.playerList.get(inputPlayer-1).getHoldingCards();
				System.out.println("Choose one from following:");
				for(int j=0;j<tempList.size();j++)
				{
					System.out.println((j+1)+". "+tempList.get(j).Name()+tempList.get(j).allSymbols());
				}
				do{
					inputCard=Master.scan.nextInt();
				}while(inputCard<=0 || inputCard>tempList.size());
				player.getHoldingCards().add(tempList.get(inputCard-1));
				tempList.remove(inputCard-1);
			}
			break;
		case 8://player another two card, meiwan
		case 11: case 31:
			int die=Master.roll();
			String input;
			
			if(die>=7){ 
				player.setMoney(player.getMoney()+4);
				Master.bank-=4;
			}else if(die==1){
				System.out.println("Do you want to pay 2 dollar? Otherwise remove one minion(Y/N)");
				do{
					input=Master.scan.nextLine().toUpperCase();
				}while(!input.equals("Y") && !input.equals("N"));
				if(input.equals("Y")){
					player.setMoney(player.getMoney()-2);
					Master.bank+=2;
				}else{
					System.out.println("Choose a city from following:");
					for(int i=0;i<Master.cityCards.size();i++)
					{
						tempCity=Master.cityCards.get(i);
						if( tempCity.minionNum(player)>=0)
						{
							System.out.println(i+" :"+tempCity.Name());
						}
						
					}
					do{
						inputCity=Master.scan.nextInt();
					}while(inputCity<0 || inputCity>=Master.cityCards.size()|| Master.cityCards.get(inputCity).minionNum(player)<=0);
					Master.cityCards.get(inputCity).removeMinion(player);
				}
			}
			break;
		case 13:// 1$ or one card
		case 18:case 44: // refill
		case 20: 
			if(!Master.card.isEmpty())
		{
			int total=Master.card.size();
			Random rn= new Random();
			int randomNum=rn.nextInt(total);
			System.out.println(Master.card.get(randomNum).name);
		}
			break;
		case 21://choose one player, pay 5 or destoary building
			for(int i=0;i<Master.playerList.size();i++){
				if(i!=player.getID()-1){
					System.out.println(i+":Player "+(i+1));
				}
			}
			do{
				inputPlayer=Master.scan.nextInt();
			}while(inputPlayer<0 || inputPlayer>=Master.playerList.size()|| player.getID()!=inputPlayer+1);
			System.out.println("Player "+inputPlayer+" do you want to give $5?(Y/N) If not one of your building is destroy." );
			do{
				input=Master.scan.nextLine().toUpperCase();
			}while(!input.equals("Y") && !input.equals("N"));
			if(input.equals("Y")){
				player.money+=5;
				Master.playerList.get(inputPlayer).money-=5;
			}
			else{
				if(Master.playerList.get(inputPlayer).building<6)
				{
					for(int i=0;i<Master.cityCards.size();i++){
						if(Master.cityCards.get(i).owner==Master.playerList.get(inputPlayer).id)
							System.out.println((i+1)+" "+Master.cityCards.get(i).getName());
					}
					do{
						inputCity=Master.scan.nextInt();
					}while(inputCity<0 || inputCity>=Master.cityCards.size()||Master.cityCards.get(inputCity).owner!=Master.playerList.get(inputPlayer).id);
					Master.cityCards.get(inputCity).destroy();
					
				}
			}
		break;
		
		case 24://discard card
			num=0;
			for(int i=0 ;i<player.getHoldingCards().size();i++){
				System.out.println("Do yout want to discard card "+player.getHoldingCards().get(i).Name()+"?(Y/N)");
				do{
					input=Master.scan.nextLine().toUpperCase();
				}while(!input.equals("Y") && !input.equals("N"));
				if(input.equals("Y")){
					player.getHoldingCards().remove(i);
					num++;
					i--;
				}
			}
			player.setMoney(player.getMoney()+num);
			Master.bank-=num;
			System.out.println("Gain "+num+"dollar for discard "+num+"Card");
			break;
		case 25://discard card and draw4
			break;
		case 27:
			num=0;
			for(int i=0;i<Master.cityCards.size();i++){
				if(Master.cityCards.get(i).containTroubleMaker())
					num++;
			}
			
			player.setMoney(player.getMoney()+num);
			Master.bank-=num;
			System.out.println("Get $"+num+" for "+num+"trouble makers on the board");
			break;
		case 28:
			player.gain_boardcard(Master.greenCard, Master.brownCard);
			player.gain_boardcard(Master.greenCard, Master.brownCard);
			player.gain_boardcard(Master.greenCard, Master.brownCard);
			break;
		case 29: case 46:
			for(int i=0;i<Master.playerList.size();i++){
				if(i!=player.getID()-1){
					System.out.println(i+":Player "+(i+1));
				}
			}
			do{
				inputPlayer=Master.scan.nextInt();
			}while(inputPlayer<0 || inputPlayer>=Master.playerList.size()|| player.getID()!=inputPlayer+1);
			Master.playerList.get(inputPlayer).setMoney(Master.playerList.get(inputPlayer).getMoney()-3);
			player.setMoney(player.getMoney()+3);
			break;
		case 30:
			for(int i=0;i<Master.playerList.size();i++){
				if(i!=player.getID()-1){
					System.out.println(i+":Player "+(i+1));
				}
			}
			do{
				inputPlayer=Master.scan.nextInt();
			}while(inputPlayer<0 || inputPlayer>=Master.playerList.size()|| player.getID()!=inputPlayer+1);
			for(int i=0;i<player.getHoldingCards().size();i++){
				System.out.println(i+" "+player.getHoldingCards().get(i));
			}
			do{
				inputCard=Master.scan.nextInt();
			}while(inputCard<0 || inputCard>=player.getHoldingCards().size());
			BoardCard b=player.getHoldingCards().get(inputCard);
			player.getHoldingCards().remove(inputCard);
			Master.playerList.get(inputPlayer).getHoldingCards().add(b);
			System.out.println("Give the card "+b.Name()+" to Player "+(inputPlayer+1));
			Master.playerList.get(inputPlayer).setMoney(Master.playerList.get(inputPlayer).getMoney()-2);
			player.setMoney(player.getMoney()+2);
			System.out.println("Get $2 from Player "+(inputPlayer+1));
			break;
		
		case 32://discard one card
			System.out.println("Which card you want to discard?");
			for(int i=0 ;i<player.getHoldingCards().size();i++){
				System.out.println(i+" "+player.getHoldingCards().get(i).Name());
				
			}
			do{
				inputCard=Master.scan.nextInt();
			}while(inputCard<0 || inputCard>=player.getHoldingCards().size());
			player.getHoldingCards().remove(inputCard);
			break;
		case 33://remove one minion
			for(int i=0;i<Master.cityCards.size();i++){
				if(Master.cityCards.get(i).containTroubleMaker())
				System.out.println((i+1)+". "+Master.cityCards.get(i));
			}
			System.out.println("Removing form following cities:");
			do{
				inputCity = Master.scan.nextInt();
			}while(inputCity<0 || inputCity>=Master.cityCards.size()|| !Master.cityCards.get(inputCity).containTroubleMaker());
			
			tempCity=Master.cityCards.get(inputCity-1);
			tempCity.removeMinion(player);
			System.out.println("Put to one of following cities:");
			
			for(int i=0;i<tempCity.getNearestCity().length;i++){
				System.out.println((i+1)+" "+Master.cityCards.get(i));
			
			}
			do{
				inputCity = Master.scan.nextInt();
			}while(inputCity<0 || inputCity>=tempCity.getNearestCity().length);
			
			Master.cityCards.get(tempCity.getNearestCity()[inputCity-1]).putMinion(player);
			break;
		case 34://discard card for 2$
			num=0;
			for(int i=0 ;i<player.getHoldingCards().size();i++){
				System.out.println("Do yout want to discard card "+player.getHoldingCards().get(i).Name()+player.getHoldingCards().get(i).allSymbols()+"?(Y/N)");
				do{
					input=Master.scan.nextLine().toUpperCase();
				}while(!input.equals("Y") && !input.equals("N"));
				if(input.equals("Y")){
					player.getHoldingCards().remove(i);
					num++;
				}
			}
			player.setMoney(player.getMoney()+2*num);
			Master.bank-=2*num;
			System.out.println("Gain "+(2*num)+"dollar for discard "+num+"Card");
			
			break;
		case 38:case 41:// 4 card from draw deck
			player.gain_boardcard(Master.greenCard, Master.brownCard);
			player.gain_boardcard(Master.greenCard, Master.brownCard);
			player.gain_boardcard(Master.greenCard, Master.brownCard);
			player.gain_boardcard(Master.greenCard, Master.brownCard);
			break;
		case 43:
			PersonalityCard p=player.getPersonalityCard();
			player.gain_personalityCard(Master.card);
			System.out.println("You gain another card"+player.getPersonalityCard());
			Master.card.add(p);
			break;
		
			
		case 9:case 14: case 16: case 17:
		case 19: case 22: case 23:
		case 35: case 36: case 37:
		case 3: case 6: case 40:
		case 42: case 45: case 47:
			break;
			
			
		}
		return true;
	}
}
