import java.io.*;
import java.net.Socket;

/**
 * MessageListener class works in a thread listening to a socket for messages
 * and sends them to the Player object this object is property of
 */
public class MessageListener extends Thread{

    private Socket serverSocket = null;
    private String fromClient = "";
    private BufferedReader input;
    private Player player;
    
    public MessageListener(Player p) {  
        this.player = p;
        this.serverSocket = player.getID();
    }

    /**
     * Run method of this thread
     * Listens to a client socket and sends received messages to the Player object
     */
    public void run() {
       
        try {        	
        	input = new BufferedReader(new InputStreamReader(serverSocket.getInputStream(), "UTF-8"));
        	        	
            while(true){  
            	           	
            	fromClient = input.readLine();     
            	
            	if(fromClient.equals("DISCONNECT")){
            		player.relayMsg(fromClient);
            		return;
            	}
            	
            	if(fromClient != null){
            		player.relayMsg(fromClient);
            	}
            }      
            
        } catch (IOException e) {
        	System.err.println("Connection dead from " + serverSocket.getInetAddress());
        	player.relayMsg("DISCONNECT");
        	return;
        }
    }
}