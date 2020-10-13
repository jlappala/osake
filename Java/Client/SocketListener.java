import java.io.*;

/**
 * SocketListener listens for messages from the server.
 * It runs in it's own thread. Received messages are 
 * relayed to the MessageHandler object for further processing.
 */
public class SocketListener extends Thread{
	
	private String fromServer = null;
	private BufferedReader input;
	public boolean done = false;
	private MessageHandler handler;
	
	public SocketListener(BufferedReader in, MessageHandler m){
		
		this.handler = m;
		this.input = in;
	}

	/* 
	 * Run method of this thread. Listens to messages from the server and relays
	 * them to a MessageHandler object.
	 */
	public void run() {
		
		try {
			while(done == false){  				
				fromServer = input.readLine();	
				
            	if(fromServer != null){
            		handler. processMessage(fromServer);
            	}
			}	
		
		} catch (IOException ioe) {			
			System.out.println("Connection to server closed");
			return;
		}
		return;
	}	
}
