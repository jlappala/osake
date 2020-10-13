import java.net.Socket;

/**
 * Loan class represents player loans in the bank
 * It stores the attributes of loan value and the player 
 * the loan is owned by
 */
public class Loan {
	private int amount = 0;
	private Socket playerID;
	
	public Loan(Socket pID){
		this.playerID = pID;
	}
	
	/**
	 * @return Value of the loan
	 */
	public int getAmount(){
		return this.amount;
	}
	
	/**
	 * @return ID of the player owning this loan 
	 */
	public Socket getPlayerID(){
		return this.playerID;
	}
	
	/**
	 * Sets a new Value for the loan
	 * @param amount New loan value
	 */
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	/**
	 * Adds value to the current loan value
	 * @param amount Value to be added
	 */
	public void plusAmount(int amount){
		this.amount = this.amount + amount;
	}
	
	/**
	 * Subtracts a given value from current loan value
	 * @param amount Value to be subtracted
	 */
	public void minusAmount(int amount){
		this.amount = this.amount - amount;
	}
	
}
