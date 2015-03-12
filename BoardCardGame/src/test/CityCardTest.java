package test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import discworld.BoardCard;
import discworld.CardColor;
import discworld.CityCard;
import discworld.PersonalityCard;
import discworld.Player;

public class CityCardTest {
	private CityCard c;
	private Player player;
	private PersonalityCard p;
	
	@Before
	public void initialize(){
		ArrayList<BoardCard> holdingCards = new ArrayList<BoardCard>();
		c=new CityCard(2,"SSS",new byte[]{2,3,12},12);
		p = new PersonalityCard(2,"MARS");
		player = new Player(2,200,CardColor.blue,2,3,p,holdingCards);
	}
	
	@Test
	public void ConstructorTest()
	{
		assertEquals(2, c.getId());
		assertTrue(c.Name().equals("SSS"));
		assertEquals(3, c.getNearestCity().length);	
		assertEquals(3, c.getNearestCity()[1]);	
	}
	
    @Test
    public void putMinion()
    {
		boolean res = c.putMinion(player);
		assertTrue(res);
    }
    
    @Test
    public void removeMinion()
    {
    	
    }
    
    @Test
    public void build()
    {
    	
    }
    
    @Test
    public void destroy()
    {
    	//c.de
    }
    
    @Test
    public void adjacentCheck()
    {
    	
    }
}
