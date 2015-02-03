package test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import discworld.CityCard;
import discworld.PersonalityCard;
import discworld.Player;
import discworld.boardCard;
import discworld.dice;

public class TestCases {
	Vector<PersonalityCard> personalitycards;
	Vector<boardCard> brownCard;
	Vector<boardCard> greenCard;
	@Before
	public void initialize() throws NumberFormatException, IOException{
		String[] cards = {"Lord Selachii", "Lord Rust", "Lord de Worde", "Lord Vetinari", "Commander Vimes", "Dragon King of Arms", "Chrysopsase"};

		greenCard = new Vector<boardCard>(48);
		brownCard = new Vector<boardCard>(53);
		personalitycards = new Vector<PersonalityCard>(7);
		BufferedReader br=new BufferedReader(new FileReader("BoardCard.txt"));
		String line;
		String[] info;
		boardCard bc;
		int counter=0;
		while((line=br.readLine())!=null)
		{
			info=line.split(" ");
			bc=new boardCard(counter,info[0],Integer.parseInt(info[1]),info[2]);
			if(counter < 48){
				greenCard.add(bc);
			} else {
				brownCard.add(bc);
			}
			counter++;
		}
		br.close();
		for (int i=0; i<7; i++){
			String dummy = cards[i];
			PersonalityCard temp = new PersonalityCard (dummy);
			personalitycards.add(temp);
		}
		
	}
	
	@Test
	public void test1(){
		dice d=new dice();
		int result = d.roll();
		boolean res;
		if(result>0){
			res = true;
		} else{
			res = false;
		}
		assertTrue(res);
	}

	@Test
	public void test2(){
		ArrayList<Player> playerList = new ArrayList<Player>();
		boolean res = new CityCard(2,"Unreal Estate").destory(playerList);
		assertFalse(res);
	}

	@Test
	public void test3(){
		String information = "player1-blue";
		
		Player p = new Player(information, personalitycards, greenCard, brownCard);
		boolean res = new CityCard(1, "Dolly Sister").build(p);
		assertTrue(res);
	}
	
	@Test
	public void test4(){
		
		CityCard cc = new CityCard(1, "Dolly Sister");
		cc.putDemon();
		//assertTrue(res);
		assertEquals(cc.getDemon(),1);
	}
	
	@Test
	public void test5(){
		String information = "player1-blue";
		
		Player p = new Player(information, personalitycards, greenCard, brownCard);
		boolean res = new CityCard(1, "Dolly Sister").putMinion(p);
		


		assertTrue(res);
	}
}
