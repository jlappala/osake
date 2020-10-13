

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class LobbyView extends JFrame{    
    
    // Variables declaration
    private javax.swing.JLabel lobbyHeader;
    private javax.swing.JButton helpButton;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel lblgameEnd;
    private javax.swing.JSpinner jcbStockMarket1;
    private javax.swing.JButton btnCreateGame;
    private javax.swing.JButton btnJoinGame;
    private javax.swing.JButton btnQuitGame;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jcbNoofPlayer;
    private javax.swing.JSpinner jcbNoofPlayer1;
    private javax.swing.JSpinner jcbStockMarket;
    private javax.swing.JSpinner jcbStpckValue;
    private javax.swing.JList joinGameArea;
    private javax.swing.JLabel lblGameName;
    private javax.swing.JLabel lblJoinGame;
    private javax.swing.JLabel lblNoofPlayer;
    private javax.swing.JLabel lblStartFunds;
    private javax.swing.JLabel lblStartGame;
    private javax.swing.JLabel lblStockMarket;
    private javax.swing.JLabel lblStockValue;
    private javax.swing.JPanel pnlJoinGame;
    private javax.swing.JPanel pnlQuit;
    private javax.swing.JPanel pnlStartGame;
    private javax.swing.JTextArea txaPlayerList;
    private javax.swing.JTextField txtGameName;
    private ActionListener buttonListener;
    private DefaultListModel listModel;
    // End of variables declaration
    
    

    
    public LobbyView(){
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new CloseOperationListener());
    }
    
	public void showGUI(){
		
            pack();
            setVisible(true);
	}
    
        /**
	 * Buttonlistener for window.
	 */
    public void setButtonListener(ActionListener bl){
        this.buttonListener = bl;
        btnJoinGame.addActionListener(buttonListener);
        btnQuitGame.addActionListener(buttonListener);
        helpButton.addActionListener(buttonListener);
	}
    
    /*
     * This method is called from within the constructor to initialize the form.  
     */
  private void initComponents() {
	  	  
	jDialog1 = new javax.swing.JDialog();
      	lobbyHeader = new javax.swing.JLabel();
      	helpButton = new javax.swing.JButton();
        jcbStockMarket1 = new javax.swing.JSpinner();
        lblgameEnd = new javax.swing.JLabel();
        listModel = new DefaultListModel();
        pnlStartGame = new javax.swing.JPanel();
        lblStartGame = new javax.swing.JLabel();
        btnCreateGame = new javax.swing.JButton();
        lblNoofPlayer = new javax.swing.JLabel();
        lblStartFunds = new javax.swing.JLabel();
        lblStockValue = new javax.swing.JLabel();
        lblStockMarket = new javax.swing.JLabel();
        lblGameName = new javax.swing.JLabel();
        txtGameName = new javax.swing.JTextField();
        jcbNoofPlayer = new javax.swing.JSpinner();
        jcbNoofPlayer1 = new javax.swing.JSpinner();
        jcbStpckValue = new javax.swing.JSpinner();
        jcbStockMarket = new javax.swing.JSpinner();
        pnlJoinGame = new javax.swing.JPanel();
        lblJoinGame = new javax.swing.JLabel();
        btnJoinGame = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        joinGameArea = new javax.swing.JList(listModel);
        pnlQuit = new javax.swing.JPanel();
        btnQuitGame = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaPlayerList = new javax.swing.JTextArea();
        
        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        
        pnlStartGame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblStartGame.setFont(new java.awt.Font("Arial", 1, 14));
        lblStartGame.setText("Start a new game");

        btnCreateGame.setText("Create game");

        lblNoofPlayer.setText("Update interval");

        lblStartFunds.setText("Starting funds:");

        lblStockValue.setText("Stock startvalue:");

        lblStockMarket.setText("Stock on market:");

        lblGameName.setText("Game name:");
        
        
        /**
	 * Adds icons for various components.
	 */
         try {
            
            Image join = ImageIO.read(getClass().getResource("icons/start.png"));
            btnJoinGame.setIcon(new ImageIcon(join));
            btnCreateGame.setIcon(new ImageIcon(join));
            
            Image quit = ImageIO.read(getClass().getResource("icons/action_stop.gif"));
            btnQuitGame.setIcon(new ImageIcon(quit));
            
            Image help = ImageIO.read(getClass().getResource("icons/icon_info.gif"));
            helpButton.setIcon(new ImageIcon(help));
            
            Image stockexchanger = ImageIO.read(getClass().getResource("icons/stockexchanger.png"));
            setIconImage(stockexchanger);
            
        } catch (IOException ex) {     
        }

        jcbNoofPlayer.setModel(new javax.swing.SpinnerNumberModel(6, 5, 60, 1));

        jcbNoofPlayer1.setModel(new javax.swing.SpinnerNumberModel(500, 1, null, 10));

        jcbStpckValue.setModel(new javax.swing.SpinnerNumberModel(50, 1, 150, 1));

        jcbStockMarket.setModel(new javax.swing.SpinnerNumberModel(2000, 1, null, 10));
        
        lblgameEnd.setText("Game ending value:");
        
        jcbStockMarket1.setModel(new javax.swing.SpinnerNumberModel(150000, 1, null, 1000));
        
        setTitle("Stock Exchanger - Game Lobby");

        javax.swing.GroupLayout pnlStartGameLayout = new javax.swing.GroupLayout(pnlStartGame);
        pnlStartGame.setLayout(pnlStartGameLayout);
        pnlStartGameLayout.setHorizontalGroup(
            pnlStartGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStartGameLayout.createSequentialGroup()
                .addGroup(pnlStartGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStartGameLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btnCreateGame))
                    .addGroup(pnlStartGameLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(lblStartGame))
                    .addGroup(pnlStartGameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlStartGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNoofPlayer)
                            .addComponent(lblGameName)
                            .addComponent(lblStartFunds)
                            .addComponent(lblStockValue)
                            .addComponent(lblStockMarket)
                            .addComponent(lblgameEnd))
                        .addGap(26, 26, 26)
                        .addGroup(pnlStartGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbStockMarket1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                            .addComponent(jcbStpckValue)
                            .addComponent(jcbNoofPlayer1)
                            .addComponent(jcbStockMarket)
                            .addComponent(txtGameName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jcbNoofPlayer, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        pnlStartGameLayout.setVerticalGroup(
            pnlStartGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStartGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStartGame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStartGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGameName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGameName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStartGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoofPlayer)
                    .addComponent(jcbNoofPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStartGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbNoofPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStartFunds))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStartGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbStpckValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStockValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStartGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbStockMarket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStockMarket))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStartGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbStockMarket1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblgameEnd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreateGame)
                .addContainerGap())
        );

        pnlJoinGame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblJoinGame.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblJoinGame.setText("Join game");

        btnJoinGame.setText("Join game");
        
        btnQuitGame.setText("Quit game");

        joinGameArea.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(joinGameArea);

        javax.swing.GroupLayout pnlJoinGameLayout = new javax.swing.GroupLayout(pnlJoinGame);
        pnlJoinGame.setLayout(pnlJoinGameLayout);
        pnlJoinGameLayout.setHorizontalGroup(
            pnlJoinGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlJoinGameLayout.createSequentialGroup()
                .addGap(0, 86, Short.MAX_VALUE)
                .addComponent(lblJoinGame)
                .addGap(83, 83, 83))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlJoinGameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlJoinGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnlJoinGameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnJoinGame)))
                .addContainerGap())
        );
        pnlJoinGameLayout.setVerticalGroup(
            pnlJoinGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJoinGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJoinGame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnJoinGame)
                .addContainerGap())
        );

        pnlQuit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        

        javax.swing.GroupLayout pnlQuitLayout = new javax.swing.GroupLayout(pnlQuit);
        pnlQuit.setLayout(pnlQuitLayout);
        pnlQuitLayout.setHorizontalGroup(
            pnlQuitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlQuitLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnQuitGame)
                .addContainerGap())
        );
        pnlQuitLayout.setVerticalGroup(
            pnlQuitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlQuitLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnQuitGame)
                .addContainerGap())
        );

        txaPlayerList.setColumns(20);
        txaPlayerList.setEditable(false);
        txaPlayerList.setRows(5);
        jScrollPane2.setViewportView(txaPlayerList);

        lobbyHeader.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lobbyHeader.setText("Game Lobby");

        helpButton.setText("Help");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlStartGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlQuit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlJoinGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lobbyHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(helpButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lobbyHeader, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(helpButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlJoinGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlStartGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(pnlQuit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>
        
        /**
	 * getMethods for the view.
	 */
    public JTextField getGameField(){
        return txtGameName;
    }
    
     public JList getGameArea(){
        return joinGameArea;
    }
     
     public JTextArea getPlayerArea(){
         return txaPlayerList;
     }
     
     public int getUpdateInterval(){
         return (Integer) jcbNoofPlayer.getModel().getValue();
     }
     
     public int getStartMoney(){   
     	return (Integer) jcbNoofPlayer1.getModel().getValue();
     }
     
     public int getStartValue(){   
     	return (Integer) jcbStpckValue.getModel().getValue();
     }
     
     public int getStartShares(){   
     	return (Integer) jcbStockMarket.getModel().getValue();
     } 
     
     public int getStartMaximum(){
         return (Integer) jcbStockMarket1.getModel().getValue();
     }
     
     public JButton getGameButton(){
         return btnCreateGame;
     }
     
    public DefaultListModel getJoinListModel(){
        return listModel;
    }  
        /**
	 * Closeoperation listener
	 */
    private class CloseOperationListener extends WindowAdapter{
		
		public void windowClosing(WindowEvent e) {				 
			btnQuitGame.doClick();			 
		}				
	}
}