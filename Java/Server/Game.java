import java.util.Vector;

/**
 * All the games in the server are instances of this class. 
 * Game class manages a list of players in the game and has
 * it's own MessageHandler object for processing player messages,
 * and a GameLogic object that is configured according to game settings.
 */
public class Game{
	
	private Vector<Player> players;
	private GameMessageHandler handler;
	private Lobby lobby;
	private GameLogic logic;
	private String gameName;
	private String playerNames;
	private int startMoney = 0;
	private boolean gameInProgress = false;
	
	public Game(Lobby lobby, GameMessageHandler m, String name, int[] settings){
		this.gameName = name;
		this.handler = m;
		this.logic = new GameLogic(handler, settings);
		this.lobby = lobby;
		this.players = new Vector<Player>();
		this.startMoney = logic.getStartMoney();
		handler.setGame(this);		
	}
		
	/**
	 * Adds a player to a game. Player list and a string containing names of
	 * players in the game is updated, and sent to all players in the same game.  
	 * @param plr Player to be added
	 */
	public void addPlayer(Player plr){
		
		synchronized(players){
			plr.setGame(this);
			plr.setIngame(true);
			players.add(plr);
			plr.setMoney(startMoney);
		}		
		handler.sendAllPlayers(handler.serverMessage(plr.getName() + " has joined the game"), players);
		handler.sendMoneyInfo(plr);
		
		updateNames();
		handler.sendPlayersNameInfoToAll();
		
		if(gameInProgress == true)
			handler.sendStocksInfoToAll();		
	}
	
	/**
	 * Removes a player from the game. Player list is updated and notification
	 * of player removal is sent to other players in the game. Player's attributes
	 * are set back to default. If removed player is last in the game, game will be 
	 * shut down also. 
	 * @param plr Player to be removed
	 */
	public void removePlayer(Player plr){
				
		String name = null;
		
		synchronized(players){
			
			for(int i=0; i<players.size(); i++){
				if(players.get(i).equals(plr)){			
					name = players.get(i).getName();
					if(gameInProgress == true)
						handler.returnPlayerStocksToMarket(plr);	
					handler.setPlayerToDefault(plr);
					players.get(i).exitGame();
					players.remove(i);
				}
	    	}
		}
		if(players.isEmpty()){
			handler.endGame();
			lobby.removeGame(this);
		}
		else{
			handler.sendAllPlayers(handler.serverMessage(name + " has left the game"), players);
			updateNames();
			handler.sendPlayersNameInfoToAll();
		}
	}
	
	/**
	 * Relays a message from a player to the GameMessageHandler object
	 * @param msg Relayed message
	 * @param plr Player sending the message
	 */
	public void processMessage(String msg, Player plr){
		handler.processMessage(msg, plr);
	}
	
	/**
     * Combines all the names of the players in the current game into a single string,
     * separated by a '#' character
     */
    public void updateNames(){
    	
    	playerNames = "";
    	
    	if(!players.isEmpty()){
	    	for(int i=0; i<players.size(); i++){
	    		playerNames += ('#' + players.get(i).getName());
	    	}
    	}
    }    
    
	/**
	 * Sets a boolean representing whether the game is in 
	 * progress or not
 	 * @param b In progress status
	 */
	public void setGameInProgress(boolean b){
		gameInProgress = b;
	}
	
	/**
	 * Sets a name for the game
	 * @param name Game name
	 */
	public void setName(String name){
		this.gameName = name;		
	}
	
	/**
	 * @return Name of the game
	 */
	public String getName(){
		return gameName;
	}
	
	/**
	 * @return String containing all player names
	 */
	public String getPlayerNames(){
		return playerNames;
	}
	
	/**
	 * @return List of players in the game
	 */
	public Vector<Player> getPlayers(){
		return players;
	}
	
	/**
	 * @return GameLogic object of the game
	 */
	public GameLogic getGameLogic(){
		return logic;
	}
	
	/**
	 * @return In progress status of the game
	 */
	public boolean inProgress(){
		return gameInProgress;
	}
}