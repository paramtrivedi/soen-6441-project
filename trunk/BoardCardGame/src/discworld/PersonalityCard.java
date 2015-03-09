package discworld;
/**
 * 
 * This class provides the name of personality cards for output.
 * 
 * @author Zixi Quan
 * @version 1.00, 1 February 2015
 *
 */
public class PersonalityCard {
	protected int id;
	protected String name;
	public PersonalityCard(int id, String name){
		this.id=id;
		this.name=name;
	}
	
	public int ID(){
		return id;
	}
	public String toString(){
		return name;
	}
	
	
}
