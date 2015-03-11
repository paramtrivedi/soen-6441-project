package test;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import discworld.Player;
import discworld.Master;


public class MasterTest {
	private Master m;
	private Player player;
	@Before
	public void initialize(){
		m=new Master();
		player = new Player();
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
