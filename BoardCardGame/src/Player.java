import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


public class Player {
	/*******************************************************************
	 * Attributes
	 *******************************************************************/
	protected int id;
	protected int money;
	protected String color;
	protected int minion,building;

	protected PersonalityCard personalityCard;
	protected ArrayList<boardCard> holdingCards=new  ArrayList<boardCard>();

	public Player(String information,Vector<PersonalityCard> personalitycards,Vector<boardCard> greencard, Vector<boardCard>browncard){
		String[] info=information.split("-");
		int numGreenCard = 0,numBrownCard = 0;
		this.id=Integer.parseInt(info[0].substring(6,7));
		money=50;
		System.out.println("Player "+this.getID()+" has "+money+" Ankh-Morpork dollors.");
		this.color=info[1];
		minion=0;
		building=0;
		personalityCard=this.gain_personalityCard(personalitycards);
		System.out.println("Player "+this.getID()+" get personality card:"+this.personalityCard.toString());
		String greenInfo="",brownInfo="";
		for(int i=0;i<5;i++)
		{
			boardCard temp=this.gain_boardcard(greencard,browncard);

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
		System.out.println("\t"+numGreenCard+" Green Cards: "+ greenInfo+";");
		System.out.println("\t"+numBrownCard+" Brown Cards: "+ brownInfo+";");


	}
	public boardCard gain_boardcard(Vector<boardCard> g, Vector<boardCard>b){
		Random rn=new Random();
		int total;
		int randomNum;
		boardCard card=null;
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
	public String toString(){
		String s="";
		s=s+"Player "+this.getID()+"\t"+color+"\t"+this.personalityCard+"\n";
		boardCard temp;
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
		s=s+"Money: "+money+" Ankh-Morpork dollors\n";
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
	public void setMinion(int x){
		minion+=x;
	}
	public void setBuilding(int x){
		building+=x;
	}
}
