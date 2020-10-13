import java.util.Vector;

/**
 * WorkMethods class has methods that have general work
 * algorithms such as sort algorithms and input type tests
 */
public class WorkMethods {

	/**
	 * Checks if the parameter is an integer
	 * @param number Tested parameter
	 * @return -2 if parameter is not an integer, 
	 * else returns parameter parsed into integer
	 */
	public int isNumber(String number){
		
		int result = -2;
		
		try {
		    result = Integer.parseInt(number);
		    return result;
		} catch(NumberFormatException nfe){
			return result;
		}
	}
	
	/**
	 * Divides an integer with another integer
	 * @param number Divided number
	 * @param divider Dividing number
	 * @return Result
	 */
	public int divideInt(String number, String divider){
	
		return Integer.parseInt(number)/Integer.parseInt(divider);
	}
		
	/**
	 * Application of an insertion sort algorithm.
	 * Used to sort player attributes for the end window.
	 * @param players List containing information of players' status at the end of the game
	 * @param selection Parameter defining which player attribute is used for sorting
	 */
	public void sortPlayers(Vector<PlayerInfo> players, int selection){
		
		 int i; 
	     int j;                     
	     PlayerInfo plr;             

	     for(j = 1; j < players.size(); j++){
	    	 plr = players.get(j);
	         for(i = j - 1; (i >= 0) && (players.get(i).returnAttribute(selection) < plr.returnAttribute(selection)); i--){
                 players.set(i+1, players.get(i));
	         }
	         players.set(i+1, plr);   
	     }
	}
}
