package test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import discworld.BoardCard;
import discworld.CardColor;
import discworld.CityCard;
import discworld.PersonalityCard;
import discworld.Player;

public class PlayerTest {
	private Player player;
	private PersonalityCard p;
	private CityCard c;
	Vector<PersonalityCard> personalitycards;
	Vector<BoardCard> brownCard;
	Vector<BoardCard> greenCard;
	
	@Before
	public void initialize() throws IOException{
		c = new CityCard(1, "Dolly Sister",new byte[]{2,3,12},6);
		String[] cards = {"Lord Selachii", "Lord Rust", "Lord de Worde", "Lord Vetinari", "Commander Vimes", "Dragon King of Arms", "Chrysopsase"};
		p = new PersonalityCard(2,"MARS");
		ArrayList<BoardCard> holdingCards = new ArrayList<BoardCard>();
		player=new Player(2,200,CardColor.blue,2,3,p,holdingCards);
		greenCard = new Vector<BoardCard>();
		brownCard = new Vector<BoardCard>();
		BufferedReader br=new BufferedReader(new FileReader("BoardCard.txt"));
		String line;
		String[] info;
		BoardCard bc;
		int counter=0;
		while((line=br.readLine())!=null)
		{
			info=line.split(" ");
			bc=new BoardCard(counter,info[0],info[1],Integer.parseInt(info[2]),info[3]);
			if(counter < 48){
				greenCard.add(bc);
			} else {
				brownCard.add(bc);
			}
			counter++;
		}
		br.close();
		personalitycards = new Vector<PersonalityCard>(7);
		for (int i=0; i<7; i++){
			String dummy = cards[i];
			PersonalityCard temp = new PersonalityCard (1,dummy);
			personalitycards.add(temp);
		}
	}
	
	@Test
	public void ConstructorTest()
	{
		assertEquals(2, player.getID());
		assertEquals(200, player.getMoney());
		assertEquals(CardColor.blue,player.getColor());
		assertEquals(2, player.getMinion());
		assertEquals(3, player.getBuilding());
		assertEquals(p,player.getPersonalityCard());
		
	}
	
	/**
	 * This method checks whether each player gets a random board card or not.
	 */
	@Test
	public void gain_boardcard()
	{
		 player.gain_boardcard(greenCard, brownCard);
		 
		 assertEquals(0,player.getHoldingCards().size());
	}
	
	
	
	
	/**
	 * This method checks whether a player builds a building in a specific city or not.
	 */
	@Test
	public void putBuilding()
	{
		boolean res = c.build(player);
		assertFalse(res);
	}

}
