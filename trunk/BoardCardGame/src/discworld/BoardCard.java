package discworld;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * This class generates board cards.
 * 
 * @author Shu Liu and Zixi Quan
 * @version 2.00, 12 March 2015
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
	 * @param id ID for the boardcard
	 * @param name Name for the boardcard
	 * @param s_symbols
	 * @param dollar
	 * @param des
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
	 * @param name
	 * @param id
	 * @param sym
	 * @param des
	 * @param dollar
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
	 * @author zixi quan
	 * @param cityCard
	 * @param p 
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
	
	public int dollar(){
		
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
	 * @return description
	 */
	public String Description(){
		return des;
	}
	/**
	 * Gets the name in the board card.
	 * 
	 * @return name
	 */
	public String Name(){
		return name;
	}
	/**
	 * Gets the id in the board card.
	 * 
	 * @return id
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
	public boolean action(Player player){
		CityCard tempCity=null;
		int inputPlayer,inputCard;
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
			//player.setLoan(12);
			break;
		case 5:case 15: //move a minion of another player, meiwan
		case 7: case 39://select one player, meiwan
			for(int i=0;i<Master.playerList.size();i++){
				if(i!=player.getID()-1){
					System.out.println(i+":Player "+(i+1));
				}
			}
			break;
		case 8://player another two card, meiwan
		case 11: case 31:
			int die=Master.roll();
			String input;
			int inputCity;
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
			System.out.println(Master.card.get(randomNum).toString());
		}
		case 21://choose one player, pay 5 or destoary building
		break;
		
		case 24://discard card
			num=0;
			for(int i=0 ;i<player.getHoldingCards().size();i++){
				System.out.println("Do yout want to discard card "+player.getHoldingCards().get(i)+"?(Y/N)");
				do{
					input=Master.scan.nextLine().toUpperCase();
				}while(!input.equals("Y") && !input.equals("N"));
				if(input.equals("Y")){
					player.getHoldingCards().remove(i);
					num++;
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
				System.out.println(i+" "+player.getHoldingCards().get(i));
				
			}
			do{
				inputCard=Master.scan.nextInt();
			}while(inputCard<0 && inputCard>=player.getHoldingCards().size());
			player.getHoldingCards().remove(inputCard);
			break;
		case 33://remove one minion
			break;
		case 34://discard card for 2$
			num=0;
			for(int i=0 ;i<player.getHoldingCards().size();i++){
				System.out.println("Do yout want to discard card "+player.getHoldingCards().get(i)+"?(Y/N)");
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
