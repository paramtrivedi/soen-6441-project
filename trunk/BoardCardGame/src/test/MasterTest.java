package test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import discworld.BoardCard;
import discworld.CardColor;
import discworld.Player;
import discworld.Master;
import discworld.PersonalityCard;


public class MasterTest {
	private Master m;
	private Player player;
	private PersonalityCard p;
	ArrayList<BoardCard> holdingCards = new ArrayList<BoardCard>();
	@Before
	public void initialize(){
		m=new Master();
		new Player(2,200,CardColor.Blue,2,3,p,holdingCards);
		player = new Player(2,200,CardColor.Blue,2,3,p,holdingCards);
	}
	
	@Test
	public void ConstructorTest()
	{
		
	}
	
	/*@Test
	public void newGame ()
	{
		
	}
	
	@Test
	public void ChooseCity()
	{
		
	}
	
	@Test
	public void playGames()
	{
		
	}*/
	
	@Test
	public void roll()
	{
		int result = m.roll();
		boolean res;
		if(result>0){
			res = true;
		} else{
			res = false;
		}
		assertTrue(res);
	}
	
	@Test
	public void winCheck()
	{
		boolean res = m.winCheck(player);
		assertTrue(res);
	}
}
