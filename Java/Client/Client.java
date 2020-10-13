import java.io.*;
import java.net.*;

/**
 * Main class of this program. Creates a GUI in an
 * event dispatching thread and tries to connect to the server
 * via a socket defined by serverIP and serverPort attributes.
 * Outgoing messages are sent with this class.
 */
public class Client{	

	private Controller controller;	
	private MessageHandler handler;
	private BufferedReader in;
	private Writer op;
	private final String serverIP = "80.223.253.248";
	private final int serverPort = 9000;
	protected Socket clientSocket = null;
	protected SocketListener ml;
    
    public Client() throws IOException{
    	    
	    try {
	        clientSocket = new Socket(serverIP, serverPort);
	    	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));	        
	    	op = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
	    } catch (UnknownHostException e) {
	        System.err.println("Cannot find server");
	        System.exit(1);
	    } catch (IOException e) {
	        System.err.println("Failed establishing I/O connection.");
	        System.exit(1);
	    }	    
            handler = new MessageHandler(this);
            controller = new Controller(handler);
            handler.setController(controller);
            	
	    ml = new SocketListener(in, handler);
	    ml.start();
	    	             
	 //GUI is being started in an event dispatching thread
     javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
				try {
					showGUI();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });		        
    }   	    
    
	/**
	 * Sends a message to the server
	 * @param msg Message sent
	 */
	public void send(String msg){
		try{
			op.write(msg+"\n");			
			op.flush();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Tries to disconnect from server and close socket connection
	 * @throws IOException
	 */
	public void disconnect() throws IOException{	
		in.close();
		op.close();
		clientSocket.close();
		ml.done = true;
		System.exit(0);
	}	
	
	/**
	 * Starts the GUI
	 */
	private void showGUI() throws IOException{
		controller.startGUI(); 
	}
	
	/**
	 * Creates a new instance of the Client class
	 */
	public static void main(String[] args)  throws IOException {		
		new Client();		
	}
}