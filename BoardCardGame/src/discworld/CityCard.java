package discworld;

import java.util.Vector;


/**
 * This class generates the city cards and owner of the city areas
 * 
 * @author Shu Liu, Mohit Pujara
 * @version 2.00, 12 March 2015
 *
 */
public class CityCard 
{	
	protected int benefit;	
	protected int id;		
	protected String name;	
	protected int owner;			
	protected Vector<Integer> minions=new Vector<Integer>(4);	
	protected boolean troubleMaker; 	
	protected boolean building;		
	protected int demons;			
	protected int trolls;			
	protected byte [] nearestCity;

	/**
	 * Constructs an instance of CityCard class with the given parameters.
	 * 
	 * This constructor will create a particular CityCard with the id, the name and the nearest cities to it.
	 * @param id
	 * @param name
	 * @param nearestCity
	 * @param benefit
	 */
	public CityCard(int id, String name, byte [] nearestCity,int benefit){
		this.id=id;
		this.name=name;
		this.nearestCity=nearestCity;
		getMinions().add(0);
		getMinions().add(0);
		getMinions().add(0);
		getMinions().add(0);
		setOwner(-1);
		setTroubleMaker(false);
		setBuilding(false);
		demons=0;
		trolls=0;
		this.benefit=benefit;
	}

	/**
	 * Constructs an instance of CityCard class with the given parameters.
	 * 
	 * This constructor will create a particular CityCard with id, name.
	 * the owner, number of minions for each player, existence of trouble maker and building,
	 * number of demons and trolls 
	 * @param id 	The id of the city
	 * @param name	The name of the city
	 * @param owner	The owner of the city
	 * @param minions	Vector of minions records the minions for each player
	 * @param troublemaker	Contains trouble maker or not
	 * @param building	The city has build or not
	 * @param demons	The city has demons or not
	 * @param trolls	The city has trolls or not
	 * @param nearestCity Adjacent cities to the given city
	 * @param benefit	The benefit a player can have by having a building at that city
	 */

	public CityCard(int id, String name, int owner,Vector<Integer> minions, boolean troublemaker, boolean building,int demons,int trolls,byte [] nearestCity, int benefit){
		this.id=id;
		this.name=name;
		this.setOwner(owner);
		this.setTroubleMaker(troublemaker);
		this.setBuilding(building);
		this.demons=demons;
		this.trolls=trolls;
		this.nearestCity = nearestCity;
		this.benefit = benefit;
		this.minions=minions;
	}

	/**
	 * 
	 * The method put the minions in the game.
	 * If the number of minions or demons or trolls in city area is more than or equal to one,
	 * then put a minion in this area.
	 * 
	 * @param p	The player whose minions is put in the city.
	 * @return If the player put a minion then return true, otherwise return false.
	 */
	public boolean putMinion(Player p){
		
		System.out.println("Player "+p.getID()+" added one minion in "+name);
		p.setMinion(-1);
		this.getMinions().set(p.getID()-1, getMinions().get(p.getID()-1)+1);
		int tempSumMin=0;
		for(int i=0;i<4;i++)
		{
			tempSumMin+=getMinions().get(i);
		}
		if(tempSumMin>1 || this.demons>=1||this.trolls>=1 )
			putTM();
		return true;
	}

	/**
	 * This method is used for removing one minion for a player.
	 * If the number of minions is more than or equal to one, then remove a minion from the area;
	 * otherwise output there is no minion in this area.
	 * 
	 * @param p The player whose minions is removed
	 * @return  If the player' minions is removed then return true, otherwise return false.
	 */
	public boolean removeMinion(Player p){
		int n=getMinions().get(p.getID()-1);
		if(n>0)
		{
			System.out.println("One minion of Player "+p.getID()+" removed from "+name);
			this.getMinions().set(p.getID()-1, getMinions().get(p.getID()-1)-1);
			p.setMinion(+1);
			removeTM();
			return true;
		}else {
			System.out.println("Player "+p.getID()+" does not have any minions in "+name);
			
			return false;
		}
	}

