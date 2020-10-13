import java.util.Vector;

/**
 * Lobby class contains methods that execute functions in the game lobby, 
 * such as creating a new game, adding player to a game and removing a game.
 * Lobby contains lists of players in the lobby and games in the lobby.
 * It also has an instance of LobbyMessageHandler to process messages from 
 * players in the lobby.
 */
public class Lobby {
	private Vector<Player> players;
	private Vector<Game> games;
	private GameServer server;
	private LobbyMessageHandler handler;
	private String gameString;
	private String nameString;
	
	public Lobby(GameServer server){
		this.server = server;
		this.handler = new LobbyMessageHandler(server);
		this.players = new Vector<Player>();
		this.games = new Vector<Game>();
		handler.setLobby(this);
	}
	
	/**
	 * Adds a player to the game lobby and sends all players 
	 * updated name list of players currently in lobby.
	 * @param plr Player to be added to the lobby
	 */
	public void addPlayerToLobby(Player plr){	
		
		players.add(plr);	
		updateNames();
		handler.sendNameInfoToAll();	
		handler.sendGameInfo(plr);
	}
	
	/**
	 * Removes a player from the game lobby and sends all players 
	 * updated name list of players currently in lobby.
	 * @param plr Player to be removed
	 */
	public void removePlayerFromLobby(Player plr){
		
		for(int i=0; i<players.size(); i++){
			if(players.get(i).equals(plr))
				players.remove(i);		
		}				
		updateNames();
	    handler.sendNameInfoToAll();	
	}
	
	/**
	 * Removes a player from a game or a lobby depending which one he/she is in 
	 * @param plr Player to be removed
	 */
	public void disconnectPlayer(Player plr){
		
		if(plr.getGame() == null)
			removePlayerFromLobby(plr);
		else
			plr.getGame().removePlayer(plr);
	}
	
	/**
	 * Adds player to a game and removes him/her from game lobby and sends all players 
	 * updated name list of players currently in lobby.
	 * @param gm Game player is added to
	 * @param plr Player to be added
	 */
	public void addPlayerToGame(Game gm, Player plr){
		
		gm.addPlayer(plr);
		removePlayerFromLobby(plr);
		updateNames();
    	handler.sendNameInfoToAll();
	}
	
	/**
	 * Adds a new game to the lobby and sends all players an updated list of games
	 * @param gameName Name of the game to be created
	 * @param settings Game settings for the new game
	 */
	public void createGame(String gameName, int[] settings){
		
		games.add(new Game(this, new GameMessageHandler(this.server), gameName, settings));	
		updateGames();
    	handler.sendGameInfoToAll();		
	}
		
	/**
	 * Removes a game from the lobby and sends all players an updated list of games
	 * @param gm Game to be removed
	 */
	public void removeGame(Game gm){
				
		for(int i=0; i<games.size(); i++){
			if(games.get(i).equals(gm))
				games.remove(i);
		}
		
		updateGames();
    	handler.sendGameInfoToAll();
	}
	
	 /**
     * Combines all the names of players in the lobby to a single string
     */
    public synchronized void updateNames(){
    	    	
    	nameString = "";
    	
    	if(!players.isEmpty()){
	    	for(int i=0; i<players.size(); i++){
	    		String name = players.get(i).getName();
	    		nameString += ('#' + name);
	    	}
    	}
    }
    
	/**
	 * Combines all the names of games in the lobby to a single string
	 */
	public synchronized void updateGames(){
		    	
		gameString = "";
		
    	if(!games.isEmpty()){
	    	for(int i=0; i<games.size(); i++){
	    		gameString += ('#' + games.get(i).getName());
	    	}
    	}
	}
    	
	/**
	 * Relays a message from a player to the LobbyMessageHandler object
	 * @param msg Message to be relayed
	 * @param plr Player sending the message
	 */
	public void processMessage(String msg, Player plr){
		handler.processMessage(msg, plr);
	}
	
	/**
	 * @return List of games in the lobby
	 */
	public Vector<Game> getGames(){
		return games;
	}
	
	/**
	 * @return List of players in the lobby
	 */
	public Vector<Player> getLobbyPlayers(){
		return players;
	}
	
    
    /**
     * @return String containing all names of games in lobby
     */
    public String getGameString(){
    	return gameString;
    }
    
    /**
     * @return String containing all names of players in lobby
     */
    public String getNameString(){
    	return nameString;
    }
}
