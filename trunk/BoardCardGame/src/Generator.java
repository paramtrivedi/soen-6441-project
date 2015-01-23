import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Hashtable;


public class Generator {
	public static void main(String arg[]){
		Hashtable<Integer, boardCard> ht=new Hashtable<Integer,boardCard>();
		try {
			
			BufferedReader br=new BufferedReader(new FileReader("BoardCard.txt"));
			String line;
			String[] info;
			boardCard bc;
			int counter=0;
			while((line=br.readLine())!=null)
			{
				info=line.split(" ");
				//System.out.println(info[0]+"   "+info[1]+"   "+info[2]);
				bc=new boardCard(counter,info[0],Integer.parseInt(info[1]),info[2]);
				ht.put(counter, bc);
				counter++;
			}
			System.out.println(ht.size());
			br.close();
			BufferedWriter bw=new BufferedWriter(new FileWriter("Information.txt"));
			bw.write("ID\tNAME\tA\tR\tS\tM\tE\tP\tI");
			bw.newLine();
			for(int i=0;i<101;i++)
			{
				bc=ht.get(i);
				bw.write(""+bc.Id()+"\t"+
						bc.Name()+"\t"+
						bc.Assassination()+"\t"+
						bc.Remove_Trouble_Marker()+"\t"+
						bc.Scroll()+"\t"+
						bc.Money()+"\t"+
						bc.Event()+"\t"+
						bc.Play_Another_Card()+"\t"+
						bc.Interrupt());
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
