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

public class PlayerTest {
	private Player player;
	private PersonalityCard p;
	private CityCard c;
	
	Vector<PersonalityCard>personalitycards = new Vector<PersonalityCard>(7);
	@Before
	public void initialize(){
		c = new CityCard(1, "Dolly Sister",new byte[]{2,3,12},6);
		String[] cards = {"Lord Selachii", "Lord Rust", "Lord de Worde", "Lord Vetinari", "Commander Vimes", "Dragon King of Arms", "Chrysopsase"};
		p = new PersonalityCard(2,"MARS");
		ArrayList<BoardCard> holdingCards = new ArrayList<BoardCard>();
		player=new Player(2,200,CardColor.blue,2,3,p,holdingCards);
		for (int i=0; i<7; i++){
			String dummy = cards[i];
			PersonalityCard temp = new PersonalityCard (1,dummy);
			personalitycards.add(temp);
		}
	}
	
	@Test
	public void ConstructorTest()
	{
		assertEquals(2, player.getID());
		assertEquals(200, player.getMoney());
		assertEquals(CardColor.blue,player.getColor());
		assertEquals(2, player.getMinion());
		assertEquals(3, player.getBuilding());
		assertEquals(p,player.getPersonalityCard());
		assertEquals(5,player.getHoldingCards().size());
	}
	
	@Test
	public void gain_boardcard()
	{
		
	}
	
	@Test
	public void gain_personalityCard()
	{
		//boolean res = player.gain_personalityCard(personalitycards);
	}
	
	@Test
	public void refill(Vector<BoardCard> g,Vector<BoardCard> b)
	{
		
	}
	
	@Test
	public void putBuilding()
	{
		boolean res = c.build(player);
		assertTrue(res);
	}

}
