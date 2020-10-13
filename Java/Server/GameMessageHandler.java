import java.util.Vector;
import java.util.regex.Pattern;

/**
 * GameMessageHandler class contains methods that handle game events
 * fired by players and server side game logic. Refer to the client server 
 * interface documentation for more specific information on messages and
 * their usage.
 */
public class GameMessageHandler extends MessageHandler{
	
	private Game game = null;
	private GameLogic logic;
	private String playerInfoString = null;
	
	public GameMessageHandler(GameServer s){		
		super(s);
	}	
	
	/**
	 * Sets a game for this GameMessageHandler object
	 * @param g The game object to be set
	 */
	public void setGame(Game g){
		this.game = g;	
		this.logic = game.getGameLogic();
	}
	
	/**
	 * Breaks a message received from a client into parts using '#' character 
	 * as a separator and chooses the right method for processing
	 * @param s The string to be processed
	 * @param plr Player sending the message
	 */
	public void processMessage(String s, Player plr){
				
		Pattern p = Pattern.compile("#");
	    String[] items = p.split(s);	
	    
       	String cmd = items[0];    	
        
       	/* These methods can be called from player's client 
       	 * only if the player is not bankrupt
       	 */
       	if(!plr.isBankrupt() && game.inProgress()){
       	
	       	if(cmd.equals("BUY")){
	    		buyStocks(items, plr);
	    	}else if(cmd.equals("SELL")){
	    		sellStocks(items, plr);
	    	}else if(cmd.equals("ADLN")){
	    		takeLoan(items, plr);
	    	}else if(cmd.equals("PAYLN")){
	    		payLoan(items, plr);
	    	}else if(cmd.equals("DEPMN")){
	    		depositMoney(items, plr);
	    	}else if(cmd.equals("WDRMN")){
	    		withdrawMoney(items, plr);
	    	}
		}	
       	/* These methods can be called from player's client 
       	 * even if the player is bankrupt
       	 */
		if(cmd.equals("SAY")){
	    	sayAll(items, plr);
		}else if(cmd.equals("EXTGM")){
			removePlayer(plr);
		}else if(cmd.equals("START")){
    		startGame();   
		}else if(cmd.equals("DISCONNECT")){
			game.removePlayer(plr);
			removeConnection(plr);
			game.updateNames();
			sendPlayersNameInfoToAll();
	    }
	}
	
	/**
	 * Calls game logic method to handle player's purchase of stocks
	 * @param stocks An array containing stock information
	 * @param plr Player purchasing the stocks
	 */
	public void buyStocks(String[] stocks, Player plr){
		
		int coID = Integer.parseInt(stocks[1]);
		int price = Integer.parseInt(stocks[2]);
		int amount = Integer.parseInt(stocks[3]);		
		
		if(amount > 0)
			logic.getTransactionLogic().buyShares(plr, coID-1, price, amount);		
	}	
	
	/**
	 * Calls game logic method to handle player's selling of stocks
	 * @param stocks An array containing stock information
	 * @param plr Player selling the stocks
	 */
	public void sellStocks(String[] stocks, Player plr){
		
		int coID = Integer.parseInt(stocks[1]);
		int amount = Integer.parseInt(stocks[2]);		
		
		if(amount > 0)
			logic.getTransactionLogic().sellShares(plr, coID-1, amount);
	}	
	
	/**
	 * Calls game logic method to handle player's taking of a loan
	 * @param items Array containing the loan information
	 * @param plr Player taking the loan
	 */
	private void takeLoan(String[] items, Player plr){
		
		int amount = Integer.parseInt(items[1]);
		
		logic.getBankLogic().takeLoan(plr, amount);
	}
	
	/**
	 * Calls game logic method to handle player's paying of a loan
	 * @param items Array containing the loan information
	 * @param plr Player paying the loan
	 */
	private void payLoan(String[] items, Player plr){
		
		int amount = Integer.parseInt(items[1]);
		
		logic.getBankLogic().payDebt(plr, amount);
	}
	
	/**
	 * Calls game logic method to handle player's depositing of money
	 * @param items Array containing the deposition info
	 * @param plr Player making the deposit
	 */
	private void depositMoney(String[] items, Player plr){
		
		int amount = Integer.parseInt(items[1]);
		
		logic.getBankLogic().makeDeposit(plr, amount);		
	}
	
	/**
	 * Calls game logic method to handle player's withdrawal of money
	 * @param items Array containing the withdrawal information
	 * @param plr Player making the withdrawal
	 */
	private void withdrawMoney(String[] items, Player plr){
		
		int amount = Integer.parseInt(items[1]);
		
		logic.getBankLogic().withdrawMoney(plr, amount);		
	}
		

