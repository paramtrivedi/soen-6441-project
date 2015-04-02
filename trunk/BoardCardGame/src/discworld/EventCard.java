package discworld;

import java.util.ArrayList;

/**
 * This class provides information of event cards.
 * 
 * @author Zixi Quan
 * @version 3.00, 2 April 2015
 *
 */
public class EventCard {
	protected int id;
	protected String name;
	
	public EventCard(int id, String name){
		this.id=id;
		this.name=name;
	}
	/**
	 * This method gets the id of current event card.
	 * @return number of id
	 */
	public int getId()
	{
	    return id;
	}
	
	public String toString(){
		return name;
	}
	/**
	 * This method plays the function of the event card.
	 * 
	 * @param p The current player who plays the card.
	 * @return If the event card works well, then return true; otherwise return false.
	 */
	public boolean action(Player p){
		int die;
		CityCard tempCity;
		System.out.println(" A random event has occurred: "+this.name);
		switch(this.id)
		{
		case 1:
			die=Master.roll();
			tempCity=Master.cityCards.get(die-1);
			System.out .println("Die rolls: "+ die+" ,"+tempCity.getName());
			
			tempCity.removeTrolls();
			tempCity.removeDemon();
			tempCity.removeAllMinion();
			tempCity.destroy();
			tempCity.removeTM();
			break;
		case 4://Event card: fog
			int size=p.getHoldingCards().size();
			for(int i=0;i<5;i++)
			{
				p.gain_boardcard(Master.greenCard, Master.brownCard);
				System.out.println(p.getHoldingCards().get(size)+"is picked up and throwd.");
				p.getHoldingCards().remove(size);
			}
			break;
		case 12://event earthquake
			die=Master.roll();
			tempCity=Master.cityCards.get(die-1);
			System.out .println("Die rolls: "+ die+" ,"+tempCity.getName());
			Master.cityCards.get(die-1).destroy();
		case 6://event explosion
			die=Master.roll();
			tempCity=Master.cityCards.get(die-1);
			System.out .println("Die rolls: "+ die+" ,"+tempCity.getName());
			Master.cityCards.get(die-1).destroy();
			break;
		case 11://Event card: throlls
			for(int i=0;i<3;i++)
			{
				die=Master.roll();
				tempCity=Master.cityCards.get(die-1);
				System.out .println("Die rolls: "+ die+" ,"+tempCity.getName());
				Master.cityCards.get(die-1).putTrolls();
			}
			break;
		case 8:// Event Card :demon
			for(int i=0;i<4;i++)
			{
				die=Master.roll();
				die=Master.roll();
				tempCity=Master.cityCards.get(die-1);
				System.out .println("Die rolls: "+ die+" ,"+tempCity.getName());
				Master.cityCards.get(die-1).putDemon();
			}
			break;
		case 3://Event Card:fire
			die=Master.roll();
			ArrayList<Integer> list=new ArrayList<Integer>();
			list.add(die-1);
			while(!list.isEmpty()){
				tempCity=Master.cityCards.get(list.get(0));
				if(tempCity.isBuilding())
				{
					tempCity.destroy();
					byte[]b=tempCity.getNearestCity();
					for(int i=0;i<b.length;i++){
						list.add((int)b[i]);
					}
					
				}
			}
			break;
		case 2://flood
			die=Master.roll();
			tempCity=Master.cityCards.get(die-1);
			int die2=Master.roll();
			byte[] c;
			if(die!=3 ||die !=6){
				System.out.println("Die roll: "+ die+" ,"+ tempCity.Name());
				for(int i=0;i<Master.playerList.size();i++){
					c=tempCity.getNearestCity();
					if(tempCity.minionNum(Master.playerList.get(i))>0){
						
						for(int j=0;j<c.length;j++)
						{
							if(die2!=c[i]||c[j]==3||c[j]==6){
								System.out.println();
							}
						}
					}
				}
			}
				
			
			
			break;
		case 5: //Roits
			int count = 0;
			for(int i=0;i<Master.cityCards.size();i++){
				if(Master.cityCards.get(i).containTroubleMaker())
					count++;
			}
			if(count>=8)
				return false;
			break;
		case 7:// Mysterious Murder
			int num=Master.playerList.size(), counter=0;
			while(counter<num){
				die=Master.roll();
				System.out.println("Player "+((p.getID()-1+counter)%num+1)+" rolls the die:"+die);
				
				counter++;
			}
			int indexCity;
			do{
				System.out.println("Choose a city from following: ");
				for(int i=0;i<Master.cityCards.size();i++)
					System.out.println((i+1)+". "+Master.cityCards.get(i).getName());
				indexCity=Master.scan.nextInt();
				Master.scan.nextLine();
			}while(indexCity<=0 ||indexCity>Master.cityCards.size());
			
			int indexPlayer;
			do{
				System.out.println("Choose a player:");
				for(int i =0;i<Master.playerList.size();i++)
				{
					if(Master.cityCards.get(indexCity-1).minionNum(Master.playerList.get(i))>0)
						System.out.println("Player "+Master.playerList.get(i).getID());
				}
				indexPlayer=Master.scan.nextInt();
				Master.scan.nextLine();
			}while(indexPlayer<=0||indexPlayer>Master.playerList.size());
			if(Master.cityCards.get(indexCity-1).minionNum(Master.playerList.get(indexPlayer-1))>0)
				Master.cityCards.get(indexCity-1).removeMinion(Master.playerList.get(indexPlayer-1));
			else System.out.println("Player "+indexPlayer+ " does not have ant minions.");
			break;
		case 9:
			String input;
			for(int i=0;i<Master.cityCards.size();i++)
			{
				tempCity=Master.cityCards.get(i);
				if(tempCity.isBuilding()){
					System.out.println("Player "+tempCity.getOwner()+", do you want to pay $2 for the building in "+tempCity.getName()+"(Y/N)");
					do{
						input=Master.scan.nextLine().toUpperCase();
					}while(!input.equals("Y") && !input.equals("N"));	
					if(input.equals("Y"))
					{
						Master.playerList.get(tempCity.getOwner()-1).setMoney(Master.playerList.get(tempCity.getOwner()-1).getMoney()-2);
						Master.bank+=2;
					}
					else{
						tempCity.destroy();
					}
				}
			}
			break;
		case 10:
			die=Master.roll();
			Master.blockCity=Master.cityCards.get(die);
			Master.cityCards.remove(die);
			Master.blockCity.removeMinion(p);
			break;
		}
		return true;
	}
}