	/**
	 * If there is no trouble maker in the city, then put one. 
	 * Otherwise, output trouble maker exists.
	 * 
	 * @return If trouble maker is put, then return true, otherwise return false.
	 */
	public boolean putTM(){
		if(containTroubleMaker()){
			//System.out.println("One trouble maker already exists in "+name);
			return false;
		}
		else{
			System.out.println("One trouble maker come to "+name);
			setTroubleMaker(true);
			return true;
		}
	}

	/**
	 * If there is a trouble maker in the city, then remove it;
	 * otherwise, output trouble maker does not exist. 
	 * 
	 * @return If trouble maker is removed, then return true, otherwise return false.
	 */
	public boolean removeTM(){
		if(!containTroubleMaker()){
			System.out.println("No trouble maker exists in "+name);
			return false;
		}
		else{
			System.out.println("One trouble maker is removed from "+name);
			setTroubleMaker(false);
			return true;
		}
	}

	/**
	 * If there is no building in the city, 
	 * then build one for the player and change the owner for the city. 
	 * Otherwise, output building exists. 
	 * 
	 * @param p The player who want to build a building.
	 * @return If building is built, then return true, otherwise return false.
	 */
	public boolean build(Player p){
		if(!isBuilding()&&!containTroubleMaker()&&this.minionNum(p)>0&&p.getMoney()>=benefit){
			System.out.println("One building is built in "+name);
			p.setBuilding(-1);
			setBuilding(true);
			setOwner(p.getID());
			p.setMoney(p.getMoney()-this.benefit);
			Master.bank+=benefit;
			return true;
		}
		else{
			if(containTroubleMaker())
				System.out.println("This city has a trouble make.");
			else if(this.minionNum(p)==0)
				System.out.println("Player "+p.getID()+", you dont have any minion in "+this.name);
				
			else if(benefit>p.getMoney())
				System.out.println("You do not have enough money.");
			else System.out.println("There is one building existing in "+name);
			return false;
		}
	}
	/**
	 * If there is a building in the city, 
	 * then destroy one for the player and change the owner for the city. 
	 * Otherwise, output building does not exists. 
	 * 
	 * @return If one building is destroyed, then return true.
	 */
	public boolean destroy(){
		if(isBuilding()){
			System.out.println("Player "+getOwner()+"'s building is destoried in "+name);
			setBuilding(false);
			Master.playerList.get(getOwner()-1).setBuilding(+1);
			setOwner(-1);
			return true;
		}
		else{
			System.out.println("No bulding can be destoried in "+name);
			return false;
		}

	}

	/**
	 * The method add demons in a specific city area.
	 * If the number of minions or demons or trolls in city area is more than or equal to one,
	 * then put a demon in this area.
	 * 
	 * @return If the player put the demon in this area, then return true; otherwise return false.
	 */
	public boolean putDemon(){
		demons++;
		System.out.println("One demon comes to "+name);
		int tempSumMin=0;
		for(int i=0;i<4;i++)
		{
			tempSumMin+=getMinions().get(i);
		}
		if(tempSumMin>1 || this.demons>=1||this.trolls>=1 )
			putTM();
		return true;
	}

	/**
	 * The method removes demons in a specific city area.
	 * If there is a demon in the city area, then remove a demon and a trouble maker;
	 * otherwise, output demon does not exist. 
	 * 
	 * @return If the player remove the demon in this area, then return true; otherwise return false.
	 */
	public boolean removeDemon(){
		if(demons>0){
			demons--;
			System.out.println("One demon is beaten in "+name);
			removeTM();
			return true;
		}else {
			System.out.println("No demon can be remove from "+name);
			return false;
		}
	}