	/**
	 * Sends a chat message from certain player to all the other players
	 * in the same game 
	 * @param msg Array containing the message to be sent
	 * @param plr Player sending the message
	 */
	public void sayAll(String[] msg, Player plr){
		
		if(msg.length>1)
			sendAllPlayers("SAY#"+plr.getName()+": " + msg[1], game.getPlayers());
	}
	
	/**
	 * Calls game logic to start the game update timers and sends all 
	 * players necessary starting information about the current game.
	 */
	public void startGame(){
		
		if(!game.inProgress()){
			String startString = "Game ends if all stocks are bought or a broker amasses ";
			startString += logic.getWinningWealth() + " euros of wealth";
			
			game.setGameInProgress(true);
			logic.start();
			
			sendAllPlayers(serverMessage("Game has started!"), game.getPlayers());
			sendAllPlayers(serverMessage(startString), game.getPlayers());
			sendStocksInfoToAll();
			sendInterestInfoToAll();
			sendWealthInfoToAll();
			sendDepositInfoToAll();
			sendLoanInfoToAll();
		}else
			sendAllPlayers(serverMessage("Game already in progress!"), game.getPlayers());
	}
		
	/**
	 * Calls game logic to stop the update timers, and sends all players
	 * a message indicating the game has ended with final player standings.
	 */
	public void endGame(){
		
		String msg = "GAMEOVER";
		
		game.setGameInProgress(false);
		logic.stop();
		
		updatePlayerInfoString();
		msg += playerInfoString;
		
		sendAllPlayers(msg, game.getPlayers());
	}
	
	/**
	 * Removes a player from the current game and places him/her in the lobby
	 * @param plr Player to be removed
	 */
	public void removePlayer(Player plr){
		
		game.removePlayer(plr);
		
		super.server.getLobby().addPlayerToLobby(plr);
		game.updateNames();
		sendPlayersNameInfoToAll();
	} 
    
    /**
     * Sends a message containing both deposit -and loan interest to all players
     */
    public void sendInterestInfoToAll(){
    	
    	String msg = "UDINTR";
    	int depInterest;
    	int loanInterest;
    	
    	depInterest = (int) ((logic.getBankLogic().getDepInterest() - 1.00) * 100);
    	loanInterest = (int) ((logic.getBankLogic().getLoanInterest() - 1.00) * 100);
    	
    	msg += "#" + depInterest + "#" + loanInterest;
    	
    	sendAllPlayers(msg, game.getPlayers());
    }
    
	/**
	 * Sends a message containing the value of deposits to a player
	 * @param plr Player to receive the message
	 */
	public void sendDepositInfo(Player plr){
		
		String deposit = Integer.toString(plr.getDeposits().getAmount());
		
		String message = "UDDEP#" + deposit;
		sendOnePlayer(plr, message);
	}	
	
	/**
	 * Sends all players a message containing the value of deposits 
	 * he/she currently has
	 */
	public void sendDepositInfoToAll(){
		
		Vector<Player> players = game.getPlayers();			
		
		if(!players.isEmpty()){
	    	for(int i=0; i<players.size(); i++){
	    		String info = "UDDEP#" + players.get(i).getDeposits().getAmount();
	    		sendOnePlayer(players.get(i), info);
    		}	    	
		}
	}
	
	/**
	 * Sends a player a message containing the value of his/her loans
	 * @param plr Player receiving the message
	 */
	public void sendLoanInfo(Player plr){
		
		String message = "UDLN#" + plr.getLoan().getAmount();
		sendOnePlayer(plr, message);
	}
		
	/**
	 * Sends all players a message containing the value of loans 
	 * he/she currently has
	 */
	public void sendLoanInfoToAll(){
		
		Vector<Player> players = game.getPlayers();			
		
		if(!players.isEmpty()){
	    	for(int i=0; i<players.size(); i++){	    		
	    		String info = "UDLN#" + players.get(i).getLoan().getAmount();
	    		sendOnePlayer(players.get(i), info);	    		
	    	}
		}
	}
	
	/**
	 * Sends all players a message containing the stock information of a single company
	 * @param comp Company whose stock information will be sent
	 */
	public void sendSingleStockInfoToAll(Company comp){
		
		String message = "UDST#" + comp.getCompanyShareInfo();
		sendAllPlayers(message, game.getPlayers());
	}
	
	/**
	 * Sends all players stock informations of all companies
	 */
	public void sendStocksInfoToAll(){
		Vector<Company> companies = logic.getCompanies();
		
		for(int i = 0; i < companies.size(); i++){
			sendAllPlayers("UDST#" + companies.get(i).getCompanyShareInfo(), game.getPlayers());
		}	
	}
	
