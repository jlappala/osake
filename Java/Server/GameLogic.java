import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * GameLogic has general work methods used by instances of
 * BankLogic and StockTransactionLogic functionality. It also has
 * a running timer randomizing events for the game. List of
 * companies used in the game are initialized from a config file
 * and stored in this class. 
 */
public class GameLogic {
	
	protected GameMessageHandler handler;
	protected Vector<Company> companies;
	private Vector<String> companyNames;
	private BankLogic bank;
	private RandomEventLogic events;
	private StockTransactionLogic transaction;
	private Timer eventTimer;
	
	private int UPDATE_INTERVAL = 5;
	private int NUMBER_OF_SHARES = 2000;
	private int STARTING_VALUE = 50;
	private int STARTING_MONEY = 500;
	private int WINNING_WEALTH = 200000;
	
	/**
	 * Constructor sets game parameters from an array of settings and initializes 
	 * specific game logic objects
	 * @param mh GameMessageHandler object used as an interface between game logic and server logic
	 * @param settings Array containing game settings
	 */
	public GameLogic(GameMessageHandler mh, int[] settings){
		
		this.companyNames = new Vector<String>();
		this.handler = mh;
		
		STARTING_MONEY = settings[0];	
		STARTING_VALUE = settings[1];
		NUMBER_OF_SHARES = settings[2];
		UPDATE_INTERVAL = settings[3];
		WINNING_WEALTH = settings[4];
		
		if(UPDATE_INTERVAL < 5)
			UPDATE_INTERVAL = 5;
				
		this.eventTimer = new Timer();
		bank = new BankLogic(handler, (int) (UPDATE_INTERVAL * 2.5));
		transaction = new StockTransactionLogic(handler, this, UPDATE_INTERVAL);

		initCompanies();		
	}
	 	
	/**
	 * Populates the list of companies used in the game using information loaded from
	 * a configuration file.
	 */
	private void initCompanies(){
				
		companies = new Vector<Company>();
		
		int id = 0001;
		int numberOfShares = NUMBER_OF_SHARES;
		int startingValue = STARTING_VALUE;
		
		loadConfiguration();
		
		for(int i = 0; i < companyNames.size(); i++){
			companies.add(new Company(companyNames.get(i), id, numberOfShares, startingValue));
			id++;		
		}
	}
	
	/**
	 * Loads company names and interest rates from a configuration file. 
	 */
	void loadConfiguration(){
		
	    Properties prop = new Properties();
		
		try {
		    String fileName = "config.txt";
		    InputStream is = new FileInputStream(fileName);
		    prop.load(is);	  
		    
		    bank.setLoanInterest(parseInterestRatetoDouble(prop.getProperty("loanInterest")));
		    bank.setDepositInterest(parseInterestRatetoDouble(prop.getProperty("depositInterest")));
		} catch (FileNotFoundException e) {
			  e.printStackTrace();
		} catch (IOException e) {
			  e.printStackTrace();
		}
		    
	    for(int i = 1; ; i++){
	    	
	    	String name = prop.getProperty("company"+i);
	    	
	    	if(name != null)
	    		companyNames.add(name);
	    	else
	    		break;
		}    
	}
	
	/**
	 * Timer picking random events defined in the RandomEventLogic class
	 */
	public void checkForEvents(){
		//Timer that updates values of shares

		events = new RandomEventLogic(this, handler, bank);
		
		int delay = UPDATE_INTERVAL * 1500;
		int period= UPDATE_INTERVAL * 1500;
		
		eventTimer.schedule(new TimerTask(){
			
			Random rand = new Random();
			
			public void run(){
				events.drawEvent(rand.nextInt(30));				
			}
		}, delay, period);			
	}
	
	/**
	 * Checks if a company has gone bankrupt
	 * @param comp Checked company
	 */
	public void checkCompanyBankruptcy(Company comp){
		
		if(comp.isBankrupt() && comp.getValueOfShare() > 0)
			comp.setBankruptcy(false);
		
		if(comp.getValueOfShare() == 0 && !comp.isBankrupt()){
			comp.setBankruptcy(true);
			handler.sendAllPlayers(handler.serverMessage(	"Times are tough and " + comp.getName() + " has gone bankrupt. " +
															"A sudden boost in economy could give it a fresh start though."
															), handler.getGame().getPlayers());
		}
	}
	
