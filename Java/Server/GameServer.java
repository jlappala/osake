
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

/**
 * GameServer class listens to new client connections 
 * and handles sending of messages and disconnecting of players
 */
public class GameServer implements Runnable{

	private int serverPort;
	private ServerSocket serverSocket = null;
	private boolean isStopped = false;
	private Lobby gameLobby;

    public GameServer(int port){
        this.serverPort = port;
        this.gameLobby = new Lobby(this);
    }

    /**
     * Run method of this thread. Listens to new client connections  
     */
    public void run(){
        synchronized(this){
            Thread.currentThread();
        }
        openServerSocket();
        while(! isStopped()){           
        	System.out.println("Awaiting connection...");
            addClient();
        }
        System.out.println("Server Stopped.") ;
    }

    /**
     * Accepts a new connection from a client and creates a player object for it.
     * The new player is then added to the game lobby.
     */
    private void addClient(){    
    	
    	Socket newClient = null;
    	Writer output;
    	
    	try{
    		newClient = this.serverSocket.accept();
            output = new BufferedWriter(new OutputStreamWriter(newClient.getOutputStream(), "UTF-8"));
        	gameLobby.addPlayerToLobby(new Player(newClient, output, gameLobby));
        	            
        }catch (IOException e) {
            if(isStopped()) {
                System.out.println("Server Stopped.") ;
                return;
            }
            throw new RuntimeException(
                "Error accepting client connection", e);
        }                 
        System.out.println("Accepted connection from " + newClient.getInetAddress());          
    }
                   
    /**
     * Sends a message to a specific player
     * @param plr Player receiving the message
     * @param s String holding the message
     */
    public void send(Player plr, String s){
    	
    	Writer output = plr.getOutput();
    	
		try {
			output.write(s + "\n");
			output.flush();
		} catch( IOException ie ) { 
			System.out.println("Error sending to "+plr.getID().getInetAddress()); 
			removeConnection(plr);
		} catch(NullPointerException npc){
			System.out.println("Error sending to "+plr.getID().getInetAddress());
			removeConnection(plr);
		} 		
    }
    
    /**
     * Closes a player connection
     * @param plr Player whose connection is being closed
     */
    public void removeConnection(Player plr){
    	
    	Socket sc = plr.getID();    	
    	
    	try {
    		gameLobby.disconnectPlayer(plr);
    		plr.getOutput().close();
    		sc.close();
    		System.out.println("Removed connection from "+sc.getInetAddress());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error closing "+sc.getInetAddress());
		} catch (NullPointerException e){
			e.printStackTrace();
			System.err.println("Connection already closed");
		}		  	    	
    }
            
    /**
     * Opens socket for the server
     */
    private void openServerSocket(){
        try{
            this.serverSocket = new ServerSocket(this.serverPort);
        }catch (IOException e) {
            throw new RuntimeException("Cannot open port " + serverPort , e);
        }
    }    
    
    /**
     * @return Boolean isStopped representing server status
     */
    private synchronized boolean isStopped(){
        return this.isStopped;
    }

    /**
     * Closes serverSocket, removes players and games, and stops the server
     */
    public synchronized void stopServer(){
        this.isStopped = true;
        
        if(serverSocket != null){
	        try{
	            
	            for(int i=0; i<gameLobby.getGames().size(); i++){
	            	gameLobby.removeGame(gameLobby.getGames().get(i));
	            }
	            
	            for(int i=0; i<gameLobby.getLobbyPlayers().size(); i++){
	            	gameLobby.removePlayerFromLobby(gameLobby.getLobbyPlayers().get(i));
	            }
	            
	            this.serverSocket.close();
	            
	        }catch (IOException e) {
	            throw new RuntimeException("Error closing server", e);
	        }
        }      
    }
    
    /**
     * @return The game lobby
     */
    public Lobby getLobby(){
    	return gameLobby;
    }
}