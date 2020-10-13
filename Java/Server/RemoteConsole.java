import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class is work in progress. Add features as needed.
 * Currently user of this interface can only restart the server.
 * 
 * RemoteConsole class acts as a remote interface for server administrator. 
 * Administrator can connect to the RemoteConsole via socket listening to
 * a port defined by attribute REMOTE_ACCESS_PORT.
 */
public class RemoteConsole implements Runnable {

	private StockExchangeServer server;
	private Socket admin;
	private ServerSocket serverSocket = null;
	private final int REMOTE_ACCESS_PORT = 9100;

	
	public RemoteConsole(StockExchangeServer server){
		this.server = server;
	}
	
	/* 
	 * Run method of this thread. Listens to the socket and accepts connections
	 */
	public void run(){
		
		 openConsoleSocket();		
		 
		 while(true){
		     try{
		    	System.out.println("Awaiting remote admin connection..."); 
	    		admin = this.serverSocket.accept();    
	    		new ConsoleListener(admin).start();
		     }catch (IOException e) {
	                return;
		     }    
		 }
    }
	
	/**
	 * Closes a connection
	 * @param sc Socket connection to be closed
	 */
	private void close(Socket sc){
		try{
			sc.getInputStream().close();
			sc.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	    
    /**
     * Opens a ServerSocket to listen to new connections
     */
    private void openConsoleSocket(){
    	
        try{
            this.serverSocket = new ServerSocket(this.REMOTE_ACCESS_PORT);
        }catch (IOException e) {
            throw new RuntimeException("Cannot open port: " + REMOTE_ACCESS_PORT, e);
        }
    }   
    
    /**
     * Inner class instanced for all connections of the RemoteConsole
     */
    private class ConsoleListener extends Thread{
    	
    	private String fromAdmin = null;
    	private Socket adminSocket = null;
    	private BufferedReader input;
    	
    	public ConsoleListener(Socket sc){ 
    		
    		this.adminSocket = sc;
    		
            try {
				input = new BufferedReader(new InputStreamReader(adminSocket.getInputStream(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	/* 
    	 * Listens to messages from remote administrator
    	 */
    	public void run(){
    		System.out.println("Remote admin connected");
    		try{
   	    	 while (true){  
   	    		 
   				fromAdmin = input.readLine();
   			         
   	         	if(fromAdmin.equals("RESTART")){
   	         		server.restartServer();	     
   	         		break;
   	         	}
   	         }  	
   	    	 	close(adminSocket);
   	    	 	return;
    		 }catch(IOException e){
    			 System.err.println("Admin connection reset");
    	    	 close(adminSocket);
    	    	 return;
    	     }   	    
    	}    	
    }
}
