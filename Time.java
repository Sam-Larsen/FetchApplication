import java.util.Comparator;
/**
 * 
 * @author Sam Larsen
 * 2/23/2023
 *	Simple comparator class that sorts by Date oldest to newest
 */
public class Time implements Comparator<Transaction>{
	
	@Override
	public int compare(Transaction t1, Transaction t2) {
		return t1.getDate().compareTo(t2.getDate());
	}
	
}
