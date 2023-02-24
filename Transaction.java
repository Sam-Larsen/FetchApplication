import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author Sam Larsen
 *	Transaction object to represent the transactions
 */

public class Transaction {
	private String name;
	private int points;
	private Date timeStamp;
	
	/**
	 * 3 argument constructor
	 * @param n Name of payer
	 * @param p point total
	 * @param date timeStamp of transaction
	 * @throws ParseException when date is not correctly formated
	 */
	public Transaction(String n, int p, String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("\"yyyy-MM-dd'T'HH:mm:ss'Z'\"");
		name = n;
		points = p;
		timeStamp = dateFormat.parse(date);
	}
	
	/**
	 * Date getter
	 * @return timeStamp
	 */
	public Date getDate() {
		return timeStamp;
	}
	/**
	 * Name getter
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Point getter
	 * @return points
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * Point mutator
	 * @param newPoints new value for points
	 */
	public void setPoints(int newPoints) {
		points = newPoints;
	}
}
