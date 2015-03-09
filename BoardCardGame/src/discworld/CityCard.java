package discworld;

import java.util.Vector;


/**
 * This class generates the city cards and owner of the city areas
 * 
 * @author Shu Liu, Mohit Pujara
 * @version 2.00, 12 March 2015
 *
 */
public class CityCard {
	protected int id;			
	protected String name;
	protected int owner;		//No owner -1
	protected Vector<Integer> minions=new Vector<Integer>(4);// number of minions for each players
	protected boolean troubleMaker; 
	protected boolean building;	
	protected int demons;		
	protected int trolls;		
	protected byte [] nearestCity;

	/**
	 * Constructs an instance of CityCard class with the given parameters.
	 * 
	 * This constructor will create a particular CityCard with the id, the name.
	 * 
	 * @param id
	 * @param name
	 * 
	 */
	public CityCard(int id, String name, byte [] nearestCity){
		this.id=id;
		this.name=name;
		this.nearestCity=nearestCity;
		minions.add(0);
		minions.add(0);
		minions.add(0);
		minions.add(0);
		owner=-1;
		troubleMaker=false;
		building=false;
		demons=0;
		trolls=0;
	}

	/**
	 * Constructs an instance of CityCard class with the given parameters.
	 * 
	 * This constructor will create a particular CityCard with id, name.
	 * the owner, number of minions for each player, existence of trouble maker and building,
	 * number of demons and trolls
	 * @param id 	
	 * @param name	The name of the city
	 * @param owner	The owner of the city
	 * @param minions	Vector of minions records the minions for each player
	 * @param troublemaker	Contains trouble maker or not
	 * @param building	The city has build or not
	 * @param demons	The city has demons or not
	 * @param trolls	The city has trolls or not
	 */

	public CityCard(int id, String name, int owner,Vector<Integer> minions, boolean troublemaker, boolean building,int demons,int trolls){
		this.id=id;
		this.name=name;
		this.owner=owner;
		this.troubleMaker=troublemaker;
		this.building=building;
		this.demons=demons;
		this.trolls=trolls;
	}

	/**
	 * 
	 * The method put the minions in the game.
	 * 
	 * @param p	The player whose minions is put in the city.
	 * @return If the player put a minion then return true, otherwise return false.
	 */
	public boolean putMinion(Player p){
		System.out.println("Player "+p.getID()+" added one minion in "+name);
		p.setMinion(-1);
		this.minions.set(p.id-1, minions.get(p.id-1)+1);
		int tempSumMin=0;
		for(int i=0;i<4;i++)
		{
			tempSumMin+=minions.get(i);
		}
		if(tempSumMin>1 )
			putTM();
		return true;
	}

	/**
	 * This method is used for removing one minion for a player.
	 * 
	 * @param p The player whose minions is removed
	 * @return  If the player' minions is removed then return true, otherwise return false.
	 */
	public boolean removeMinion(Player p){
		int n=minions.get(p.id-1);
		if(n>0)
		{
			System.out.println("One minion of Player "+p.getID()+" removed from "+name);
			this.minions.set(p.getID()-1, minions.get(p.getID()-1)-1);
			p.setMinion(+1);
			return true;
		}else {
			System.out.println("Player "+p.getID()+" does not have any minions in "+name);
			removeTM();
			return false;
		}
	}

	/**
	 * 
	 * If there is no trouble maker in the city, then put one. 
	 * Otherwise, output trouble maker exists 
	 * 
	 * @return If trouble maker is put, then return true, otherwise return false.
	 */
	public boolean putTM(){
		if(troubleMaker){
			System.out.println("One trouble maker already exists in "+name);
			return false;
		}
		else{
			System.out.println("One trouble maker come to "+name);
			troubleMaker=true;
			return true;
		}
	}

	/**
	 * 
	 * If there is a trouble maker in the city, then remove is. 
	 * Otherwise, output trouble maker does not exist. 
	 * 
	 * @return If trouble maker is removed, then return true, otherwise return false.
	 */
	public boolean removeTM(){
		if(!troubleMaker){
			System.out.println("No trouble maker exists in "+name);
			return false;
		}
		else{
			System.out.println("One trouble maker is removed from "+name);
			troubleMaker=false;
			return true;
		}
	}

	/**
	 * 
	 * If there is no building in the city, 
	 * then build one for the player and change the owner for the city. 
	 * Otherwise, output building exists. 
	 * 
	 * @param p The player who want to build a building.
	 * @return If building is built, then return true, otherwise return false.
	 * 
	 */
	public boolean build(Player p){
		if(!building){
			System.out.println("One building is built in "+name);
			p.setBuilding(-1);
			building=true;
			owner=p.id;
			return true;
		}
		else{
			System.out.println("There is one building existing in "+name);
			return false;
		}
	}
	/**
	 * 
	 * If there is a building in the city, 
	 * then destroy one for the player and change the owner for the city. 
	 * Otherwise, output building does not exists. 
	 * 
	 * @param player
	 * @return If one building is destroyed, then return true.
	 */
	public boolean destroy(Player player){
		if(building){
			System.out.println("Player "+owner+"'s building is destoried in "+name);
			building=false;
			player.setBuilding(+1);
			owner=-1;
			return true;
		}
		else{
			System.out.println("No bulding can be destoried in "+name);
			return false;
		}

	}

	/**
	 * 
	 * The method add demons.
	 * 
	 * @return true or false
	 */
	public boolean putDemon(){
		demons++;
		System.out.println("One demon comes to "+name);
		return true;
	}

	/**
	 * 
	 * The method removes demons.
	 * 
	 * @return true or false
	 */
	public boolean removeDemon(){
		if(demons>0){
			demons--;
			System.out.println("One demon is beaten in "+name);
			return true;
		}else {
			System.out.println("No demon can be remove from "+name);
			return false;
		}
	}

	/**
	 * 
	 * This method add trolls.
	 * 
	 * @return true or false
	 */
	public boolean putTrolls(){
		trolls++;
		System.out.println("One troll is added to "+name);
		return true;
	}

	/**
	 * 
	 * The method removes the trolls.
	 * 
	 * @return true or false.
	 */
	public boolean removeTrolls(){
		if(trolls>0){
			trolls--;
			System.out.println("One troll is remove from "+name);
			return true;
		} else {
			System.out.println("No troll can be remove from "+name);
			return false;
		}
	}

	/**
	 * Checking the adjacent city that has minion or not
	 * 
	 * @return true or false
	 */

	public boolean adjacentCheck(Vector<CityCard> CityCards, Player p){
		boolean flag = false;
		if(this.minions.get(p.getID()-1) >= 1)
		{
			for(int i=0; i < this.nearestCity.length; i++)
			{
				int values = CityCards.get(nearestCity[i]-1).minions.get(p.id-1);
				if (values >= 1)
				{
					putMinion(p);
					flag = true;
					return flag;
				}
			}
		}
		return flag;
	}
	/**
	 * Convert all the information of a specific city card to string
	 */
	public String toString(){
		String s="";
		s=s+String.format("%20s", name)+"\t";
		if(owner==-1)
			s=s+"None\t";
		else
			s=s+owner+"\t";
		for(int i=0;i<4;i++)
			s=s+minions.get(i)+"\t";
		s=s+String.format("%10s", troubleMaker)+"\t"+building+"\t";
		s=s+String.format("%10s", demons)+"\t"+trolls;
		return s;
	}

	/**
	 * This method gets the number of the demons.
	 * @return The number of demon
	 */
	public int getDemon(){
		return demons;
	}

	/**
	 * Get the name.
	 * @return the name of the city
	 */
	public String Name()
	{
		return name;
	}
}
