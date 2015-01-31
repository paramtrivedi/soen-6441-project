
import java.util.Random;

public class dice
{
	public int roll()
	{
		Random ran = new Random();
		
		int number = 0;
		
		for(int counter = 1; counter <= 1; counter++)
		{
			number= 1 + ran.nextInt(12);
		}
		return number;
	}
}
 