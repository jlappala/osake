
 /*
 * This class sets PlayerInfo to variables for the end window.
 */
public class PlayerInfo {
	
	String name;
	int deposits;
	int loans;
	int money;
	int stockValue;
	
	public PlayerInfo(){		
	}
	
	/*
     * Method for setting the name
     * @param n, string to be used.
     */
	public void setName(String n){
		this.name = n;
	}
	
	/*
     * Method for setting money
     * @param mny, int to be used.
     */
	public void setMoney(int mny){
		this.money = mny;
	}
	
    /*
     * Method for setting deposits.
     * @param dep, int to be used.
     */
	public void setDeposits(int dep){
		this.deposits = dep;
	}
	
	/*
     * Method for setting loans
     * @param loan, int to be used.
     */
	public void setLoans(int loan){
		this.loans = loan;
	}
	
	/*
     * Method for setting stockvalues
     * @param stv, int to be used.
     */
	public void setStockValue(int stv){
		this.stockValue = stv;
	}
	
	
	/**
	 * Returns an attribute defined by selection parameter
	 * @param selection Parameter defining returned attribute
	 * @return an attribute defined by selection parameter
	 */
	public int returnAttribute(int selection){
		
		if(selection == 1)
			return getStockValue();
		else if(selection == 2)
			return getLoans();
		else if(selection == 3)
			return getDeposits();
		else if(selection == 4)
			return getStockValue();
		else 
			return 0;
	}

	
	public int getMoney(){
		return money;
	}
	
	public String getName(){
		return name;
	}
	
	public int getDeposits(){
		return deposits;
	}
	
	public int getLoans(){
		return loans;
	}
	
	public int getStockValue(){
		return stockValue;
	}
	
	public int getTotalWealth(){
		return money+deposits+stockValue-loans;
	}
}
