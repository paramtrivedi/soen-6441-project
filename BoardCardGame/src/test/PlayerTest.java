package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import discworld.BoardCard;
import discworld.CityCard;
import discworld.PersonalityCard;
import discworld.Player;

public class PlayerTest {
	private Player p;
	//private CityCard c;
	@Before
	public void initialize(){
		p=new Player("player1-blue",);
	}
	
	@Test
	public void ConstructorTest()
	{
	
	}
	
	@Test
	public void gain_boardcard()
	{
		
	}
	
	@Test
	public PersonalityCard gain_personalityCard()
	{
		
	}
	
	@Test
	public void refill(Vector<BoardCard> g,Vector<BoardCard> b)
	{
		
	}
	
	//@Test
	//public boolean putBuilding(CityCard city,ArrayList<CityCard> cities)
	//{
		
	//}

}
