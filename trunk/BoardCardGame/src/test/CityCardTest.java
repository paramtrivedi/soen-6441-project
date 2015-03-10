package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import discworld.BoardCard;
import discworld.CityCard;

public class CityCardTest {
	private CityCard c;
	@Before
	public void initialize(){
		c=new CityCard(2,"SSS",new byte[]{2,3,12});
	}
	
	@Test
	public void ConstructorTest()
	{
		assertEquals(2, c.getId());
		assertTrue(c.Name().equals("SSS"));
		assertEquals(3, c.getNearestCity().length);	
	}
	
    @Test
    public void putMinion()
    {
    
    }
    
    @Test
    public void removeMinion()
    {
    	
    }
    
    @Test
    public void build()
    {
    	
    }
    
    @Test
    public void destroy()
    {
    	
    }
    
    @Test
    public void adjacentCheck()
    {
    	
    }
    
    
}
