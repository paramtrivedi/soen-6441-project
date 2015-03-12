package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import discworld.BoardCard;
import discworld.Player;
import discworld.SaveGame;
import discworld.CityCard;

public class SaveGameTest {
	Vector<BoardCard> brownCard;
	Vector<BoardCard> greenCard;
	Vector<CityCard> cityCard;
	ArrayList<Player> gamer = new ArrayList<Player>();
	
	@Before
	public void initialize(){
	greenCard = new Vector<BoardCard>(48);
	brownCard = new Vector<BoardCard>(53);
	cityCard = new Vector<CityCard>(12);
	}
	
	@Test
	public void save()
	{
		
	}
	
	@Test
	

}
