import java.net.Socket;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * StockTransactionLogic defines methods associated with stock sales and purchases.
 * It also has a timer updating stock values 
 */
public class StockTransactionLogic {
	
	private GameLogic logic;
	private GameMessageHandler handler;
	private int STOCK_DELAY = 5;
	private Timer stockTimer;
	
	/**
	 * Constructor initializes GameMessageHandler and GameLogic objects and
	 * sets the delay for stockTimer intervals
	 * @param hdl GameMessageHandler object of the game
	 * @param log GameLogic object 
	 * @param delay Delay for the stockTimer intervals
	 */
	public StockTransactionLogic(GameMessageHandler hdl, GameLogic log, int delay){
		this.handler = hdl;
		this.logic = log;
		this.STOCK_DELAY = delay;
		this.stockTimer = new Timer();
	}
	
	/**
	 * Handles player's stock purchase transaction
	 * @param player Player buying stocks
	 * @param comp ID number of the company whose stocks are bought
	 * @param price Price of the stock
	 * @param amountOfSharesToBuy Amount of shares being bought
	 */
	public void buyShares(Player player, int comp, int price, int amountOfSharesToBuy){
		
		Possession stocks;
		Company corp = logic.getCompanies().get(comp);
		int money = player.getMoney();
		int availableShares = corp.getAmountOfShares();
		int valueOfSingleShare = price;
		int cost = valueOfSingleShare * amountOfSharesToBuy;
		boolean done = false;
		Vector<Possession> sharesOwned = player.getPossessions();
		
		if(corp.isBankrupt()){
			handler.sendOnePlayer(player, handler.serverMessage("Cannot buy stocks. " + corp.getName() + " is bankrupt and its assests are frozen."));
			return;
		}
		
		//check if there is shares to buy
		if(availableShares - amountOfSharesToBuy < 0){
			handler.sendOnePlayer(player, handler.serverMessage("Not enough stocks"));
			return;
		}
		//check if player has the money to buy the shares
		if(money < valueOfSingleShare * amountOfSharesToBuy){
			handler.sendOnePlayer(player, handler.serverMessage("Not enough money"));
			return;
		}
		
		//removing the money from player
		int newAmountOfMoney = money - cost;
		player.setMoney(newAmountOfMoney);
		
		//removing the bought stocks from stock available
		int newAmountOfShares = availableShares - amountOfSharesToBuy;
		corp.setAmountOfShares(newAmountOfShares);
		
		//creating new Possession
		int cID = corp.getID();
		Socket pID = player.getID();
		
		//Check if player already owns these stocks and if so, add the amount of shares to that possession.
		for(int i = 0; i < sharesOwned.size();i++){
			Possession check = sharesOwned.get(i);
			
			/*if we can find possession with same company ID and player ID from global possession list,
			we have found similar possession. Then just add the amount of bought stocks to that Possession.*/
			if ( cID == check.getCompanyID() && pID.equals(check.getPlayerID()) ){
				int pShares = check.getAmountOfShares();
				int newAOS = pShares + amountOfSharesToBuy;		
				check.setAmountOfShares(newAOS);
				logic.setPossessionCost(check, price, amountOfSharesToBuy);
				done = true;
			}			
		}
		if(done == false){
			//If such existing possession could not be found, create a new possession and add it to proper places
			stocks = new Possession(cID, pID, amountOfSharesToBuy);
			stocks.setSpentValue(price);
			player.addPossession(stocks);
		}		
		stocks = logic.getPossessionByCompanyID(player, cID);
		handler.sendMoneyInfo(player);	
		handler.sendWealthInfo(player);
		handler.sendPossessionInfo(player, corp.getID(), stocks.getAmountOfShares(), stocks.getAverageValue());
		handler.sendSingleStockInfoToAll(corp);
		logic.checkIfAllSharesBought();
	}
	
	/**
	 * Handles the transaction of player selling stocks
	 * @param player Player selling the stocks
	 * @param comp ID of the company whose stocks are sold
	 * @param amountToSell Amount of stocks being sold
	 */
	public void sellShares(Player player, int comp, int amountToSell){

		Possession sell;
		Company corp = logic.getCompanies().get(comp);
		int cStockValue = corp.getValueOfShare();
		//get information needed to get the right possession so it can be modified (playerID and companyID)
		int cID = corp.getID();
		//find the possession we from wich we want to sell
		sell = logic.getPossessionByCompanyID(player, comp+1);
			//if the right possession is found
			if(sell != null){
				int pShares = sell.getAmountOfShares();
				if(pShares < amountToSell){
					handler.sendOnePlayer(player, handler.serverMessage("You don't have enough stocks!"));
					return;
					}
				if(pShares == amountToSell){
					//ie if the possessions amount of shares goes to 0, object will be removed from arraylists and deleted
					handler.sendPossessionInfo(player, cID, 0, 0);
					player.getPossessions().remove(sell);
					sell = null;
				}
				if(pShares > amountToSell){
					//Update the amount of shares in the possession object					
					sell.minusAmountOfShares(amountToSell);
					handler.sendPossessionInfo(player, cID, sell.getAmountOfShares(), sell.getAverageValue());
				}
				//give player money from selling stuff
				int moneyToAdd = cStockValue * amountToSell;
				player.addMoney(moneyToAdd);
				//update the availableShares variable in company object
				corp.addToAmountOfShares(amountToSell);
				//update changed information to clients												
				handler.sendMoneyInfo(player);
				handler.sendWealthInfo(player);
				handler.sendSingleStockInfoToAll(corp);
				return;
			}
		System.out.println("You don't own that stock");
		return;
	}	
	
	/**
	 * Update values of stocks with a timer that ticks with a delay 
	 * defined by STOCK_DELAY attribute. Updated information is then
	 * sent to all players. Also checks if players have gone bankrupt 
	 * or if winning conditions have been met.
	 */
	public void updateValueOfSharesTimer(){
		//Timer that updates values of shares
		int delay = STOCK_DELAY * 1000;
		int period= STOCK_DELAY * 1000;
		
		stockTimer.schedule(new TimerTask(){
			
			Random rand = new Random();
			int oldValueOfShare;
			int newValueOfShare;
			Vector<Company> comps = logic.getCompanies();
			
			public void run(){

				for (int i = 0; i < comps.size();i++){
					double updateNumber = rand.nextDouble() + 0.56;
					Company corporation = comps.get(i);
					oldValueOfShare = (int) corporation.getValueOfShare();					
				
					if(oldValueOfShare < 3){
						updateNumber += 0.3;
					}else if(oldValueOfShare < 5){
						updateNumber += 0.2;
					}else if(oldValueOfShare < 12)
						updateNumber += 0.1;
					
					if(oldValueOfShare > 150)
						updateNumber -= 0.4;
					else if(oldValueOfShare > 100)
						updateNumber -= 0.2;
					
					newValueOfShare = (int) (oldValueOfShare * updateNumber);
					corporation.setValueofShare(newValueOfShare);
					logic.checkCompanyBankruptcy(corporation);
				}
				handler.sendStocksInfoToAll();
				handler.sendWealthInfoToAll();
				handler.sendPossessionsValueToAll();
				logic.checkPlayerWealth();
				logic.checkBankruptcyForAllPlayers();	
			}
		},delay, period);			
	}	
	
	/**
	 * Stops the stockTimer
	 */
	public void stopStockTimer(){
		stockTimer.cancel();
	}
}
