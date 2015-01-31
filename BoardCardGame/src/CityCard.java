import java.util.Vector;


public class CityCard {
	protected int id;			
	protected String name;
	protected int owner;		//No owner -1
	protected Vector<Integer> minions=new Vector<Integer>(4);// number of minions for each players
	protected boolean troubleMaker; 
	protected boolean building;	
	protected int demons;		
	protected int trolls;		
	/**
	 * Constructs an instance of CityCard class with the given parameters.
	 * 
	 * This constructor will create a particular CityCard with the id, the name.
	 * 
	 * @param id
	 * @param name
	 * 
	 */
	public CityCard(int id, String name){
		this.id=id;
		this.name=name;
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
	 * @param name
	 * @param owner
	 * @param minions
	 * @param troublemaker
	 * @param building
	 * @param demons
	 * @param trolls
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
	 * Constructs an instance of CityCard class with the given parameters.
	 * 
	 * This constructor will create a particular CityCard with id, name.
	 * the owner, number of minions for each player, existence of trouble maker and building,
	 * number of demons and trolls
	 * @param id
	 * @param name
	 * @param owner
	 * @param minions
	 * @param troublemaker
	 * @param building
	 * @param demons
	 * @param trolls
	 */
	public boolean putMinion(Player p){
		//if (true){
		System.out.println("Player "+p.getID()+" added one minion in "+name);
			this.minions.set(p.id-1, minions.get(p.id-1)+1);
			return true;
		//}
	}
	public boolean removeMinion(Player p){
		int n=minions.get(p.id-1);
		if(n>0)
		{
			System.out.println("One minion of Player "+p.getID()+" removed from "+name);
			this.minions.set(p.id-1, minions.get(p.id-1)-1);
			return true;
		}else {
			System.out.println("Player "+p.getID()+" does not have any minions in "+name);
			return false;
		}
	}
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
	public boolean build(Player p){
		if(!building){
			System.out.println("One building is built in "+name);
			building=true;
			owner=p.id;
			return true;
		}
		else{
			System.out.println("There is one building existing in "+name);
			return false;
		}
	}
	public boolean destory(){
		if(building){
			System.out.println("One building is destoried in "+name);
			building=false;
			owner=-1;
			return true;
		}
		else{
			System.out.println("No bulding can be destoried in "+name);
			return false;
		}
			
	}
	public boolean putDemon(){
		demons++;
		System.out.println("One demon comes to "+name);
		return true;
	}
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
	public boolean putTrolls(){
		trolls++;
		System.out.println("One troll is added to "+name);
		return true;
	}
	public boolean removeTrolls(){
		if(trolls>0){
			trolls--;
			System.out.println("One troll is remove from "+name);
			return true;
		}else {
			System.out.println("No troll can be remove from "+name);
			return false;
		}
	}
	public String toString(){
		String s="";
		s=s+name+"\t"+owner+"\t";
		for(int i=0;i<4;i++)
			s=s+minions.get(i)+"\t";
		s=s+troubleMaker+"\t"+building+"\t"+demons+"\t"+trolls;
		return s;
	}
	public String Name()
	{
		return name;
	}
	
}
