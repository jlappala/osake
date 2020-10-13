import java.io.Writer;
import java.net.Socket;
import java.util.Vector;

/**
 * All players in the game are instances of this class. Player
 * has a MessageListener object that listens to messages from 
 * a client associated with the player object. This class also
 * encapsulates player attributes such as name, money, deposits
 * and loan.
 */
public class Player {
	
	private Socket playerID;
	private Writer output;
	
	private String name;
	private int money;
	private int totalWealth;
	private boolean inGame = false;
	private boolean bankrupt = false;
	private boolean winning = false;
	private boolean losing = false;
	
	private Game currentGame = null;
	private Lobby lobby;
	private MessageListener listener;
	
	private Vector<Possession> possessions;
	private Loan playerLoan;
	private Deposit playerDeposit;
	
	public Player(Socket pID, Writer output, Lobby lobby){
		this.playerID = pID;
		this.output = output;
		this.possessions = new Vector<Possession>();
		this.lobby = lobby;
		this.playerLoan = new Loan(playerID);
		this.playerDeposit = new Deposit(playerID);
		this.listener = new MessageListener(this);
		this.bankrupt = false;
		listener.start();
	}
	
	/**
	 * @return Socket that acts as an unique ID of the player
	 */
	public Socket getID(){
		return playerID;
	}
	
	/**
	 * Adds a new possession of stocks to the list of possessions
	 * @param newPossession Added possession
	 */
	public void addPossession(Possession newPossession){
		this.possessions.add(newPossession);
	}
	
	/**
	 * @return List of possessions the player has
	 */
	public Vector<Possession> getPossessions(){
		return this.possessions;
	}
	
	/**
	 * @return Amount of cash the player has
	 */
	public int getMoney(){
		return this.money;
	}
	
	/**
	 * @return Name of the player
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * @return Loan object the player owns
	 */
	public Loan getLoan(){
		return this.playerLoan;
	}
	
	/**
	 * @return Deposit object the player owns
	 */
	public Deposit getDeposits(){
		return this.playerDeposit;
	}
	
	/**
	 * Sets new amount of cash
	 * @param m New amount of cash
	 */
	public void setMoney(int m){
		this.money = m;
	}
	
	/**
	 * Sets new amount of total wealth player owns
	 * @param wealth New amount of player wealth
	 */
	public void setTotalWealth(int wealth){
		this.totalWealth = wealth;
	}
	
	/**
	 * Sets a name for the player
	 * @param n Name of the player
	 */
	public void setName(String n){
		this.name = n;
	}
	
	/**
	 * Sets attributes indicating player game participation
	 * to a state of non participation
	 */
	public void exitGame(){
		setGame(null);
		setIngame(false);
	}
	
	/**
	 * Sets status of boolean indicating whether the player
	 * is in game or not.
	 * @param b Boolean indicating game participation
	 */
	public void setIngame(boolean b){
		this.inGame = b;
	}
	
	/**
	 * Sets a game that player has joined in to be associated with the player
	 * @param g Game player has joined in
	 */
	public void setGame(Game g){
		this.currentGame = g;
	}
	
	/**
	 * Sets status of boolean indicating whether player is in bankruptcy
	 * or not
	 * @param bankrupt Boolean indicating player bankruptcy
	 */
	public void setBankruptcy(boolean bankrupt){
		this.bankrupt = bankrupt;
	}
	
	/**
	 * Sets status of boolean indicating whether player is winning or not
	 * @param winning Boolean indicating whether player is winning
	 */
	public void setWinning(boolean winning){
		this.winning = winning;
	}
	
	/**
	 * Sets status of boolean indicating whether player is losing or not
	 * @param losing Boolean indicating whether player is losing
	 */
	public void setLosing(boolean losing){
		this.losing = losing;
	}
	
	/**
	 * Relays message to the game player is in. If player is not currently
	 * participating in any game, message is relayed to the lobby.
	 * @param msg Message to be relayed
	 */
	public void relayMsg(String msg){
		if(!inGame)
			lobby.processMessage(msg, this);
		else
			currentGame.processMessage(msg, this);
	}
	
	/**
	 * Adds value to the current amount of cash
	 * @param nMoney Value to be added
	 */
	public void addMoney(int nMoney){
		this.money = money + nMoney;
	}
	
	/**
	 * Subtracts value from the current amount of cash
	 * @param mMoney Value to be subtracted
	 */
	public void minusMoney(int mMoney){
		this.money -= mMoney;
	}
	
	/**
	 * @return Writer object of the player socket
	 */
	public Writer getOutput(){
		return this.output;
	}
	
	/**
	 * @return Total wealth player has amassed
	 * including cash (money), deposits and value of stocks
	 * minus value of loan.
	 */
	public int getTotalWealth(){
		return this.totalWealth;
	}
	
	/**
	 * @return Game player is currently participating in
	 */
	public Game getGame(){
		return currentGame;
	}
	
	/**
	 * @return Boolean status indicating if player is bankrupt
	 */
	public boolean isBankrupt(){
		return this.bankrupt;
	}
	
	/**
	 * @return Boolean status indicating if player is winning
	 */
	public boolean isWinnning(){
		return this.winning;
	}
	
	/**
	 * @return Boolean status indicating if player is losing
	 */
	public boolean isLosing(){
		return this.losing;
	}
}
