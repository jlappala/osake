

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.AbstractTableModel;

/**
 * Controller class implements action listener classes, takes user input from 
 * view and relays it to instance of MessageHandler class. Controller also updates
 * the view according to server messages processed by message handler.
 */
public class Controller{	
	
	private GameView mainView;
    private LobbyView lobby;
    private EndWindow endwindow;
    private BankView bank;
    private LobbyHelpWindow lobbyHelp;
    private GameHelpWindow gameHelp;
    private Dialogs dialog;
    private MessageHandler handler = null;
    private Vector<PlayerInfo>players;
    private String name = null;
    private OtherPlayersView others;
    private WorkMethods worker;
	
	public Controller(MessageHandler h){
		
        this.handler = h;
        this.worker = new WorkMethods();
        this.lobby = new LobbyView();
        this.mainView = new GameView();	
        this.bank = new BankView();
        this.dialog = new Dialogs();
        this.others = new OtherPlayersView();
        this.endwindow = new EndWindow(); 
        this.lobbyHelp = new LobbyHelpWindow();
        this.gameHelp = new GameHelpWindow();
            
        init();       
	}
	/**
	 * GUI launching method, opens lobby and asks for name
	 */	
	public void startGUI(){
		
        lobby.setVisible(true);		 
        name = dialog.showOptionDialog("Enter name, please", null, JOptionPane.OK_CANCEL_OPTION);
	    handler.sendMessage("NAME#" +name);
	}
	
	/**
	 * Initializes the view and adds event listeners.
	 */
	private void init(){
		
        mainView.setButtonListener(new MainViewButtonListener());	
        mainView.getMessageWriter().addActionListener(new MessageListener()); 

        lobby.getGameButton().addActionListener(new GameListener());
        lobby.setButtonListener(new LobbyButtonListener());
        lobby.getJoinListModel().clear();
        others.getJoinListModel1().clear();
         
        endwindow.setButtonListener(new EndWindowButtonListener());
        bank.setButtonListener(new BankButtonListener());
        mainView.setKeyListener(new MainViewKeyListener());
        
	}
	/**
	 * Various set methods that gather and insert data. Also methods for
     * clearing various textAreas for new games. 
	 */
	
	/**
	 * Sets an instance of MessageHandler class to this controller
	 * @param mh MessageHandler to be set
	 */
	public void setHandler(MessageHandler mh){
        this.handler = mh;
	}
	
	/**
	 * Appends a string to the chat area of the game view
	 * @param s Appended string
	 */
	public void addText(String s){		
        mainView.getMessageArea().append(s + "\n");		
        mainView.getMessageArea().setCaretPosition(mainView.getMessageArea().getDocument().getLength());
	}
	
    /**
     * Clears the game list in lobby view
     */
    public void clearGames(){		
        lobby.getJoinListModel().clear();
	}
        
    /**
     * Adds a game name to the game list in lobby view
     * @param s Name of the game
     */
    public void addGame(String s){		
        lobby.getJoinListModel().addElement(s);
        lobby.getGameArea().setSelectedIndex(lobby.getJoinListModel().getSize()-1);
	}
        
	/**
	 * Adds a player name to the name list in lobby view
	 * @param s Name of the player
	 */
	public void addName(String s){		
        lobby.getPlayerArea().append(s + "\n");
	}
        
    /**
     * Clears the name list in lobby view
     */
    public void clearNames(){		
        clearArea(lobby.getPlayerArea());
	}        
	
    /**
     * Clears an instance of JTextArea
     * @param area JTextArea to be cleared
     */
    private void clearArea(JTextArea area){
        area.selectAll();
        area.setText("");
    }
                
    /**
     * Sets a new value for the money field
     * @param s New money value
     */
    public void updateMoney(String s){		
        mainView.getMoneyArea().selectAll();
        mainView.getMoneyArea().setText(s);                
	}
    
    /**
     * Sets a new value for the wealth field
     * @param s New wealth value
     */
    public void updateWealth(String s){
        mainView.getTotalWealth().selectAll();
        mainView.getTotalWealth().setText(s);
    }
        
