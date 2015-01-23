import java.util.Random;
import java.util.Vector;


public class Player {
	protected int money;
	protected String color;
	protected int minion;
	protected int personalityCard;
	
	public int gain_card(Vector<Integer> g, Vector<Integer>b){
		Vector<Integer> green=g;
		Vector<Integer> brown=b;
		Random rn=new Random();
		int total;
		int randomNum;
		if(!green.isEmpty())
		{
			total=green.size();
			randomNum=rn.nextInt()%total;
			return green.get(randomNum);
			
		} else if(!brown.isEmpty())
		{
			total=brown.size();
			randomNum=rn.nextInt()%total;
			return brown.get(randomNum);
		}
		else return -1;
	}
}
