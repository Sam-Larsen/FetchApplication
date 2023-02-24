import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;


/**\
 * 
 * @author Sam Larsen
 *	2/23/2023
 *	This class contains the main function for the point spending project
 *	This class reads a file containing point transactions and takes in
 *	a parameter for the number of points to spend and outputs the result
 *	of spending the points from oldest to newest.
 */
public class PointSpender{
	// Requires arguments args[0] = int points to be spent and args[1] = name of file to be read
	public static void main(String[] args) {
		int pointsToSpend = Integer.parseInt(args[0]);
		String fileName = args[1];
		//initial capacity of priority queue
		int CAPACITY = 10;
		//comparator that sorts by date for the priority queue
		Comparator<Transaction> timeSort = new Time();
		PriorityQueue<Transaction> transactions = new PriorityQueue<>(CAPACITY, timeSort);
		
		Scanner s;
		String tempName;
		int tempPoints;
		String tempDate;
		//try to read the file into a priority queue of type Transaction
		try {
			s = new Scanner(new File(fileName)).useDelimiter(",|\\R");
			s.nextLine();
			while (s.hasNextLine()) {
				tempName = s.next();
				tempPoints = s.nextInt();
				tempDate = s.next();
				transactions.offer(new Transaction(tempName, tempPoints, tempDate));
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found");
		} catch (ParseException e) {
			System.err.println("File Not Correclty Formatted");
		}
		//HashMap used to keep track of polled elements from priority queue
		HashMap<String, Integer> output = new HashMap<>();
		for(Transaction t: transactions) {
			output.put(t.getName(), 0);
		}
		
		
		Transaction temp;
		//Spends points in order of date and updates HashMap with new point totals
		//Terminates when all points have been spent
		while(pointsToSpend != 0 && transactions.isEmpty() != true) {
			temp = transactions.poll();
			
			if(pointsToSpend >= temp.getPoints()) {
				pointsToSpend -= temp.getPoints();
				output.put(temp.getName(),output.get(temp.getName())+0);
			}
			else {
				temp.setPoints(temp.getPoints()- pointsToSpend);
				pointsToSpend = 0;
				output.put(temp.getName(), output.get(temp.getName())+temp.getPoints());
			}
			
		}
		//Adds all un-polled values to fully update HashMap
		while(transactions.isEmpty() != true) {
			temp = transactions.poll();
			output.put(temp.getName(), output.get(temp.getName())+temp.getPoints());
		}
		
		//Terminates if point total is insufficient for requested amount spent
		if(pointsToSpend > 0) {
			System.out.println("Not Enough Points");
			return;
		}
		
		//Outputs the point totals for each payer
		for(String key: output.keySet()) {
			System.out.println(key+": "+output.get(key));
		}
		
	}
}
