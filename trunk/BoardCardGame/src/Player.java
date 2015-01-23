import java.util.Random;
import java.util.Vector;


public class Player {
	protected int money;
	protected String color;
	protected int minion;
	protected int personalityCard;
	
	public int gain_boardcard(Vector<Integer> g, Vector<Integer>b){
		Vector<Integer> green=g;
		Vector<Integer> brown=b;
		Random rn=new Random();
		int total;
		int randomNum;
		int cardNum;
		if(!green.isEmpty())
		{
			total=green.size();
			randomNum=rn.nextInt()%total;
			cardNum=green.get(randomNum);
			green.remove(randomNum);
			return cardNum;
			
		} else if(!brown.isEmpty())
		{
			total=brown.size();
			randomNum=rn.nextInt()%total;
			cardNum=brown.get(randomNum);
			brown.remove(randomNum);
			return cardNum;
		}
		else return -1;
	}
}
