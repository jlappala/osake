import java.util.Random;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * LobbyMessageHandler class contains methods that handle events in the game
 * lobby. Refer to the client -server interface documentation for more 
 * specific information on messages and their usage.
 */
public class LobbyMessageHandler extends MessageHandler {
	
	private Lobby lobby = null;
	
	public LobbyMessageHandler(GameServer s){
		super(s);
	}	
	
	/**
	 * Sets a Lobby object for this LobbyMessageHandler
	 * @param l Lobby object to be set
	 */
	public void setLobby(Lobby l){
		this.lobby = l;
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
        
       	if(cmd.equals("NAME")){
    		setPlayerName(items, plr);
    	}else if(cmd.equals("CRGM")){
    		createGame(items);
    	}else if(cmd.equals("JOGM")){
    		joinGame(items, plr);
    	}else if(cmd.equals("DISCONNECT")){
    		removePlayer(plr);
    	}else
    		return;
    }
	
	/**
	 * Calls Lobby object to create a new game with specific attributes
	 * @param items Array containing the game settings
	 */
	private void createGame(String[] items){
		
		String name = items[1];
		int[] settings = new int[5];		
		boolean ok = true;
		
		for(int i=2; i<items.length; i++){
			if(isInteger(items[i]))
				settings[i-2] = Integer.parseInt(items[i]);			
			else{
				ok = false;
				break;
			}			
		}
		if(ok == true )
			lobby.createGame(name, settings);		
	}
	
	/**
	 * Tries to add a player to a game defined by it's name
	 * @param items Array containing the game name
	 * @param plr Player to be added to the game
	 */
	public void joinGame(String[] items, Player plr){
		Vector<Game> games = lobby.getGames();
		String gameName = items[1];
		
		if(!games.isEmpty()){
			for(int i=0; i<games.size(); i++){
				if(games.get(i).getName().equals(gameName)){
					lobby.addPlayerToGame(games.get(i), plr);
					return;
				}
			}
		}
		sendOnePlayer(plr, serverMessage("Game not found"));
	}
	
	/**
	 * Delegates player removal to Lobby and MessageHandler objects
	 * @param plr Player to be removed from the server
	 */
	public void removePlayer(Player plr){
		
		lobby.removePlayerFromLobby(plr);
		lobby.updateNames();
		sendNameInfoToAll();
		removeConnection(plr);
	}	

	/**
	 * Sets a name for a player. If given name is null, a substitute name is given instead
	 * @param currentMsg Array containing the new name
	 * @param plr Player getting the name
	 */
	public void setPlayerName(String[] currentMsg, Player plr){
		
		String name = plr.getName();
		Random rand = new Random();
		String substitute = "Player"+rand.nextInt(99);
		
		if(currentMsg.length>1){
			if(name == null){
				if(currentMsg[1].equals("null"))
					plr.setName(substitute);					
				else
					plr.setName(currentMsg[1]);
			}else{
				sendAllPlayers(serverMessage(name+" is now " + currentMsg[1]+"!"), lobby.getLobbyPlayers());
				name = currentMsg[1];	
				plr.setName(name);
			}
		}else{
			plr.setName(substitute);
		}
			
		lobby.updateNames();
    	sendNameInfoToAll();
	}
	
	/**
	 * Sends a player a message containing all the names of players in the lobby
	 * @param plr Player receiving the message
	 */
	public void sendNameInfo(Player plr){
		
		String message = "UDNM" + lobby.getNameString();
		sendOnePlayer(plr, message);
	}
	
	/**
	 * Sends all players in the lobby a message containing all the names of players in the same game 
	 */
	public void sendNameInfoToAll(){
		
		Vector<Player> players = lobby.getLobbyPlayers();	
		String message = "UDNM" + lobby.getNameString();
		
		if(!players.isEmpty()){
	    	for(int i=0; i<players.size(); i++){	    		
	    		sendOnePlayer(players.get(i), message);	    		
	    	}
		}
	}
	
	/**
	 * Sends a player a message containing all the names of games in the lobby
	 * @param plr Player receiving the message
	 */
	public void sendGameInfo(Player plr){
		
		String message = "UDGM" + lobby.getGameString();
		sendOnePlayer(plr, message);
	}

	/**
	 * Sends all players in the lobby a message containing all the names of games in the lobby
	 */
	public void sendGameInfoToAll(){
		
		Vector<Player> players = lobby.getLobbyPlayers();	
		String message = "UDGM" + lobby.getGameString();
		
		if(!players.isEmpty()){
	    	for(int i=0; i<players.size(); i++){	    		
	    		sendOnePlayer(players.get(i), message);	    		
	    	}
		}
	}
}