    /**
	 * Updates stocks for user.
     * @param column the column to be processed
     * @param row row to be processed
     * @param param Object to be processed.
	 */
    public void updateStock(Object param, int row, int column){     
		
            String value = (String) param;
		
            if(value.equals("0") && column > 3)
            	value = null;				
			int position = mainView.getStockTable().getSelectedRow();
	                
			if(position < 0)
                position = 0;
		
			mainView.getStockTable().setValueAt(value, row, column);
			((AbstractTableModel) mainView.getStockTable().getModel()).fireTableRowsUpdated(row, row);
	}	
	
	/**
	 * Clears the column of stock table containing stocks owned and average spent information
	 */
	public void clearPossessions(){
		
        TableModel mod = mainView.getStockTable().getModel();
		
		for(int i = 0; i<mod.getRowCount(); i++){
			updateStock("0", i, 4);
			updateStock("0", i, 5);
		}
	}
    
    /**
     * Clears the fields in the bank view
     */
    public void clearBank(){
        bank.getBalanceField().selectAll();
        bank.getLoanArea().selectAll();
        bank.getBalanceField().setText("");
        bank.getLoanArea().setText("");     
    }    
        
    /**
     * Sets new value for the deposits field
     * @param s New value of deposits
     */
    public void updateDeposit(String s){
    	bank.getBalanceField().selectAll();
        bank.getBalanceField().setText(s);                                 
    }   
    
    /**
     * Sets new value for the loan field 
     * @param s New value for loan
     */
    public void updateLoan(String s){
    	bank.getLoanArea().selectAll();
        bank.getLoanArea().setText(s);                                 
    }   
    
    /**
     * Clears the list of other players
     */
    public void clearOtherPlayers(){
        others.getJoinListModel1().clear();
    }    
	
    /**
     * Adds information to the list of other players
     * @param s Added information
     */
    public void updateStatus(String s){
        others.getJoinListModel1().addElement(s);
    }      
	
    /**
     * Clears the list in the end statistics window
     */
    public void clearEndWindow(){
        endwindow.getListModel().clear();
    }    
	
    /**
     * Adds information to the end statistics window
     * @param s Added information
     */
    public void updateEndWindow(String s){
        endwindow.getListModel().addElement(s);
    }   		   
        
    /**
     * Appends a string to the nasdaq info screen
     * @param s Added string
     */
    public void updateServerArea(String s){
        mainView.getStatusArea().append(s + "\n");
        mainView.getStatusArea().setCaretPosition(mainView.getStatusArea().getDocument().getLength());
    }    
    
    /**
     * Sets new values for the loan and deposit interest rates
     * @param dep Deposit interest rate
     * @param loan Loan interest rate
     */
    public void updateInterestRates(String dep, String loan){
    	bank.getDepIntField().selectAll();
    	bank.getDepIntField().setText(dep);
    	bank.getLoanIntField().selectAll();
    	bank.getLoanIntField().setText(loan);
    }
    
     /**
	 * Method that fires end window.
	 */
    public void showEndWindow(){   	
    	clearEndWindow();
    	
    	for(int i=0; i<players.size(); i++){
    		updateEndWindow(players.get(i).getName() + ": " + players.get(i).getTotalWealth());
    	}
    	endwindow.showGUI();
    }    
    
    /**
     * Sets the list of player information at the end of the game
     * @param plrs List of player information
     */
    public void setPlayers(Vector<PlayerInfo> plrs){
    	this.players = plrs;       
    }    
        
