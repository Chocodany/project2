import java.io.BufferedReader; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

public class Blocks implements Comparable<Blocks>{
	private int number;
	private String miner;
	private long timestamp;
	private int transactions;
	private static ArrayList<Blocks> blocks;
	
	
	
	//Constructors
	public Blocks() {
		
	}
	public Blocks(int number) {
		this.number = number;
	}
	public Blocks(int number, String miner) {
		this.number = number;
		this.miner = miner;
	}
	public Blocks(int number, String miner, long timestamp, int transactions) {
		this.number = number;
		this.miner = miner;
		this.timestamp = timestamp;
		this.transactions = transactions;
	}
	
	//Getters
	public int getNumber() {
		return number;
	}
	public String getMiner() {
		return miner;
	}
	public String getDate() {
		Date date = new Date(timestamp * 1000);
		SimpleDateFormat correct = new SimpleDateFormat("EEE, dd MMMMMM yyyy HH:mm:ss z");
		TimeZone time = TimeZone.getTimeZone("CST");
		correct.setTimeZone(time);
		String dateCST = correct.format(date);
		return dateCST;
	}
	public int getTransactions() {
		return transactions;
	}
	public static ArrayList<Blocks> getBlocks(){
		ArrayList<Blocks> copy = new ArrayList<Blocks>();
		for(int i = 0; i < blocks.size(); i++) {
			copy.add(blocks.get(i));
		}
		return copy;
	}
	
	//Other Methods
	public static void calUniqMiners() {
		ArrayList<String> miners = new ArrayList<>();
		ArrayList<String> calUniq = new ArrayList<>();
		
		for(int i = 0; i < blocks.size() - 1; i++) {
			String temp = blocks.get(i).getMiner();
			miners.add(i, temp);
		}
		
		for (int i = 0; i < blocks.size() - 1; i++) {
			String temp = blocks.get(i).getMiner();
			boolean isDuplicate = false;
			if(Collections.frequency(miners, temp) > 1)
				isDuplicate = true;
			if (isDuplicate == false)
				calUniq.add(temp);
		}
		
		System.out.println("Number of unique Miners: " + calUniq.size());
		System.out.println();
		System.out.println("Each unique Miner and its frequency:");
		for (int i = 0; i < blocks.size() - 1; i++) {
		System.out.println("Miner Address: " + miners.get(i));
		System.out.println("Miner Frequency: " + Collections.frequency(miners, miners.get(i)));
		System.out.println();
		}
	}
	public static int blockDiff(Blocks A, Blocks B) {
		return A.getNumber() - B.getNumber();
	}
	public static Blocks getBlockByNumber(int num) {
		Blocks index = new Blocks();
		index = null;
		for (int i = 0; i < blocks.size(); i++) {
			if (blocks.get(i).number == num) {
				index = blocks.get(i);
				break;
			}
		}
		if (index != null) {
			return index;
		} else {
			return null;
		}
	}
	public static void readFile(String filename) throws FileNotFoundException, IOException {
		BufferedReader scnr = new BufferedReader (new FileReader (filename));
		blocks = new ArrayList<Blocks>();
		String blockInfo[];
		String lines = scnr.readLine();
		while (lines != null) {
			blockInfo = lines.split(",");
			blocks.add(new Blocks(Integer.parseInt(blockInfo[0]), blockInfo[9]));
			lines = scnr.readLine();
		}
		scnr.close();
	}
	
	public static void timeDiff(Blocks first, Blocks second) {
		if (first == null || second == null) {
			System.out.println("A given Block is null.");
		}
		int diff = (int) (first.timestamp - second.timestamp);
		int hours = diff/3600;
		int minutes = (diff%3600)/60;
		int seconds = (diff%3600)%60;
		
		if (hours == 1 && minutes == 1 && seconds == 1) {
			System.out.println("The difference in time between Block " + first + " and Block " + second + " is " + hours + " hour, " + minutes + " minute, " + seconds + " seconds.");
		} else if (hours == 1 && minutes == 1) {
			System.out.println("The difference in time between Block " + first + " and Block " + second + " is " + hours + " hour, " + minutes + " minute, " + seconds + " seconds.");
		} else {
			System.out.println("The difference in time between Block " + first + " and Block " + second + " is " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds.");
		}
	}
	public static int transactionDiff(Blocks first, Blocks second) {
		sortBlocksByNumber();
		if (blocks.indexOf(second) == blocks.indexOf(first)) {
			return -1;
		}
		if (first == null || second == null) {
			return -1;
		}
		if (blocks.indexOf(first) > blocks.indexOf(second)) {
			return -1;
		}
		int sum = 0;
		for (int i = (blocks.indexOf(first) + 1); i < blocks.indexOf(second); i++) {
			sum += blocks.get(i).getTransactions();
		}
		System.out.println(second.getTransactions());
		return sum;
	}
	
	@Override
	public int compareTo(Blocks otherBlock) {
		//return number;
		//sortBlocksByNumber();
		Integer num = (Integer)number;
		return num.compareTo(otherBlock.number);
	}
	public static void sortBlocksByNumber() {
		Collections.sort(blocks);
	}
	
	public String toString() {
		if ((number == 0) && (miner == null)) {
			return "Empty Block";
		}
		if ((number != 0) && (miner == null)) {
			return "Block Number: " + number;
		}
		if ((number != 0) && (miner != null)) {
			return "Block Number: " + number + " Miner Address: " + miner;
		} else {
			return "";
		}
	}
} 
