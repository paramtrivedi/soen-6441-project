import java.util.Vector;


public class CityCard {
	protected int id;
	protected String name;
	protected int owener;
	protected Vector<Integer> minions=new Vector<Integer>(4);
	protected boolean troubleMaker;
	protected int building;
	protected int demons;
	protected int trolls;
	
	public CityCard(int id, String name){
		this.id=id;
		this.name=name;
		owener=-1;
		troubleMaker=false;
		building=-1;
		demons=0;
		trolls=0;
	
	}
	
	public CityCard(int id, String name, int owener,Vector<Integer> minions, boolean tm, int building,int demons,int trolls){
		this.id=id;
		this.name=name;
		this.owener=owener;
		this.troubleMaker=tm;
		this.building=building;
		this.demons=demons;
		this.trolls=trolls;
	}
	
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
		if(building==-1){
			building=p.id;
			return true;
		}
		else
			return false;
	}
	public boolean destory(Player p){
		if(building!=-1){
			building=-1;
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
		s=s+name+" "+owener+" ";
		for(int i=0;i<4;i++)
			s=s+minions.get(i)+" ";
		s=s+troubleMaker+" "+building+" "+demons+" "+trolls;
		return s;
	}
	
}
