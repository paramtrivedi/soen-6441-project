package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import discworld.BoardCard;
public class BoardCardTest {
	private BoardCard b;
	//private CityCard c;
	@Before
	public void initialize(){
		b=new BoardCard(2,"SSS","MR",3,null);
	}
	@Test
	public void ConstructorTest()
	{
		assertEquals(2, b.Id());
		assertTrue(b.Name().equals("SSS"));
		assertEquals(BoardCard.Symbols.Minion,b.allSymbols().get(1));
		assertEquals(3, b.dollar());
	}
	@Test
	public void AssassinationTest()
	{
		
	}
}
