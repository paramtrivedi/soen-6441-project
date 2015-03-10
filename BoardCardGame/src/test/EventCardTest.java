package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import discworld.EventCard;

public class EventCardTest {
	private EventCard e;
	@Before
	public void initialize(){
		 e=new EventCard("The Dragon");
	}
	@Test
	public void ConstructorTest()
	{
		assertTrue(e.equals("The Dragon"));
	}
}
