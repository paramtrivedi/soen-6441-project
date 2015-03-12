package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Vector;

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
	Vector<CityCard> cityCard;
	
	@Before
	public void initialize(){
		ArrayList<BoardCard> holdingCards = new ArrayList<BoardCard>();
		c=new CityCard(2,"The Hippo",new byte[]{2,3,12},12);
		p = new PersonalityCard(2,"Lord Rust");
		player = new Player(2,200,CardColor.blue,2,3,p,holdingCards);
		cityCard = new Vector<CityCard>(12);
	}
	
	@Test
	public void ConstructorTest()
	{
		assertEquals(2, c.getId());
		assertTrue(c.Name().equals("The Hippo"));
		assertEquals(3, c.getNearestCity().length);	
		assertEquals(3, c.getNearestCity()[1]);	
	}
	
	/**
	 * This method checks whether the Player put the minions at a specific area or not.
	 */
    @Test
    public void putMinionTest()
    {
		boolean res = c.putMinion(player);
		assertTrue(res);
    }
    
    /**
     * This method checks whether the Player remove the minions from a specific area or not.
     */
    @Test
    public void removeMinionTest()
    {
    	c.putMinion(player);
    	boolean res = c.removeMinion(player);
		assertTrue(res);
    }
    
    /**
     * This method checks whether the building needs to build or change the owner of the building successfully or not.
     */
    @Test
    public void buildTest()
    {
    	boolean res = c.build(player);
    	assertTrue(res);
    }
    
    /**
     * This method checks whether the building needs to destroy or change the owner of the building successfully or not.
     */
    @Test
    public void destroyTest1()
    {
    	boolean res = c.destroy();
    	assertFalse(res);
    }
      
      
    /**
     * This method checks whether the Player put the demons to a specific area or not.
     */
    @Test
    public void putDemonTest()
    {
    	boolean res = c.putDemon();
    	assertTrue(res);
    }
    
    /**
     * This method checks whether the Player remove the demons from a specific area or not.
     */
    @Test
    public void removeDemonTest1()
    {
    	boolean res = c.removeDemon();
    	assertFalse(res);
    }
    @Test
    public void removeDemonTest2()
    {
    	c.putDemon();
    	assertEquals(1,c.getDemons());
    	boolean res = c.removeDemon();
    	assertTrue(res);
    }
    
    /**
     * This method checks whether the Player put the trolls from a specific area or not
     */
    @Test
    public void putTrollTest()
    {
    	boolean res = c.putTrolls();
    	assertTrue(res);
    }
    
    /**
     * This method checks whether the Player remove the trolls from a specific area or not.
     */
    @Test
    public void removeTrollTest1()
    {
    	boolean res = c.removeTrolls();
    	assertFalse(res);
    }
    @Test
    public void removeTrollTest2()
    {
    	c.putTrolls();
    	boolean res = c.removeTrolls();
    	assertTrue(res);
    }
}