import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * BankLogic defines methods for bank transactions such as 
 * taking and paying loan and making deposits. It also has a
 * timer updating loans and deposits according to current interest rate
 */
public class BankLogic {
	
	private GameMessageHandler handler;
	private double DEPOSIT_INTEREST = 1.03;	
	private double LOAN_INTEREST = 1.03;
	private Timer bankTimer;
	protected int BANK_DELAY = 10;
	
	/**
	 * Instances the BankLogic object
	 * @param hdl GameMessageHandler object acting as an interface between game logic
	 * and server logic
	 * @param delay Delay time for the bankTimer
	 */
	public BankLogic(GameMessageHandler hdl, int delay){
		this.handler = hdl;
		this.bankTimer = new Timer();
		BANK_DELAY = delay;
	}
		
	/**
	 * Adds loan to player and adds the amount of loan to the 
	 * player money pool
	 * @param player Player taking the loan
	 * @param amount Amount of loan
	 */
	public void takeLoan(Player player, int amount){

		Loan pLoan = player.getLoan();

		pLoan.plusAmount(amount);
		player.addMoney(amount);
		handler.sendMoneyInfo(player);
		handler.sendLoanInfo(player);
		handler.sendWealthInfo(player);
	}
	
	/**
	 * Deposits player money to the bank and removes player money accordingly
	 * @param player Making the deposit
	 * @param amount Amount of deposit
	 */
	public void makeDeposit(Player player, int amount){
		// make sure the player has enough money to this deposit
		int pMoney = player.getMoney();
		if (amount > pMoney){
			handler.sendOnePlayer(player, handler.serverMessage("You don't have enough money"));
			return;
		}
		Deposit pDeposit = player.getDeposits();
		//add money to the object and take money from the player.
		pDeposit.plusAmount(amount);
		player.minusMoney(amount);
		handler.sendMoneyInfo(player);
		handler.sendDepositInfo(player);
		handler.sendWealthInfo(player);
	}
	
	/**
	 * Updates the loans every bankTime tick. For every loan existing, 
	 * multiplies the amount of loan with the interest and update the correct Loan object
	 */
	public void updateLoans(){

		Vector<Player>players = handler.getGame().getPlayers();
		
		for(int p = 0; p < players.size(); p++){
			Player plr = players.get(p);
			Loan pLoan = plr.getLoan();			
			int amount = pLoan.getAmount();
			int nAmount = (int) (amount * LOAN_INTEREST);
			pLoan.setAmount(nAmount);			
		}
	}
	
	/**
	 * Updates the deposits every time BankTimer ticks.
	 * For every deposit existing, multiplies the amount of money in the deposit with interest rate
	 * and update it to the correct object
	 */
	public void updateDeposits(){

		Vector<Player>players = handler.getGame().getPlayers();		

		for(int p = 0; p < players.size(); p++){
			Player plr = players.get(p);
			Deposit pDeposit = plr.getDeposits();					
			int amount = pDeposit.getAmount();
			int nAmount = (int) (amount * DEPOSIT_INTEREST);
			pDeposit.setAmount(nAmount);			
		}		
	}
	
	/**
	 * Handles player debt paying transaction
	 * @param player Player paying the debt
	 * @param amount Amount paid
	 */
	public void payDebt(Player player, int amount){
		//get proper loan object vector
		Loan pLoan = player.getLoan();

		if(amount <= player.getMoney()){
			int loanAmount = pLoan.getAmount();
			//if player is trying to pay more debt than he owes
			if(loanAmount < amount)
				amount = loanAmount;

			//take the paid amount out of loans total amount
			pLoan.minusAmount(amount);
			//take the money from player, which he paid with
			player.minusMoney(amount);
			handler.sendMoneyInfo(player);
			handler.sendLoanInfo(player);
			handler.sendWealthInfo(player);
			return;
			
		}else
			handler.sendOnePlayer(player, handler.serverMessage("You don't have enough money"));				
	}
	
	/**
	 * Handles player money withdrawal transaction
	 * @param player Player withdrawing money
	 * @param amount Amount withdrawn
	 */
	public void withdrawMoney(Player player, int amount){
		//get proper deposit objects
		Deposit pDeposit = player.getDeposits();
		int amountOfDeposit = pDeposit.getAmount();
		
		if(amount > amountOfDeposit){
			//Player tries to withdraw more than he has in his deposit
			handler.sendOnePlayer(player,handler.serverMessage("You dont have that much to withdraw!"));
			return;
		}
		if(amount <= amountOfDeposit){
			//get the money from deposit
			pDeposit.minusAmount(amount);
			//give player money withdrawn
			player.addMoney(amount);
			handler.sendMoneyInfo(player);
			handler.sendDepositInfo(player);
			handler.sendWealthInfo(player);
			return;
		}
	}	
	
	/**
	 * Updates loans and deposits in a time intervals defined by
	 * BANK_DELAY attribute, and sends updated information to all players.
	 */
	public void updateValueOfDeposits(){

		int delay = BANK_DELAY * 1000;
		int period= BANK_DELAY * 1000;
		
		bankTimer.schedule(new TimerTask(){
			
			public void run(){
				updateDeposits();
				updateLoans();
				handler.sendDepositInfoToAll();
				handler.sendLoanInfoToAll();		
				handler.sendWealthInfoToAll();
			}
		}, delay, period);			
	}
	
	/**
	 * @return Interest rate of the loans
	 */
	public double getLoanInterest(){
		return this.LOAN_INTEREST;
	}
	
	/**
	 * @return Interest rate of the deposits
	 */
	public double getDepInterest(){
		return this.DEPOSIT_INTEREST;
	}
	
	/**
	 * Sets a new interest rate for loans
	 * @param interest New interest rate
	 */
	public void setLoanInterest(double interest){
		LOAN_INTEREST = interest;
	}
	
	/**
	 * Sets a new interest rate for deposits
	 * @param interest New interest rate
	 */
	public void setDepositInterest(double interest){
		DEPOSIT_INTEREST = interest;
	}
	
	/**
	 * Sets the delay for bankTimer ticks
	 * @param delay Delay time in seconds
	 */
	public void setBankTimerDelay(int delay){
		BANK_DELAY = delay;
	}
	
	/**
	 * Stops the bankTimer
	 */
	public void stopBankTimer(){
		bankTimer.cancel();
	}	
}
