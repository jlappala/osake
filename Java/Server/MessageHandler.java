import java.util.Vector;

/**
 * MessageHandler class defines methods for sending messages
 * to players. It also contains methods that are used by both of
 * it's subclasses
 */
public class MessageHandler {

	protected GameServer server;
	
	public MessageHandler(GameServer server){
		this.server = server;
	}	
	
	/**
	 * Checks if the parameter is an integer
	 * @param number The string to be tested
	 * @return True if the parameter is an integer
	 */
	protected boolean isInteger(String number){
		
		try {
		    Integer.parseInt(number);
		    return true;
		} catch(NumberFormatException nfe){
		    System.out.println("Number format exception");
			return false;
		}
	}	

	/**
	 * Tells server to close player's connection
	 * @param plr The player whose connection will be closed
	 */
	public synchronized void removeConnection(Player plr){
		
		server.removeConnection(plr);
	}	

	/**
	 * Sends a message to a specific player
	 * @param plr Player that will receive the message
	 * @param msg The message to be sent
	 */
	public void sendOnePlayer(Player plr, String msg){
		
		server.send(plr, msg);		
	}
	
	/**
	 * Sends a message to a group of players
	 * @param msg The message to be sent
	 * @param players Vector containing the receiving players
	 */
	public void sendAllPlayers(String msg, Vector<Player> players){
		
		if(!players.isEmpty()){
			for(int i=0; i<players.size(); i++){
				server.send(players.get(i), msg);
			}
		}
	}	
	
	/**
	 * Adds a server tag to the start of a string
	 * @param msg String to be modified
	 * @return String msg with added server tag
	 */
	public String serverMessage(String msg){
		
		String message = "SERVER#" + msg;		
		return message;
	}
}