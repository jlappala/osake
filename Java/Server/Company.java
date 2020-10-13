
/**
 * All the companies in the game are instanced from this class.
 * It has functions for modifying and getting company attributes 
 * such as company name, company ID number, amount of company 
 * shares in market and value of company's shares
 */
public class Company {
	private String name;
	int companyID;
	int amountOfShares;
	int valueOfShare;
	boolean bankrupt = false;
	
	public Company (String name, int cID, int amount, int value) {
		this.name = name;
		this.companyID = cID;
		this.amountOfShares = amount;
		this.valueOfShare = value;
	}
	
	/**
	 * @return Name of the company
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * @return ID number of the company
	 */
	public int getID(){
		return this.companyID;
	}
	
	/**
	 * @return Amount of shares this company currently has
	 */
	public int getAmountOfShares(){
		return this.amountOfShares;
	}
	
	/**
	 * @return Value of company's shares
	 */
	public int getValueOfShare(){
		return this.valueOfShare;
	}
	
	/**
	 * @return String containing company attributes separated by a '#' character
	 */
	public String getCompanyShareInfo(){
		String info = this.companyID + "#" + this.name + "#" + this.amountOfShares + 
		"#" + this.valueOfShare;
		return info;
	}
	
	/**
	 * Sets a new value for this company's shares
	 * @param newValueOfShare New value of shares
	 */
	public void setValueofShare(int newValueOfShare){
		this.valueOfShare = newValueOfShare;
	}
	
	/**
	 * Sets a new amount of shares this company has
	 * @param newAmountOfShares New amount of shares
	 */
	public void setAmountOfShares(int newAmountOfShares){
		this.amountOfShares = newAmountOfShares;
	}
	
	/**
	 * Adds shares to current amount of shares
	 * @param add Amount of shares to be added
	 */
	public void addToAmountOfShares(int add){
		this.amountOfShares = this.amountOfShares + add;
	}
	
	/**
	 * Adds value to the current value of shares
	 * @param add Value to be added 
	 */
	public void addToValueOfShare(int add){
		this.valueOfShare = this.valueOfShare + add;
	}
	
	/**
	 * Sets bankruptcy status for this company
	 * @param status Bankruptcy status of this company
	 */
	public void setBankruptcy(boolean status){
		bankrupt = status;
	}
	
	/**
	 * @return Bankruptcy status of this company
	 */
	public boolean isBankrupt(){
		return bankrupt;
	}
	
	/**
	 * Subtracts value of shares by a certain value
	 * @param minus Value to be subtracted
	 */
	public void minusValueOfShare(int minus){
		int newValue = this.valueOfShare - minus;
		if (newValue < 0){
			this.valueOfShare = 0;
		}else {
			this.valueOfShare = newValue;
		}
	}
	
	/**
	 * @return true, if all shares have been bought or if the company 
	 * is bankrupt and value of its shares is 0
	 */
	public boolean allSharesGone(){

		boolean gone = false;
		if(valueOfShare == 0){
			gone = true;
		}
		if(amountOfShares == 0){
			gone = true;
		}
		return gone;
	}	
}
