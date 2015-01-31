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
			this.minions.set(p.id, minions.get(p.id)+1);
			return true;
		//}
	}
	public boolean removeMinion(Player p){
		int n=minions.get(p.id);
		if(n>0)
		{
			this.minions.set(p.id, minions.get(p.id)-1);
			return true;
		}else return false;
	}
	public boolean putTM(){
		if(troubleMaker)
			return false;
		else{
			troubleMaker=true;
			return true;
		}
	}
	public boolean removeTM(){
		if(!troubleMaker)
			return false;
		else{
			troubleMaker=false;
			return true;
		}
	}
	public boolean build(Player p){
		if(!building){
			building=true;
			owner=p.id;
			return true;
		}
		else
			return false;
	}
	public boolean destory(){
		if(building){
			building=false;
			owner=-1;
			return true;
		}
		else
			return false;
	}
	public boolean putDemon(){
		demons++;
		return true;
	}
	public boolean removeDemon(){
		if(demons>0){
			demons--;
			return true;
		}else return false;
	}
	public boolean putTrolls(){
		trolls++;
		return true;
	}
	public boolean removeTrolls(){
		if(trolls>0){
			trolls--;
			return true;
		}else return false;
	}
	public String toString(){
		String s="";
		s=s+name+" "+owner+" ";
		for(int i=0;i<4;i++)
			s=s+minions.get(i)+" ";
		s=s+troubleMaker+" "+building+" "+demons+" "+trolls;
		return s;
	}
	
}
