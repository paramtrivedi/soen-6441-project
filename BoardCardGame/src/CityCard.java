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
	
	public String toString(){
		String s="";
		
		return s;
	}
	
}
