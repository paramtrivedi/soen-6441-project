import java.util.Random;
import java.util.Set;


public class Player {
	protected int money;
	protected String color;
	protected int minion;
	protected int personalityCard;
	
	public int gain_card(Set<Integer> g, Set<Integer>b){
		Set<Integer> green=g;
		Set<Integer> brown=b;
		Random rn=new Random();
		if(!green.isEmpty())
		{
			int total=green.size();
			int randomNum=rn.nextInt()%total;
					
		}
		return 1;
	}
}
