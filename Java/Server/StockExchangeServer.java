
/**
 * Main class of this program. Creates a new GameServer object
 * and a new RemoteConsole object and starts their threads.
 */
public class StockExchangeServer {

	private GameServer currentServer;
	private RemoteConsole console;
	private final int SERVER_PORT = 9000;
	
	public StockExchangeServer(){
				
		currentServer = new GameServer(SERVER_PORT);
		new Thread(currentServer).start();
		
		console = new RemoteConsole(this);		
		new Thread(console).start();
	}
	
	/**
	 * Stops the server and creates a new one in it's place
	 */
	public void restartServer(){
		currentServer.stopServer();
		currentServer = new GameServer(SERVER_PORT);
		new Thread(currentServer).start();
	}
	
	/**
	 * Main method of this program. Creates an instance of StockExchangeServer
	 */
	public static void main(String[] args) {
		new StockExchangeServer();
	}
}
