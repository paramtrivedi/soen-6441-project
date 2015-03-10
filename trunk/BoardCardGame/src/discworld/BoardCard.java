package discworld;
import java.util.Vector;

/**
 * 
 * This class generates board cards.
 * 
 * @author Shu Liu and Zixi Quan
 * @version 1.00, 1 February 2015
 *
 */
public class BoardCard {
	protected String name;
	protected int id;
	protected Vector<Boolean> ability=new Vector<Boolean>(7);
	protected String des;
	protected int money;
	/**
	 * Constructs an instance of boardCard class with the given parameters.
	 * 
	 * This constructor will create a particular board card with the id,
	 * the name, the ability and the description.
	 * 
	 * @param id
	 * @param name
	 * @param num_ablity
	 * @param des
	 */
	public BoardCard(int id, String name, int num_ablity,int money,String des){
		this.id=id;
		this.name=name;
		
		for(int i=0;i<7;i++)
		{
			if (num_ablity%2==1)
				this.ability.add(true);
			else
				this.ability.add(false);
			num_ablity/=2;
		}
		this.money=money;
		this.des=des;
	}
	/*******************************************************************
	 * Getters 
	 *******************************************************************/
	
	public boolean Assassination(CityCard cityCard, Player p){
		if(cityCard.isTroubleMaker())
		{
			int numPlayer = p.getID();
			if(cityCard.getMinions().get(numPlayer-1)>0)
				cityCard.removeMinion(p);
		}
		return ability.get(0);
	}
	public boolean Remove_Trouble_Marker(){
		return ability.get(1);
	}
	public int Money(){
		
		return money;
	}
	public boolean Scroll(){
		return ability.get(3);
	}
	public boolean Event(){
		return ability.get(4);
	}
	public boolean Play_Another_Card(){
		
		return ability.get(5);
	}
	public boolean Interrupt(){
		return ability.get(6);
	}
	/**
	 * Gets the description in the board card.
	 * 
	 * @return description
	 */
	public String Description(){
		return des;
	}
	/**
	 * Gets the name in the board card.
	 * 
	 * @return name
	 */
	public String Name(){
		return name;
	}
	/**
	 * Gets the id in the board card.
	 * 
	 * @return id
	 */
	public int Id(){
		return id;
	}
	
	
}
