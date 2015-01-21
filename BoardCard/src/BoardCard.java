
public class BoardCard {
	protected String name;
	protected int id;
	protected int type;
	
	public BoardCard(int id, String name){
		this.id=id;
		this.name=name;
		if(id<=48)
		{
			this.type=1;
		}
		else
			this.type=2;
		
	}
}
