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
	protected int minion;
	protected PersonalityCard personalityCard;
	protected ArrayList<boardCard> holdingCards=new  ArrayList<boardCard>();
	
	public Player(int id,Vector<PersonalityCard> personalitycards,Vector<boardCard> greencard, Vector<boardCard>browncard){
		this.id=id;
		money=50;
		//invoke the function to choose a color
		minion=3;
		personalityCard=this.gain_personalityCard(personalitycards);
		for(int i=0;i<5;i++)
		{
			holdingCards.add(this.gain_boardcard(greencard,browncard));
		}
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
}
