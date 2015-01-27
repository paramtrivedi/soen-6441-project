import java.util.Random;
import java.util.Vector;


public class Player {
	/*******************************************************************
	 * Attributes
	 *******************************************************************/
	protected int money;
	//protected String color;
	protected int minion;
	protected int personalityCard;
	
	public Player(){
		money=50;
		//invoke the function to choose a color
		minion=3;
		//invoke the function to choose a Personality Card
	}
	public int gain_boardcard(Vector<Integer> g, Vector<Integer>b){
		//Vector<Integer> green=g;
		//Vector<Integer> brown=b;
		Random rn=new Random();
		int total;
		int randomNum;
		int cardNum;
		if(!g.isEmpty())
		{
			total=g.size();
			randomNum=rn.nextInt(total);
			cardNum=g.get(randomNum);
			g.remove(randomNum);
			return cardNum;
			
		} else if(!b.isEmpty())
		{
			total=b.size();
			randomNum=48+rn.nextInt(total);
			cardNum=b.get(randomNum);
			b.remove(randomNum);
			return cardNum;
		}
		else return -1;
	}
}