	/**
	 * Event listener listening to the chat text field. 
	 */
	private class MessageListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ent){
			
			String text = ent.getActionCommand();
			mainView.getMessageWriter().selectAll();
            mainView.getMessageWriter().setText("");

            if(text!=null)
				handler.sendMessage("SAY#"+text);			
		}	
	}
	
	/**
	 * Event listener listening to the text field, that takes name as an input.
	 */
	private class NameListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ent){
			
			String text = ent.getActionCommand();
			
			if(text!=null)
				handler.sendMessage("NAME#"+text);
		}	
	}
	/**
	 * Event listener listening to the list, that takes game name as an input.
	 */
    private class GameListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ent){
			
			String name = lobby.getGameField().getText();
                        int interval = lobby.getUpdateInterval();
			int money = lobby.getStartMoney();
			int value = lobby.getStartValue();
			int shares = lobby.getStartShares();
			int goalWealth = lobby.getStartMaximum();
			
			if(name.length() > 0)
                handler.sendMessage("CRGM#"+name+"#"+money+"#"+value+"#"+shares+"#"+interval+"#"+goalWealth);
			else
				dialog.showMessageDialog("You must enter a name for the game");
			
		}	
	}
    
    /**
	 * KeyListener for main view, that listens for shortcut keys. 
	 */    
    protected class MainViewKeyListener implements KeyListener{
       
       public boolean isCtrlPressed, isBPressed, isSPressed; 
        
        public void keyTyped(KeyEvent e) {
            //Do nothing.
        }
        
        public void keyPressed(KeyEvent e) {
            
           switch(e.getKeyCode()){
               case KeyEvent.VK_CONTROL: isCtrlPressed = true; break;
               case KeyEvent.VK_B: isBPressed = true; break;
               case KeyEvent.VK_S: isSPressed = true; break;
                  
           }
            checkBooleans();             
        }

        
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()){
            case KeyEvent.VK_CONTROL: isCtrlPressed = false; break;
            case KeyEvent.VK_B: isBPressed = false; break;
            case KeyEvent.VK_S: isSPressed = false; break;
            }
           
          checkBooleans();   
        }
		/**
		 * Method to check which controls are used and their actions.
		 */
        public void checkBooleans(){
            
            if(isCtrlPressed == true && isBPressed == true){
                isCtrlPressed = false;
                isBPressed = false;
                
                String amount;
				int param;
				Object[] selectedRow = mainView.getSelectedRow();	
				
				if(selectedRow[2] != null && (!selectedRow[2].equals("0"))){
					String label = ("Company: " + selectedRow[1].toString() + ", Price: " + selectedRow[3].toString());	
					
					amount = dialog.showOptionDialog(label, "Confirm purchase", JOptionPane.YES_NO_CANCEL_OPTION);
					param = worker.isNumber(amount);
					
					if(amount != null){
						if(param > 0)
							handler.buyStocks(selectedRow, param);
						else							
							dialog.showMessageDialog("Please enter positive number");
					}
				}else
					dialog.showMessageDialog("No stocks to buy");
                                
            } else if(isCtrlPressed == true && isSPressed==true){
                isCtrlPressed = false;
                isSPressed = false;
                
                String amount;
				int param;
				Object[] selectedRow = mainView.getSelectedRow();
				
				if(selectedRow[5] != null){
					String label = ("Company: " + selectedRow[1].toString() + ", Stocks owned: " + selectedRow[5].toString());	
					
					amount = dialog.showOptionDialog(label, "Confirm sell", JOptionPane.YES_NO_CANCEL_OPTION);
					param = worker.isNumber(amount);
					
					if(amount != null){
						if(param > 0)
							handler.sellStocks(selectedRow, param);
						else
							dialog.showMessageDialog("Please enter positive number");
					}
				}else
					dialog.showMessageDialog("No stocks to sell");			       
            }
        }        
     }        
    
	/**
	 * Event listener listening to the button events. Add this as an listener 
	 * to all buttons.
	 */
    private class MainViewButtonListener implements ActionListener{
    		
        public void actionPerformed(ActionEvent ent){
                
        	if(ent.getActionCommand().equals("Buy Stock")){
			
				String amount;
				int param;
				Object[] selectedRow = mainView.getSelectedRow();	
				
				if(selectedRow[2] != null && (!selectedRow[2].equals("0"))){
					String label = ("Company: " + selectedRow[1].toString() + ", Price: " + selectedRow[3].toString());	
					
					amount = dialog.showOptionDialog(label, "Confirm purchase", JOptionPane.YES_NO_CANCEL_OPTION);
					param = worker.isNumber(amount);
					
					if(amount != null){
						if(param > 0)
							handler.buyStocks(selectedRow, param);
						else							
							dialog.showMessageDialog("Please enter positive number");
					}
				}else
					dialog.showMessageDialog("No stocks to buy");
			}	
        	
        	else if(ent.getActionCommand().equals("Buy with all money")){
				
				String divider;
				int param;
				int selection;
				Object[] selectedRow = mainView.getSelectedRow();
				int stocksLeft = Integer.parseInt((String) selectedRow[2]);	
				divider = selectedRow[3].toString();
				
				if(selectedRow[3] != null && !divider.equals("0")){
										
					param = worker.divideInt(mainView.getMoneyArea().getText(), divider);
					if(param > stocksLeft)
						param = stocksLeft;
					String label = ("Buy " + param + " " + selectedRow[1].toString() + " stock(s)?");	
					selection = dialog.showOptionMessage(label, "Confirm action");

					if(selection == 0)
						handler.buyStocks(selectedRow, param);					
				}else
					dialog.showMessageDialog("No stocks to buy");
			}		
        	
        	else if(ent.getActionCommand().equals("Sell Stock")){
				
				String amount;
				int param;
				Object[] selectedRow = mainView.getSelectedRow();
				
				if(selectedRow[5] != null){
					String label = ("Company: " + selectedRow[1].toString() + ", Stocks owned: " + selectedRow[5].toString());	
					
					amount = dialog.showOptionDialog(label, "Confirm sell", JOptionPane.YES_NO_CANCEL_OPTION);
					param = worker.isNumber(amount);
					
					if(amount != null){
						if(param > 0)
							handler.sellStocks(selectedRow, param);
						else
							dialog.showMessageDialog("Please enter positive number");
					}
				}else
					dialog.showMessageDialog("No stocks to sell");
			}		
        	
        	else if(ent.getActionCommand().equals("Sell all stock")){
				
				int selection;
				int param;
				Object[] selectedRow = mainView.getSelectedRow();
				
				if(selectedRow[5] != null){
					
					param = Integer.parseInt(selectedRow[5].toString());
					String label = ("Sell " + param + " " + selectedRow[1].toString() + " stock(s)?");	
					selection = dialog.showOptionMessage(label, "Confirm action");

					if(selection == 0)
						handler.sellStocks(selectedRow, param);					
					}else
						dialog.showMessageDialog("No stocks to sell");
            }	
        	
        	else if(ent.getActionCommand().equals("Help")){
            	  gameHelp.setVisible(true);           
            }     
        	
            else if(ent.getActionCommand().equals("Other players")){
                 others.showGUI();
            }
        	
            else if(ent.getActionCommand().equals("Start game")){
            	handler.sendMessage("START");               
            }     
        	
            else if(ent.getActionCommand().equals("Quit game")){
            	exitGame();                     
            }     
        	
            else if(ent.getActionCommand().equals("Bank")){        	
            	bank.setVisible(true);
			}          
        }	
    }
    
    /**
	 * When exiting the game this method launches and asks for confirmation.
	 */
    private void exitGame(){
    	
    	int answer = dialog.showOptionMessage("Exit current game and return to lobby?", "Confirm action");
		if(answer == 0){
	    	handler.sendMessage("EXTGM");
	    	clearGameGui();
	    	mainView.setVisible(false);
	        others.setVisible(false);
	        bank.setVisible(false);   
	        endwindow.setVisible(false);
	        lobby.showGUI();
		}
    }
    
    private void clearGameGui(){
    	
    	int rows;
    	int cols;
    	DefaultTableModel mod = (DefaultTableModel) mainView.getStockTable().getModel();
    	
    	rows = mod.getRowCount();
    	cols = mod.getColumnCount();
    	
    	for(int r=0; r<rows; r++){
    		for(int c=0; c<cols; c++){
    			updateStock(" ", r, c);
    		}    		
    	}    	
    	
    	clearPossessions();
    	clearArea(mainView.getMessageArea());
    	clearArea(mainView.getTotalWealth());
    	clearArea(mainView.getStatusArea());    	
    }
    
    /**
	 * Bank view Buttonlistener. 
	 */
    private class BankButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent ent) {
			
			if(ent.getActionCommand().equals("Withdraw")){
				
	            String amount;
	            int param;
				
				amount = dialog.showOptionDialog("Withdraw money from bank", "Bank transaction", JOptionPane.YES_NO_CANCEL_OPTION);                        
				param = worker.isNumber(amount);
				
				if(amount != null){
					if(param > 0)
						handler.withdrawMoney(param);
					else							
						dialog.showMessageDialog("Please enter positive number");
				}									                                    
			}  
			
			else if(ent.getActionCommand().equals("Withdraw all")){
				
				int selection;
				int param;
				String balance = bank.getBalanceField().getText();
				
				param = worker.isNumber(balance);
				
				//It is assumed that bank account cant go to minus value.
				if(param != -2 && param != 0){
					
					String label = ("Withdraw " + balance + " euros?");	
					selection = dialog.showOptionMessage(label, "Confirm action");
					
					if(selection == 0)
						handler.withdrawMoney(param);				
				}else
					dialog.showMessageDialog("No money to withdraw");
			}	
			
			else if(ent.getActionCommand().equals("Deposit")){
				
	            String amount;
	            int param;		
	            				
	            amount = dialog.showOptionDialog("Deposit money to bank account", "Bank transaction", JOptionPane.YES_NO_CANCEL_OPTION);                        
	            param = worker.isNumber(amount);
	            
	            if(amount != null){
					if(param > 0)
						 handler.depositMoney(param);
					else
						dialog.showMessageDialog("Please enter positive number");                                         
	            }
	        }   
			
			else if(ent.getActionCommand().equals("Take loan")){
				
	            String amount;
	            int param;		
	            				
	            amount = dialog.showOptionDialog("Take Loan from bank", "Bank transaction", JOptionPane.YES_NO_CANCEL_OPTION);                        
	            param = worker.isNumber(amount);
	            
	            if(amount != null){
					if(param > 0)
						 handler.takeLoan(param);
					else
						dialog.showMessageDialog("Please enter positive number");                                         
	            }
            }        
			
			else if(ent.getActionCommand().equals("Pay loan")){
				
	            String amount;
	            int param;		
	            				
	            amount = dialog.showOptionDialog("Pay Loan", "Bank transaction", JOptionPane.YES_NO_CANCEL_OPTION);                        
	            param = worker.isNumber(amount);
	            
	            if(amount != null){
					if(param > 0)
						 handler.payLoan(param);
					else
						dialog.showMessageDialog("Please enter positive number");                                         
	            }
            }         
		}       	
    }
    
     /**
	 * Lobby view buttonlistener.
	 */
    private class LobbyButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent ent) {
			
			if(ent.getActionCommand().equals("Join game")){
				
				if(lobby.getGameArea().getModel().getSize() > 0){
					String name = (String)lobby.getGameArea().getSelectedValue();			
					
					mainView.setGameName(name);
					lobby.setVisible(false);
                    mainView.showGUI();
                    mainView.getStockTable().repaint();
	                
					if(!name.equals(null))
						handler.joinGame(name);
				}
			}     
			else if(ent.getActionCommand().equals("Quit game")){
				
				int  answer = dialog.showOptionMessage("Exit game?", "Confirm action");
				if(answer == 0){
					handler.sendMessage("DISCONNECT");
					handler.disconnect();
				}
			}
			else if(ent.getActionCommand().equals("Help")){
				
				lobbyHelp.setVisible(true);
				
			}     
		}       	
    }
    
    /**
	 * End view buttonlistener.
	 */
    private class EndWindowButtonListener implements ActionListener{
   
        public void actionPerformed(ActionEvent ent) {
            if(ent.getActionCommand().equals("Best overall")){
            
	            clearEndWindow();
	            worker.sortPlayers(players, 1);
	            for(int i=0; i<players.size(); i++){
	    		updateEndWindow(players.get(i).getName() + ": " + players.get(i).getTotalWealth());
            }
            
            }else if(ent.getActionCommand().equals("Most loans")){
            	
	             clearEndWindow();
	             worker.sortPlayers(players, 2);
	             for (int i=0; i<players.size(); i++){
	             updateEndWindow(players.get(i).getName() + ": " + (players.get(i).getLoans()));
            }
             
            }else if(ent.getActionCommand().equals("Most Savings")){

	            clearEndWindow();
	            worker.sortPlayers(players, 3);
                for (int i=0; i<players.size();i++){
                updateEndWindow(players.get(i).getName() + ": " + (players.get(i).getDeposits()));        
            }
                
            }else if(ent.getActionCommand().equals("Best stock value")){
   
                clearEndWindow();
                worker.sortPlayers(players, 4);
            	for (int i=0; i<players.size();i++){
                    updateEndWindow(players.get(i).getName() + ": " + players.get(i).getStockValue());        
            	}
                
            }else if(ent.getActionCommand().equals("Back to lobby")){
                clearOtherPlayers();
                clearBank();
            	exitGame();            	
            }                  
        }        
    }
}
