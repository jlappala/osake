import java.net.Socket;

/**
 * Possession class encapsulates information of
 * stock ownership for a single company. Instance
 * of this class is created when a player buys 
 * company stocks for the first time. Possession 
 * objects are stored in a vector that's an attribute
 * of a  Player object.
 */
public class Possession {
	private int companyID;
	private Socket playerID;
	private int amountOfShares;
	private int spentValue = 0;
	
	public Possession (int cID, Socket pID, int sharesBought){
		this.companyID = cID;
		this.playerID = pID;
		this.amountOfShares = sharesBought;
	}
	
	/**
	 * @return ID number of the company this stock belongs to
	 */
	public int getCompanyID(){
		return this.companyID;
	}
	
	/**
	 * @return Socket identifying the player who owns the possession
	 */
	public Socket getPlayerID(){
		return this.playerID;
	}
	
	/**
	 * @return Amount of shares the possession has
	 */
	public int getAmountOfShares(){
		return this.amountOfShares;
	}
	
	/**
	 * Sets a new amount of shares the possession has
	 * @param shares New amount of shares
	 */
	public void setAmountOfShares(int shares){
		this.amountOfShares = shares;
	}
	
	/**
	 * Adds to the amount of shares possession currently has
	 * @param shares Amount to be added
	 */
	public void addAmountOfShares(int shares){
		this.amountOfShares = shares + this.amountOfShares;
	}
	
	/**
	 * Subtracts from the amount of shares possession currently has
	 * @param shares Amount to be subtracted
	 */
	public void minusAmountOfShares (int shares){
		this.amountOfShares = this.amountOfShares - shares;
	}
	
	/**
	 * Sets a value indicating how much stocks in the possession
	 * have cost on average for the player
	 * @param value New value indicating how much has been spent on the stocks
	 * of this possession on average.
	 */
	public void setSpentValue(int value){
		if(amountOfShares == 0)
			this.spentValue = 0;
		else
			this.spentValue = value;
	}
	
	/**
	 * @return Value indicating how much has been spent on the stocks
	 * of this possession on average.
	 */
	public int getAverageValue(){
		return spentValue;
	}
}
