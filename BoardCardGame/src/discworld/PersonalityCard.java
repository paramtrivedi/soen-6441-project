package discworld;
/**
 * 
 * This class provides the name of personality cards for output.
 * 
 * @author Zixi Quan
 * @version 3.00, 2 April 2015
 *
 */
public class PersonalityCard {
	/** It gives the id to the personality card. */
	protected int id;
	/** It gives the name to the personality card.	*/
	protected String name;
	/** 
	 * It  construct the personality card with the id and name.
	 * @param id
	 * @param name	
	 */
	public PersonalityCard(int id, String name){
		this.id=id;
		this.name=name;
	}
	/**
	 *  It returns id of the personality card.	
	 *  return id
	 */
	public int ID()
	{
		return id;
	}
	/**
	 *  It converts name to string.	
	 *  return name
	 */
	public String toString()
	{
		return name;
	}
	
	
}