	/**
	 * Sends a player a message containing information of how many stocks he/she owns from certain
	 * company, and how much has been invested in them on average.
	 * @param plr Player receiving the message
	 * @param cid Id number of a company
	 * @param amount Amount of stocks that are owned from the company
	 * @param avCost Amount that has been invested in the company stocks on average
	 */
	public void sendPossessionInfo(Player plr, int cid, int amount, int avCost){
		
		String message = "UDPS#" + cid + "#" + amount + "#" + avCost;
		sendOnePlayer(plr, message);
	}
	
	/**
	 * Sends all players a message containing information of how much each player has value in stocks
	 */
	public void sendPossessionsValueToAll(){
		
		Vector<Player>players = game.getPlayers();
		String message = "UDVAL";
		
		for(int i=0; i<players.size(); i++){
			message += ( "#" + players.get(i).getName() + ": " + logic.calculatePlayerStockValue(players.get(i)) + " euros" );		
		}		
		sendAllPlayers(message, game.getPlayers());
	}
	
	/**
	 * Sends a player a message containing information of how much he/she has
	 * amassed total wealth
	 * @param plr Player receiving the message
	 */
	public void sendWealthInfo(Player plr){
		
		String message = "UDWTH";		
		
		message += ( "#" + logic.calculateWealth(plr) );		
				
		sendOnePlayer(plr, message);
	}
	
	/**
	 * Sends all players a message containing information of how much they have amassed total wealth
	 */
	public void sendWealthInfoToAll(){
		Vector<Player> players = game.getPlayers();
		
		for(int i = 0; i < players.size(); i++){
			Player current = players.get(i);
			sendOnePlayer(current, "UDWTH#" + logic.calculateWealth(current));
		}	
	}
	
	/**
	 * Sends a player a message containing information of how much he/she has money
	 * @param plr Player receiving the message
	 */
	public void sendMoneyInfo(Player plr){
				
		String message = "UDMO#" + plr.getMoney();
		sendOnePlayer(plr, message);
	}
	
	/**
	 * Sends a player a message containing all the names of players in the same game 
	 * @param plr Player receiving the message
	 */
	public void sendPlayersNameInfo(Player plr){
		
		String message = "UDNM" + game.getPlayerNames();
		sendOnePlayer(plr, message);
	}
	
	/**
	 * Sends all players in the current game a message containing all the names of players in the same game 
	 */
	public void sendPlayersNameInfoToAll(){
		
		String message = "UDNM" + game.getPlayerNames();
		sendAllPlayers(message, game.getPlayers());

	}
	
	/**
	 * Returns player's stocks back to the market
	 * @param plr Player whose stocks will be returned
	 */
	public void returnPlayerStocksToMarket(Player plr){
		
		Vector<Possession>pos = plr.getPossessions();
		Vector<Company>comp = logic.getCompanies();
		
		for(int i=0; i<pos.size(); i++){
			for(int n=0; n<comp.size(); n++){
				if(pos.get(i).getCompanyID() == (comp.get(n).getID())){
					comp.get(n).setAmountOfShares(comp.get(n).getAmountOfShares() + pos.get(i).getAmountOfShares());
					pos.get(i).setAmountOfShares(0);
				}
			}
		}
	}
	
	/**
	 * Sets all player's attributes to default, except for the name
	 * @param plr Player whose attributes are set to default
	 */
	public void setPlayerToDefault(Player plr){
		plr.setMoney(0);
		plr.getDeposits().setAmount(0);
		plr.getLoan().setAmount(0);
		plr.getPossessions().clear();
		plr.setTotalWealth(0);
		plr.setBankruptcy(false);
		plr.setWinning(false);
		plr.setLosing(false);
		plr.setIngame(false);
	}
	
	/**
	 * Gathers information on each players's attributes that are relevant to game's end statistics
	 * to a single string.
	 */
	private void updatePlayerInfoString(){
		
		Vector<Player>players = game.getPlayers();
		playerInfoString = "";
		
		for(int i=0; i<players.size(); i++){	    		
    		playerInfoString += "#" + players.get(i).getName();    
    		playerInfoString += "#" + players.get(i).getMoney();  
    		playerInfoString += "#" + players.get(i).getDeposits().getAmount();  
    		playerInfoString += "#" + players.get(i).getLoan().getAmount();    
    		playerInfoString += "#" + logic.calculatePlayerStockValue(players.get(i));
    	}
	}
	
	/**
	 * @return Game object that is connected to this GameMessageHandler object
	 */
	public Game getGame(){
		return game;
	}
}