	/** 
	 * This method add trolls in the city.
	 * If the number of minions or demons or trolls in city area is more than or equal to one,
	 * then put a troll in this area.
	 * 
	 *  @return If the player put a troll in this area, then return true; otherwise return false.
	 */
	public boolean putTrolls(){
		trolls++;
		System.out.println("One troll is added to "+name);
		int tempSumMin=0;
		for(int i=0;i<4;i++)
		{
			tempSumMin+=getMinions().get(i);
		}
		if(tempSumMin>1 || this.demons>=1||this.trolls>=1 )
			putTM();
		return true;
	}

	/**
	 * The method removes the trolls in a specific city area.
	 * If there is a troll in the city area, then remove a troll;
	 * otherwise, output troll does not exist.
	 * 
	 * @return If the player remove a troll in this area, then return true; otherwise return false.
	 */
	public boolean removeTrolls(){
		if(trolls>0){
			trolls--;
			System.out.println("One troll is remove from "+name);
			removeTM();
			return true;
		} else {
			System.out.println("No troll can be remove from "+name);
			return false;
		}
	}

	/**
	 * Checking the adjacent city that has minion or not
	 * @param CityCards The city which is adjacent needs to check.
	 * @param player The player who plays the game
	 * 
	 * @return true or false
	 */

	public boolean adjacentCheck(Vector<CityCard> CityCards, Player p){
		boolean flag = false;
		if(this.getMinions().get(p.getID()-1) >= 1)
		{
			for(int i=0; i < this.nearestCity.length; i++)
			{
				int values = CityCards.get(nearestCity[i]-1).getMinions().get(p.getID()-1);
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
	/** Convert all the information of a specific city card to string */
	public String toString(){
		String s="";
		s=s+String.format("%20s", name)+"\t";
		if(getOwner()==-1)
			s=s+"None\t";
		else
			s=s+getOwner()+"\t";
		for(int i=0;i<4;i++)
			s=s+getMinions().get(i)+"\t";
		s=s+String.format("%10s", containTroubleMaker())+"\t"+isBuilding()+"\t";
		s=s+String.format("%10s", demons)+"\t"+trolls;
		return s;
	}

	/**
	 * Get the name.
	 * @return the name of the city
	 */
	public String Name()
	{
		return name;
	}
	/**
	 * Get the number of minions for a player.
	 * @param p
	 */
	
	public int minionNum(Player p)
	{
		return getMinions().get(p.getID()-1);
		
	}
	/**
	 * It gives the number of pieces by checking id and building for a player.
	 * @param p
	 * @return the number of minions
	 */
	public int pieces(Player p){
		if(isBuilding() && getOwner()==p.getID())
			return minionNum(p)+1;
		else return minionNum(p);
	}
	
	/**Check whether there is a trouble marker in a city or not .*/

	public boolean containTroubleMaker()
	{
		
		return this.troubleMaker;
	}

	

	public void setTroubleMaker(boolean troubleMaker) {
		this.troubleMaker = troubleMaker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDemons() {
		return demons;
	}

	
	public int getTrolls() {
		return trolls;
	}

	public void setTrolls(int trolls) {
		this.trolls = trolls;
	}

	public byte[] getNearestCity() {
		return nearestCity;
	}

	public String getNearestCitys()
	{
		String temp="";
		for(int i=0;i<this.nearestCity.length;i++)
			temp+=this.nearestCity[i]+",";
		
		temp = temp.substring(0, temp.length()-1);
		return temp;
	}
	
	public void setNearestCity(byte[] nearestCity) {
		this.nearestCity = nearestCity;
	}

	public Vector<Integer> getMinions() {
		return minions;
	}
	
	public int getId() {
		return id;
	}

	public boolean isBuilding() {
		return building;
	}

	public void setBuilding(boolean building) {
		this.building = building;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}
	public int getBenefit(){
		return benefit;
	}
	
	public void removeAllMinion() {
		Vector<Integer> minions=new Vector<Integer>(4);
		minions.add(0);
		minions.add(0);
		minions.add(0);
		minions.add(0);
		this.minions=minions;
	}
}
