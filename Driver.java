import java.io.FileNotFoundException;
import java.io.IOException;


public class Driver 
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{	
		Blocks.readFile("ethereumP1data.txt");
		
		// Constructor for given Block number, miner address
		// timestamp, and transaction_count.
		// The toString method should return 
		// "Block Number: 1 Miner Address: 0x000a"
		Blocks blockA = new Blocks(1, "0x000a", 15000000, 3);
		Blocks blockB = new Blocks(2, "0x0bab", 15003661, 60);
		System.out.println(blockA + "\n");
		
		
		// Print out the date and time according to the sample output.
		// See section 2.3 in project pdf.
		System.out.println(Blocks.getBlockByNumber(15049308).getDate());
		System.out.println(Blocks.getBlockByNumber(15049407).getDate());
		
		System.out.println();
		
		// Print out the difference in time between two Blocks in hours, minutes, and seconds.
		// See section 2.3 in project pdf.
		Blocks.timeDiff(Blocks.getBlockByNumber(15049308), Blocks.getBlockByNumber(15049407));
		Blocks.timeDiff(blockB, blockA);
		Blocks.timeDiff(Blocks.getBlockByNumber(150), Blocks.getBlockByNumber(15049407));
		
		// Sorts the blocks ArrayList in ascending order based on Block number.
		Blocks.sortBlocksByNumber();
		
		System.out.println();
		
		// Finds the number of transactions between two Blocks. 
		// See section 2.4 in project pdf.
		int diff = Blocks.transactionDiff(Blocks.getBlockByNumber(15049308), Blocks.getBlockByNumber(15049407));
		System.out.println("Number of transactions between Block 15049308 and Block 15049407: " + diff);
		
		diff = Blocks.transactionDiff(Blocks.getBlockByNumber(15049320), Blocks.getBlockByNumber(15049335));
		System.out.println("Number of transactions between Block 15049320 and Block 15049335: " + diff);
		
        
	}
}