	/**
	 * Tries to parse a string to a double
	 * @param number String to be parsed
	 * @return Parsed double if successful, if not returns constant interest 
	 */
	double parseInterestRatetoDouble(String number){
		double interest = 1.01;
		boolean ok = true;
		//tries to parse the String to double from config file, if it fails returns standard interest rate (1.01)
		try{
			interest = Double.parseDouble(number);
		}catch(NumberFormatException NFE){
			ok = false;
		}catch(NullPointerException NPE){
			ok = false;
		}
			
		if(ok == true)
			return interest;
		else{
			System.out.println("Reading interest from configfile failed, setting rate to 1.01");
			return interest;
		}
	}
	
	/**
	 * Calculates how much a player has wealth in stocks
	 * @param player Owner of the stocks
	 * @return Total value of the stocks
	 */
	public int calculatePlayerStockValue (Player player){

		Vector <Possession> playerP = player.getPossessions();
		int overallValue = 0;
		//one by one get the amount of shares owned and with that, calculate variable of those shares
		for(int i = 0; i < playerP.size();i++){
			Possession check = playerP.get(i);
			int valueOfPossession = getPossessionValue(check);
			overallValue = overallValue + valueOfPossession;
		}
		return overallValue;
	}

	/**
	 * Returns value of a possession
	 * @param possession Possession to be evaluated 
	 * @return Total value of the possession
	 */
	public int getPossessionValue(Possession possession){
		int cID = possession.getCompanyID();
		int amountOfShares = possession.getAmountOfShares();
		Company corp = getCompanyByID(cID);
		int valueOfShare = corp.getValueOfShare();
		int totalValue = amountOfShares * valueOfShare;
		return totalValue;
	}
	
