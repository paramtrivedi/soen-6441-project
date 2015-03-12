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
public class BoardCardTest {
	private BoardCard b;
	private CityCard c;
	private Player player;
	private PersonalityCard p;
	@Before
	public void initialize(){
		c=new CityCard(2,"The Hippo",new byte[]{2,3,12},6);
		b=new BoardCard(2,"SSS","MRD",3,null);
		ArrayList<BoardCard> holdingCards = new ArrayList<BoardCard>();
		p = new PersonalityCard(2,"Lord Rust");
		player=new Player(2,200,CardColor.blue,2,3,p,holdingCards);
	}
	
	@Test
	public void ConstructorTest()
	{
		assertEquals(2, b.Id());
		assertTrue(b.Name().equals("SSS"));
		assertEquals(BoardCard.Symbols.Minion,b.allSymbols().get(0));
		assertEquals(3, b.dollar());
	}
	
	/**
	 * This method checks one minion of other players has been assassinated or not.
	 */
	@Test
	public void AssassinationTest1()
	{
		boolean res = b.Assassination(c, player);
		assertFalse(res);
	}
	@Test
	public void AssassinationTest2()
	{
		c.putMinion(player);
		boolean res = b.Assassination(c, player);
		assertTrue(res);
	}
}
