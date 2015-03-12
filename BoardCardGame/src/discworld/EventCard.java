package discworld;

/**
 * This class provides information of event cards.
 * 
 * @author Zixi Quan
 * @version 2.00, 12 March 2015
 *
 */
public class EventCard {
	protected int id;
	protected String name;
	
	public EventCard(int id, String name){
		this.id=id;
		this.name=name;
	}
	
	public String toString(){
		return name;
	}
	public boolean action(Player p){
		int die;
		CityCard tempCity;
		switch(this.id)
		{
		case 1:
			die=Master.roll();
			tempCity=Master.cityCards.get(die-1);
			//
			break;
		case 4:
			int size=p.getHoldingCards().size();
			for(int i=0;i<5;i++)
			{
				p.gain_boardcard(Master.greenCard, Master.brownCard);
				System.out.println(p.getHoldingCards().get(size)+"is picked up and throwd.");
				p.getHoldingCards().remove(size);
			}
			
			
		}
		return true;
	}
}
