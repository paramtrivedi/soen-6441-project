package discworld;
import java.util.Random;

/**
 * This class rolls the dice.
 * 
 * @author Jay Tanna
 * @version 1.00, 1 February 2015
 *
 */
public class dice
{
	/**
	 * The method rolls to get the random number of the dice.
	 * 
	 * @return integer
	 */
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