	/**
	 * Finds a player's possession by a company ID
	 * @param plr Player owning the possession
	 * @param cID Company ID
	 * @return Found possession
	 */
	public Possession getPossessionByCompanyID(Player plr, int cID){
		
		Vector<Possession> possessions = plr.getPossessions();
		
		for(int i = 0; i < possessions.size();i++){
			int corp = possessions.get(i).getCompanyID();
			if (corp == cID){
				return possessions.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Finds a company by it's ID
	 * @param CompanyID ID of the searched company
	 * @return Found company, or null if not found
	 */
	public Company getCompanyByID(int CompanyID){
		for(int i = 0; i < companies.size();i++){
			Company corp = companies.get(i);
			if (CompanyID == corp.getID()){
				return corp;
			}
		}
		return null;
	}
			
	/**
	 * Checks if a player has gone bankrupt by owing two times more than he/she owns assets
	 * @param player Checked player
	 * @return true if player is bankrupt, else return false
	 */
	public boolean bankruptcyCheck(Player player){

		int amountOfDebt = player.getLoan().getAmount();
		int amountDeposited = player.getDeposits().getAmount();
		int playerMoney = player.getMoney();
		int valueOfShares = calculatePlayerStockValue(player);
		
		if(amountOfDebt > 2*(playerMoney + valueOfShares + amountDeposited))		
			return true;	
		else
			return false;		
	}
	
	/**
	 * Bankrupts a player returning player's stocks back to the market and sending
	 * a notification to all players
	 * @param player Bankrupt player
	 */
	public void bankruptPlayer(Player player){
		
		handler.sendAllPlayers(handler.serverMessage(player.getName()+" has gone bankrupt"), handler.getGame().getPlayers());		
		handler.sendOnePlayer(player, "BROKE");
		player.setBankruptcy(true);
		checkPlayerBankruptcy();
		handler.returnPlayerStocksToMarket(player);		
	}
	
	/**
	 * checks every player for bankruptcy, called by timer.
	 * sets player bankrupt boolean to true if player deemed bankrupt.
	 */
	public void checkBankruptcyForAllPlayers(){

		Vector<Player>players = handler.getGame().getPlayers();
		
		for(int i = 0; i < players.size(); i++){
			Player checked = players.get(i);
			if(checked.isBankrupt() == false)
				if(bankruptcyCheck(checked))
					bankruptPlayer(checked);
		}
	}
	
	/**
	 * Checks if all players are bankrupt, if so, ends game
	 */
	public void checkPlayerBankruptcy(){

		Vector<Player> players = handler.getGame().getPlayers();
		
		boolean ok;
		for(int i = 0; i < players.size();i++){
			Player p = players.get(i);
			ok = p.isBankrupt();
			if(ok == false){
				return;
			}
		}
		handler.endGame();
		return;
	}
	
	/**
	 * Checks if all shares have been bought and if so, gameOver boolean is set as true
	 * and the game will be ended
	 */
	public void checkIfAllSharesBought(){

		boolean ok;
		//Get status of all stocks from companies and add them to array vector.
		for(int i = 0; i < companies.size();i++){
			Company corp = companies.get(i);
			ok = corp.allSharesGone();
			if(ok == false){
				return;
			}
		}
		handler.endGame();
		return;
	}
	
	/**
	 * Checks if any of the players has amassed the required winning wealth, if so gameOver boolean
	 * is set as true, as game will be ended. Also notifications are sent to player if some player is
	 * doing very well or very badly.
	 */
	public void checkPlayerWealth(){
 
		Vector<Player> players = handler.getGame().getPlayers();
		for(int i = 0; i < players.size();i++){
			Player cPlayer = players.get(i);
			int wealth = calculateWealth(cPlayer);
			int loan = players.get(i).getLoan().getAmount();
			
			cPlayer.setTotalWealth(wealth);
			if(wealth >= WINNING_WEALTH){
				handler.endGame();
			}
			
			if(wealth >= (int)(WINNING_WEALTH * 0.7) && cPlayer.isWinnning() == false){
				handler.sendAllPlayers(handler.serverMessage(cPlayer.getName() + " has amassed impressive amount of wealth."), players);
				cPlayer.setWinning(true);
				cPlayer.setLosing(false);
			}

			if((loan > (int) (1.5*(calculateWealth(cPlayer) + loan))) && (cPlayer.isLosing() == false)){
				handler.sendAllPlayers(handler.serverMessage("Rumor has it that " + cPlayer.getName() + "'s investments are doing quite bad."), players);
				cPlayer.setLosing(true);
				cPlayer.setWinning(false);
			}		
		}
	}
	
	/**
	 * Calculates players overall wealth and returns it
	 * @param player Player whose wealth is calculated
	 * @return Total wealth of a player
	 */
	public int calculateWealth(Player player){

		int stocks, loans, deposits, money, wealth;
		stocks = calculatePlayerStockValue(player);
		money = player.getMoney();
		deposits = player.getDeposits().getAmount();
		loans = player.getLoan().getAmount();
		wealth = stocks + deposits + money;
		wealth = wealth - loans;
		return wealth;
	}
	
	/**
	 * Calculates a new value for how much a possession has cost on average
	 * @param pos Calculated possession
	 * @param value Stock value
	 * @param amount Stock amount
	 */
	public void setPossessionCost(Possession pos, int value, int amount){
		
		int cost = value * amount;		
		int currentAmount = pos.getAmountOfShares();
		int currentValue = pos.getAverageValue() * currentAmount;
		
		pos.setSpentValue((cost + currentValue) / (amount + currentAmount));
	}
	
	/**
	 * Starts the game logic updaters
	 */
	public void start(){
		transaction.updateValueOfSharesTimer();
		bank.updateValueOfDeposits();
		checkForEvents();
	}
	
	/**
	 * Stops the game logic updaters
	 */
	public void stop(){
		transaction.stopStockTimer();
		bank.stopBankTimer();
		eventTimer.cancel();
	}
	
	/**
	 * @return List of companies
	 */
	public Vector<Company> getCompanies(){
		return companies;
	}	
	
	/**
	 * @return Starting money for the game
	 */
	public int getStartMoney(){
		return STARTING_MONEY;
	}
	
	/**
	 * @return Total wealth required to win the game
	 */
	public int getWinningWealth(){
		return WINNING_WEALTH;
	}
	
	/**
	 * @return Instance of BankLogic associated with this class
	 */
	public BankLogic getBankLogic(){
		return bank;
	}
	
	/**
	 * @return Instance of StockTransactionLogic associated with this class
	 */
	public StockTransactionLogic getTransactionLogic(){
		return transaction;
	}
	
	/**
	 * @return Instance of MessageHandler associated with this class
	 */
	public GameMessageHandler getMessageHandler(){
		return handler;
	}
}
