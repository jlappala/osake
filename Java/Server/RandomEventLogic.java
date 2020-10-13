import java.util.Vector;

/**
 * RandomEventLogic's drawEvent method is called from GameLogic to pick a 
 * random event influencing the state of the game. Probability of events 
 * is defined by the number range used when randomizing an integer in the 
 * GameLogic's checkForEvents method. Currently all the events have the same
 * probability. New events can be added in this class as necessary.
 */
public class RandomEventLogic {
	
	private GameLogic logic;
	private GameMessageHandler handler;
	private double interestBoost = 0.1;
	private BankLogic bank;
	private Vector<Loan> loans;
	private Vector<Player> players;
	
	private int rounds = 0;
	private int roundBCCalled = 0;
	private int rBCLasts = 10;
	private boolean bankCrisis = false;
	
	/**
	 * Constructor sets game logic objects for this class so that
	 * wide range of events can be used.
	 * @param log GameLogic object of the game
	 * @param h GameMessageHandler object of the game acting as an interface between game logic
	 * and server logic
	 * @param bank BankLogic object of the game
	 */
	public RandomEventLogic(GameLogic log, GameMessageHandler h, BankLogic bank){
		this.logic = log;
		this.handler = h;
		this.loans = new Vector<Loan>();
		this.players = handler.getGame().getPlayers();
		this.bank = bank;
		
		for(int i=0; i<players.size(); i++){
			loans.add(players.get(i).getLoan());			
		}
	}
	
	/**
	 * Checks if the given parameter r from random generator is same with any event
	 * @param r Choosing parameter
	 */
	public void drawEvent(int r){

		rounds++;
		
		//compare the random int to numbers for events and do them if they match
		if(r == 1){
			globalUpswing();
		}
		if(r == 2){
			globalRecession();
		}
		if(r == 3){
			loanMarketCrisis();
		}
		if(r == 4){
			if(bankCrisis == false){
				roundBCCalled = rounds;
				bankCrisis();
			}
		}
		//checks if some events need to be reset
		//Events are reseted when they have affected the game for certain amount of timer ticks
		if(bankCrisis == true && (rounds == roundBCCalled + rBCLasts))
			resetBankCrisis();		
		return;
	}
		
	/**
	 * Adds the integer add to every company's value of share
	 */
	public void globalUpswing(){
		
		int add = 5;
		Vector<Company> group = logic.getCompanies();
		
		for(int i = 0; i < group.size(); i++){
			Company corp = group.get(i);
			corp.addToValueOfShare(add);
		}
		handler.sendStocksInfoToAll();
		handler.sendAllPlayers(handler.serverMessage(	"Due to global upswing in business, " +
														"every company is suddenly worth more! " +
														"The future looks bright!"), players);
		return;
	}
	
	/**
	 * Subtracts the attribute minus from every company's value of share
	 */
	public void globalRecession(){
		//
		int minus = 3;
		Vector<Company> group = logic.getCompanies();
		
		for(int i = 0; i < group.size(); i++){
			Company corp = group.get(i);
			corp.minusValueOfShare(minus);
			logic.checkCompanyBankruptcy(corp);
		}
		handler.sendStocksInfoToAll();
		handler.sendAllPlayers(handler.serverMessage(	"The stock market is in RECESSION, " +
														"the value of shares is diminishing! " +
														"The end is nigh!"), players);
		return;
	}	
		
	/**
	 * Increases the amounts of debt the players have by 15%
	 */
	public void loanMarketCrisis(){

		int cAmount, fAmount;

		//increase the amount of loan by 15%
		for(int i = 0; i < loans.size(); i++){
			Loan pLoan = loans.get(i);
			cAmount = pLoan.getAmount();
			fAmount = (int) (cAmount * 1.15);
			pLoan.setAmount(fAmount);
		}
		
		handler.sendLoanInfoToAll();
		handler.sendAllPlayers(handler.serverMessage(	"The loan market is in crisis! " +
														"Your bank had to increase the amounts of " +
														"owed money on all their loans!"), players);
		return;
	}
	
	/**
	 * Increases the interest rate on loans for settled amount
	 */
	public void bankCrisis(){

		double interest = bank.getLoanInterest();
		bankCrisis = true;
		
		bank.setLoanInterest(interest + interestBoost);
		
		handler.sendInterestInfoToAll();
		handler.sendAllPlayers(handler.serverMessage(	"The banks are in crisis! Interest rate on all " +
														"loans has increased by 10%!"), players);
		return;
	}
	
	/**
	 * Resets the effects of bankCrisis event, ie. returns interest rate back to normal
	 */
	public void resetBankCrisis(){
				
		double interest = bank.getLoanInterest();
		bankCrisis = false;
		
		bank.setLoanInterest(interest - interestBoost);	
		handler.sendInterestInfoToAll();
		handler.sendAllPlayers(handler.serverMessage(	"The bank crisis seems to be finally over, " +
														"interest rates are back to normal!"), players);
		return;
	}
}
