import java.net.Socket;

/**
 * Deposit class represents player deposits in the bank
 * It stores the attributes of deposit value and the player ID
 * the deposit is owned by.
 */
public class Deposit {
private int amount = 0;
private Socket playerID;
	
	public Deposit(Socket playerID){
		this.playerID = playerID;	
	}
	
	/**
	 * @return Value of the deposit
	 */
	public int getAmount(){
		return this.amount;
	}
	
	/**
	 * @return Player that owns the deposit
	 */
	public Socket getPlayerID(){
		return this.playerID;
	}
	
	/**
	 * Sets new value for the deposit
	 * @param amount New value for the deposit
	 */
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	/**
	 * Adds value to the deposit
	 * @param amount Value to be added
	 */
	public void plusAmount(int amount){
		this.amount = this.amount + amount;
	}
	
	/**
	 * Subtracts value from the deposit
	 * @param amount Value to be subtracted
	 */
	public void minusAmount(int amount){
		this.amount = this.amount - amount;
	}
}
