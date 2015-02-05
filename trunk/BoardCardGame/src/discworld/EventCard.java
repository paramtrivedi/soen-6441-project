package discworld;

/**
 * This class provides information of event cards.
 * 
 * @author Zixi Quan
 * @version 1.00, 1 February 2015
 *
 */
public class EventCard {
	protected String name;
	
	public EventCard( String name){
		this.name=name;
	}
	
	public String toString(){
		return name;
	}
}
