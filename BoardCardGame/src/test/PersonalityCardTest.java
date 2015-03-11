package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import discworld.PersonalityCard;

public class PersonalityCardTest {

	private PersonalityCard p;
	@Before
	public void initialize(){
		p=new PersonalityCard(2,"SSS");
	}
	@Test
	public void ConstructorTest()
	{
		assertEquals(2, p.ID());
		assertTrue(p.equals("SSS"));
	}
	
}
