package discworld;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/**
 * Player class is to give each player a personality card, fifty dollars, 
 * green cards and brown cards.
 * 
 * @author Shu Liu and Zixi Quan
 * @version 1.00, 1 February 2015
 *
 */

public class Player {
	/*******************************************************************
	 * Attributes
	 *******************************************************************/
	private int id;
	private int money;
	private String color;
	private int minion,building;
	private PersonalityCard personalityCard;
	private ArrayList<BoardCard> holdingCards=new  ArrayList<BoardCard>();
    
	/**
	 * 
	 * The Player constructor
	 * 
	 * @param information
	 * @param personalitycards
	 * @param greencard
	 * @param browncard
	 */
	public Player(String information,Vector<PersonalityCard> personalitycards,Vector<BoardCard> greencard, Vector<BoardCard>browncard){
		String[] info=information.split("-");
		int numGreenCard = 0,numBrownCard = 0;
		this.id=Integer.parseInt(info[0].substring(6,7));
		money=10;
		System.out.println("Player "+this.getID()+" has "+money+" Ankh-Morpork dollars.");
		this.color=info[1];
		minion=12;
		building=6;
		personalityCard=this.gain_personalityCard(personalitycards);
		System.out.println("Player "+this.getID()+" get personality card:"+this.personalityCard.toString());
		String greenInfo="",brownInfo="";
		for(int i=0;i<5;i++)
		{
			BoardCard temp=this.gain_boardcard(greencard,browncard);

			if(temp.Id()<48){
				numGreenCard++;
				greenInfo+=temp.Name()+"  ";
			}
			else{
				numBrownCard++;
				brownInfo+=temp.Name()+"  ";
			}
			holdingCards.add(temp);
		}
		System.out.println("Player "+this.getID()+" has "+holdingCards.size()+" Board Cards:");
		System.out.println("\t"+numGreenCard+" Green Cards: "+ greenInfo);
		System.out.println("\t"+numBrownCard+" Brown Cards: "+ brownInfo);
	}
	
	/**
	 * 
	 * The gain_boardcard method is for each player to get a random boardcard.
	 * 
	 * @param g
	 * @param b
	 * @return card
	 */
	public BoardCard gain_boardcard(Vector<BoardCard> g, Vector<BoardCard>b){
		Random rn=new Random();
		int total;
		int randomNum;
		BoardCard card=null;
		if(!g.isEmpty())
		{
			total=g.size();
			randomNum=rn.nextInt(total);
			card=g.get(randomNum);
			g.remove(randomNum);
		} else if(!b.isEmpty())
		{
			total=b.size();
			randomNum=48+rn.nextInt(total);
			card=b.get(randomNum);
			b.remove(randomNum);
		}
		return card;
	}

	/**
	 * 
	 * The gain_personalityCard is used for each player to get a personality card.
	 * 
	 * @param personalitycards
	 * @return card
	 */
	public PersonalityCard gain_personalityCard(Vector<PersonalityCard> personalitycards){
		Random rn=new Random();
		int total;
		int randomNum;
		PersonalityCard card=null;
		if(!personalitycards.isEmpty())
		{
			total=personalitycards.size();
			randomNum=rn.nextInt(total);
			card=personalitycards.get(randomNum);
			personalitycards.remove(randomNum);
		} 
		return card;
	}

	/**
	 * The method is used for output.
	 */
	public String toString(){
		String s="";
		s=s+"Player "+this.getID()+"\t Color: "+color+"\t Personality Card: "+this.personalityCard+"\n";
		BoardCard temp;
		String greenInfo="",brownInfo="";
		for(int i=0;i<holdingCards.size();i++)
		{
			temp=holdingCards.get(i);

			if(temp.Id()<48){
				greenInfo+=temp.Id()+"  ";
			}
			else{
				brownInfo+=temp.Id()+"  ";
			}
		}
		s=s+"Green Card: "+greenInfo+"\n";
		s=s+"Brown Card: "+brownInfo+"\n";
		s=s+"Money: "+money+" Ankh-Morpork dollars\n";
		s=s+"Number of Minions\t"+minion+"\n";
		s=s+"Number of Building\t"+building+"\n";
		return s;
	}
	public int getID(){
		return id;
	}
	public String Color(){
		return color;
	}
	public boolean putMinion(CityCard city,Vector<CityCard> cityCards){
		if(minion==0 )
		{
			System.out.println("You need to remove one minion from following cities:");
			for(int i=0;i<12;i++)
				if(cityCards.get(i).getMinions().get(this.id-1)>0)
					System.out.println(cityCards.get(i).getId()+"/t"+cityCards.get(i).Name());
			Scanner scan = new Scanner(System.in);
			int input;
			do{
				input=scan.nextInt();
			}while(input<13 && input >0 && cityCards.get(input-1).getMinions().get(this.id-1)>0);
			scan.close();
			city.putMinion(this);
			cityCards.get(input-1).removeMinion(this);

		}
		else{
			city.putMinion(this);
		}
		return true;
	}
	public boolean interrupt()
	{	
		boolean flag=false;
		for(int i=0;i<holdingCards.size();i++)
			if(holdingCards.get(i).Interrupt())
			{
				flag=true;
				break;
			}
		
		if(flag){
			System.out.println("Do you want to interrupt?(Y/N)");
			Scanner scan=new Scanner(System.in);
			String input;
			do{
				input=scan.next().toUpperCase();
			}while(input.length()==1 && (input.equals("Y")|| input.equals("N")));
			if(input.equals("Y"))
			{
				for(int i=0;i<holdingCards.size();i++)
					if(holdingCards.get(i).Interrupt())
					{
						System.out.println(i+"/t"+holdingCards.get(i).Name());
						
					}
				int num;
				do{
					num=scan.nextInt();
				}while(num>=0 && num<holdingCards.size()&&holdingCards.get(num).Interrupt());
				scan.close();
				holdingCards.remove(num);
				return true;
			}
			else 
			{
				scan.close();
				return false;
			}
		}
		return false;
		
		
	}
	public void refill(Vector<BoardCard> g,Vector<BoardCard> b) {
		if(holdingCards.size()<5)
		{
			System.out.println("Player "+this.getID()+" needs to refill");
			holdingCards.add(gain_boardcard(g,b));
			
		}
		
	}

	public boolean putBuilding(CityCard city,ArrayList<CityCard> cities){
		if(building==0 )
		{
			System.out.println("You need to remove one minion from following cities:");
			for(int i=0;i<12;i++)
				if(cities.get(i).isBuilding() && cities.get(i).getOwner()==this.id )
					System.out.println(cities.get(i).getId()+"/t"+cities.get(i).Name());
			Scanner scan = new Scanner(System.in);
			int input;
			do{
				input=scan.nextInt();
			}while(input<13 && input >0 && cities.get(input).getOwner()==this.id);
			scan.close();
			city.build(this);
			cities.get(input-1).destroy(this);

		}
		else{
			city.putMinion(this);
		}
		return true;
	}
	void setMinion(int x){
		minion+=x;
		
	}
	
	public void setBuilding(int x){
		building+=x;
	}
	
	public int personalityCard(){
		return personalityCard.ID();
	}
	
	public int ID(){
		return this.id;
	}

	public int Money() {
		return money;
	}
}