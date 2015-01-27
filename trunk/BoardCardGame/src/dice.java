
import java.util.Random;

public class dice
{
	void roll()
	{
		Random ran = new Random();
		
		int number;
		
		for(int counter = 1; counter <= 1; counter++)
		{
			number= 1 + ran.nextInt(12);
			System.out.println(number);
		}
	}
	
	
	
	
	
		
			
	
		
public static void main(String args[])
{
	dice d = new dice();
	d.roll();
}
}
 