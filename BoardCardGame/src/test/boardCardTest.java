
package test;
import static org.junit.Assert.*;
import discworld.*;
import org.junit.*;

public class boardCardTest  {
	@Test
	public void ConstructorTest()
	{
		boardCard bc=new boardCard(1, "TestCase", 8,null);
	}
	@Test
	public void testMethod(){
		
		assertEquals(1,1);
	}

	
}
