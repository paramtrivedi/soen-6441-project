import java.util.Vector;


public class boardCard {
	protected String name;
	protected int id;
	//protected boolean type;
	protected Vector<Boolean> ablity=new Vector<Boolean>(7);
	protected String des;
	
	
	public boardCard(int id, String name, int num_ablity,String des){
		this.id=id;
		this.name=name;
		/*if(id<=48)
			type=true;
		else 
			type=false;
		*/
		for(int i=0;i<7;i++)
		{
			if (num_ablity%2==1)
				this.ablity.add(true);
			else
				this.ablity.add(false);
			num_ablity/=2;
		}
		this.des=des;
	}
	
	public boolean action_Card_Assassination(){
		return ablity.get(0);
	}
	public boolean action_Card_Remove_Trouble_Marker(){
		return ablity.get(1);
	}
	public boolean action_Card_Money(){
		return ablity.get(2);
	}
	public boolean action_Card_Scroll(){
		return ablity.get(3);
	}
	public boolean action_Card_Event(){
		return ablity.get(4);
	}
	public boolean action_Card_Play_Another_Card(){
		return ablity.get(5);
	}
	public boolean action_Card_Interrupt(){
		return ablity.get(6);
	}
	public String get_Description(){
		return des;
	}
	public String get_Name(){
		return name;
	}
	public int get_Id(){
		return id;
	}
	
}
