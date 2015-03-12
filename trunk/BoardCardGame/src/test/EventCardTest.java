package test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import discworld.BoardCard;
import discworld.CardColor;
import discworld.EventCard;
import discworld.PersonalityCard;
import discworld.Player;

public class EventCardTest {
	private EventCard e;
	private Player player; 
	private PersonalityCard p;
	
	@Before
	public void initialize(){
		 e=new EventCard(2,"The Dragon");
		 p = new PersonalityCard(2,"Lord Rust");
		 ArrayList<BoardCard> holdingCards = new ArrayList<BoardCard>();
		 player=new Player(2,200,CardColor.blue,2,3,p,holdingCards);
	}
	
	@Test
	public void ConstructorTest()
	{
		//assertEquals(2,);
		assertTrue(e.equals("The Dragon"));
	}
	
	/**
	 * 
	 */
	@Test
	public void actionTest()
	{
		boolean res = e.action(player);
		assertTrue(res);
	}
}
