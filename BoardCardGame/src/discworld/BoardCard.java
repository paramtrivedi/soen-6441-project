package discworld;
import java.util.ArrayList;


/**
 * 
 * This class generates board cards.
 * 
 * @author Shu Liu and Zixi Quan
 * @version 2.00, 12 March 2015
 *
 */
public class BoardCard {
	protected String name;
	protected int id;
	public static enum Symbols{Minion,Building,Assassination,RemoveTroubleMaker,Dollar,Scroll,Event,PlayCard,Interrupt};
	protected ArrayList<Symbols> symbol=new ArrayList<Symbols>();
	protected String des;
	protected int dollar;
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
	public BoardCard(int id, String name, String s_Symbols,int dollar,String des){
		this.id=id;
		this.name=name;
		
		for(int i=0;i<s_Symbols.length();i++)
		{
			char s=s_Symbols.toUpperCase().charAt(i);
			switch(s){
			case 'M':	symbol.add(Symbols.Minion);break;
			case 'B':	symbol.add(Symbols.Building);break;
			case 'A':	symbol.add(Symbols.Assassination);break;
			case 'R':	symbol.add(Symbols.RemoveTroubleMaker);break;
			case 'E':	symbol.add(Symbols.Event);break;
			case 'S':	symbol.add(Symbols.Scroll);break;
			case 'P':	symbol.add(Symbols.PlayCard);break;
			case 'I':	symbol.add(Symbols.Interrupt);break;
			case 'D':	symbol.add(Symbols.Dollar);break;		
			}		
			
		}
		this.dollar=dollar;
		this.des=des;
	}
	/**
	 * Constructs an instance of boardCard class with the given parameters.
	 * 
	 * This constructor will create a particular board card with the name,
	 * the id, the sym and the description.
	 * 
	 * @param name
	 * @param id
	 * @param sym
	 * @param des
	 */
	
	public BoardCard(String name, int id,  ArrayList<Symbols> sym, String des,
			int dollar) {
		super();
		this.name = name;
		this.id = id;
		this.symbol = sym;
		this.des = des;
		this.dollar = dollar;
	}

	/*******************************************************************
	 * Getters 
	 *******************************************************************/
	/** 
	 * This method will assassinate a minion of another player
	 * @author zixi quan
	 * @param cityCard
	 * @param p 
	 * */
	public boolean Assassination(CityCard cityCard, Player p){
		if(cityCard.containTroubleMaker())
		{
			int numPlayer = p.getID();
			if(cityCard.getMinions().get(numPlayer-1)>0)
				cityCard.removeMinion(p);
			return true;
		}else {
			System.out.println(cityCard.getName()+" does not have any trouble maker.");
			return false;
		}
		
	}
	
	public int dollar(){
		if(symbol.contains(Symbols.Dollar))
		return dollar;
		else return 0;
	}
	public boolean Scroll(){
		return symbol.contains(Symbols.Scroll);
	}
	public boolean Event(){
		return symbol.contains(Symbols.Event);
	}
	public boolean Play_Another_Card(){
		
		return symbol.contains(Symbols.PlayCard);
	}
	public boolean Interrupt(){
		return symbol.contains(Symbols.Interrupt);
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
	public ArrayList<Symbols> allSymbols(){
		return symbol;
	}
	public void setSymbol(ArrayList<Symbols> sym){
		this.symbol=sym;
	}
}
