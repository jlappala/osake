

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class GameView extends JFrame {
	
 // Variables declaration		
    private javax.swing.JButton btnBank;
    private javax.swing.JButton btnOthers;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnStartgame;
    protected javax.swing.JButton buy;
    private javax.swing.JButton buyAll;
    private javax.swing.JTextArea chatArea1;
    private javax.swing.JLabel chatLabel;
    private javax.swing.JButton helpButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblGameName;
    private javax.swing.JLabel lblMainMoney;
    private javax.swing.JLabel lblMainWealth;
    protected javax.swing.JTextField messageWriter;
    protected javax.swing.JTextArea moneyArea;
    private javax.swing.JPanel northPanel;
    private javax.swing.JPanel pnlButtons;
    protected javax.swing.JButton sell;
    private javax.swing.JButton sellAll;
    private javax.swing.JTextArea serverMsgArea;
    private javax.swing.JLabel serverMsgLabel;
    protected javax.swing.JTable stockTable;
    protected javax.swing.JTextArea totalWealth;
    protected JPanel namePanel;
    protected JPanel stockPanel;
    private ActionListener buttonListener = null;
    private KeyListener keyListener = null;
    private String ACTION_KEY = "theAction";
    // End of variables declaration 
               
     /*
     * This is the main view in Stock Exchanger. 
     */
	public GameView(){
                
		initComponents();
		setTitle("Stock Exchanger");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new CloseOperationListener());
		getContentPane();
		//Select first row of the table as default
		stockTable.setColumnSelectionAllowed(false);
		stockTable.setRowSelectionAllowed(true);
		stockTable.setRowSelectionInterval(0, 0);
		//set custom renderer for column that shows average spendings
		stockTable.getColumnModel().getColumn(4).setCellRenderer(new CustomColumnRenderer());                
                
	}
	
	public void showGUI(){
		
        pack();
        setVisible(true);        
	}
	
	/*
     * Setting buttonlistener for the view
     */
	public void setButtonListener(ActionListener bl){
	this.buttonListener = bl;        
        
        buy.addActionListener(buttonListener);
        sell.addActionListener(buttonListener);
        btnQuit.addActionListener(buttonListener);
        btnBank.addActionListener(buttonListener);
        buyAll.addActionListener(buttonListener);
        sellAll.addActionListener(buttonListener);
        btnOthers.addActionListener(buttonListener);
        btnStartgame.addActionListener(buttonListener);
        helpButton.addActionListener(buttonListener);
        
        
	}
        /*
         * Setting Keylistener for the view
         */
        public void setKeyListener (KeyListener k){  
        this.keyListener = k;  
        stockTable.addKeyListener(keyListener);
        btnStartgame.addKeyListener(keyListener);      
        }
        
     /*
     * This method is called from within the constructor to initialize the form.  
     */	
    private void initComponents() {

    	 northPanel = new javax.swing.JPanel();
         lblMainMoney = new javax.swing.JLabel();
         lblGameName = new javax.swing.JLabel();
         jScrollPane1 = new javax.swing.JScrollPane();
         stockTable = new javax.swing.JTable();
         jScrollPane2 = new javax.swing.JScrollPane();
         moneyArea = new javax.swing.JTextArea();
         messageWriter = new javax.swing.JTextField();
         pnlButtons = new javax.swing.JPanel();
         buy = new javax.swing.JButton();
         buyAll = new javax.swing.JButton();
         sell = new javax.swing.JButton();
         sellAll = new javax.swing.JButton();
         btnOthers = new javax.swing.JButton();
         btnBank = new javax.swing.JButton();
         btnQuit = new javax.swing.JButton();
         btnStartgame = new javax.swing.JButton();
         serverMsgLabel = new javax.swing.JLabel();
         chatLabel = new javax.swing.JLabel();
         jScrollPane3 = new javax.swing.JScrollPane();
         totalWealth = new javax.swing.JTextArea();
         lblMainWealth = new javax.swing.JLabel();
         helpButton = new javax.swing.JButton();
         jScrollPane4 = new javax.swing.JScrollPane();
         serverMsgArea = new javax.swing.JTextArea();
         jScrollPane5 = new javax.swing.JScrollPane();
         chatArea1 = new javax.swing.JTextArea();
         jPanel2 = new javax.swing.JPanel();
         
         addWindowFocusListener(new WindowAdapter(){
          public void windowGainedFocus(WindowEvent e){
              requestFocusInWindow();
          }   
         });
         
        setLocationByPlatform(true);
        setFocusable(true);
        
        northPanel.setPreferredSize(new java.awt.Dimension(668, 635));
        
        lblMainMoney.setFont(new java.awt.Font("Arial", 1, 12));
        lblMainMoney.setText("Money:");

        lblGameName.setFont(new java.awt.Font("Arial", 1, 18));

        stockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Company", "Stocks", "Value", "Avg. spent", "Owned"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        stockTable.setFillsViewportHeight(true);
        stockTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(stockTable);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        moneyArea.setColumns(20);
        moneyArea.setEditable(false);
        moneyArea.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        moneyArea.setRows(5);
        moneyArea.setFocusable(false);        
        
        jScrollPane2.setViewportView(moneyArea);

        buy.setText("Buy Stock");

        buyAll.setText("Buy with all money");

        sell.setText("Sell Stock");

        sellAll.setText("Sell all stock");

        btnOthers.setText("Other players");

        btnBank.setText("Bank");

        btnQuit.setText("Quit game");

        btnStartgame.setText("Start game");
        
        /*
         * Adds icons to various components in the view.
         */
         try {
            
        	 Image buyimg = ImageIO.read(getClass().getResource("icons/green_up.png"));
             buy.setIcon(new ImageIcon(buyimg));                 
             buyAll.setIcon(new ImageIcon(buyimg)); 
             buy.setHorizontalAlignment(SwingConstants.LEFT);
             buyAll.setHorizontalAlignment(SwingConstants.LEFT);
             
             Image sellimg = ImageIO.read(getClass().getResource("icons/red_down.png"));
             sell.setIcon(new ImageIcon(sellimg));
             sellAll.setIcon(new ImageIcon(sellimg));
             sell.setHorizontalAlignment(SwingConstants.LEFT);
             sellAll.setHorizontalAlignment(SwingConstants.LEFT);
             
             Image bankicon = ImageIO.read(getClass().getResource("icons/bank.png"));
             btnBank.setIcon(new ImageIcon(bankicon));
             btnBank.setHorizontalAlignment(SwingConstants.LEFT);
             
             Image others = ImageIO.read(getClass().getResource("icons/others.png"));
             btnOthers.setIcon(new ImageIcon(others));
             btnOthers.setHorizontalAlignment(SwingConstants.LEFT);
             
             Image start = ImageIO.read(getClass().getResource("icons/start.png"));
             btnStartgame.setIcon(new ImageIcon(start));
             btnStartgame.setHorizontalAlignment(SwingConstants.LEFT);
             
             Image stop = ImageIO.read(getClass().getResource("icons/action_stop.gif"));
             btnQuit.setIcon(new ImageIcon(stop));
             btnQuit.setHorizontalAlignment(SwingConstants.LEFT);
             
             Image help = ImageIO.read(getClass().getResource("icons/icon_info.gif"));
             helpButton.setIcon(new ImageIcon(help));
             helpButton.setHorizontalAlignment(SwingConstants.LEFT);
             
             Image stockexchanger = ImageIO.read(getClass().getResource("icons/stockexchanger.png"));
             setIconImage(stockexchanger);

            
        } catch (IOException ex) {     
        }

        javax.swing.GroupLayout pnlButtonsLayout = new javax.swing.GroupLayout(pnlButtons);
        pnlButtons.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlButtonsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buyAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sellAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOthers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBank, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnStartgame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQuit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlButtonsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBank, btnOthers, btnQuit, buy, buyAll, sell, sellAll});

        pnlButtonsLayout.setVerticalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addComponent(buy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buyAll)
                .addGap(33, 33, 33)
                .addComponent(sell)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sellAll)
                .addGap(59, 59, 59)
                .addComponent(btnBank)
                .addGap(18, 18, 18)
                .addComponent(btnOthers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnStartgame)
                .addGap(18, 18, 18)
                .addComponent(btnQuit))
        );

        serverMsgLabel.setText("NASDAQ Info's");

        chatLabel.setText("Broker chat");

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        totalWealth.setColumns(20);
        totalWealth.setEditable(false);
        totalWealth.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        totalWealth.setRows(5);
        totalWealth.setFocusable(false);
        jScrollPane3.setViewportView(totalWealth);

        lblMainWealth.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblMainWealth.setText("Total wealth:");

        helpButton.setText("Help");

        serverMsgArea.setColumns(20);
        serverMsgArea.setEditable(false);
        serverMsgArea.setLineWrap(true);
        serverMsgArea.setRows(5);
        
        /*
         * Adding background to serverMsgarea, not working ATM // TODO
         */
        ImageIcon icon = new ImageIcon("icons/bgrnd.png");
        Image image = icon.getImage();
        
        Image grayImage = GrayFilter.createDisabledImage(image);{
        serverMsgArea.setOpaque(false);
        }
        
        jScrollPane4.setViewportView(serverMsgArea);

        chatArea1.setColumns(20);
        chatArea1.setEditable(false);
        chatArea1.setLineWrap(true);
        chatArea1.setRows(5);
        jScrollPane5.setViewportView(chatArea1);

        javax.swing.GroupLayout northPanelLayout = new javax.swing.GroupLayout(northPanel);
        northPanel.setLayout(northPanelLayout);
        northPanelLayout.setHorizontalGroup(
            northPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(northPanelLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(northPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(northPanelLayout.createSequentialGroup()
                        .addComponent(lblGameName, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMainMoney)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMainWealth)
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(chatLabel)
                    .addComponent(messageWriter, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                    .addComponent(serverMsgLabel)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(northPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(northPanelLayout.createSequentialGroup()
                        .addComponent(pnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, northPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(helpButton)
                        .addGap(35, 35, 35))))
        );
        northPanelLayout.setVerticalGroup(
            northPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(northPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(northPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(helpButton)
                    .addComponent(lblGameName)
                    .addComponent(lblMainWealth)
                    .addComponent(lblMainMoney))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(northPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(northPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serverMsgLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(chatLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(messageWriter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(northPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(574, 574, 574)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(northPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        	pack();
    	}
    
		/*
		* getMethods for the view
		*/        
		public JTextField getMessageWriter(){
			return messageWriter;
		}
			
		public JTextArea getStatusArea(){
			return serverMsgArea;
		}
		
		public JTextArea getMoneyArea(){
			return moneyArea;
		}
		
		public JTextArea getMessageArea(){
			return chatArea1;
		} 
		
		public JTable getStockTable(){
			return stockTable;
		}
	        
	    public JTextArea getTotalWealth(){
	            return totalWealth;
	    }
    
        /*
        * Method for getting selected row from the stockTable.
        */
	
        public Object[] getSelectedRow(){
    	
                Object[] selectedRow = new Object[stockTable.getColumnCount()];
                int row = stockTable.getSelectedRow();

                for(int i=0; i<stockTable.getColumnCount(); i++){
                        selectedRow[i] = stockTable.getModel().getValueAt(row, i);        		
                }
                return selectedRow;
        }      
        /*
        * Gets the game name from lobby and adds it as game name in main view.
        */
        public void setGameName(String s){
                lblGameName.setText(s);
        }
        /*
        * Columnrenderer for the stockTable that is used to know what cell
         * is selected and what attributes are linked to that cell.
        */
        private class CustomColumnRenderer extends DefaultTableCellRenderer{
    	
                private Color green = new Color(0,128,0);
                private DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
    	    	
                public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus, int r, int c){
    		
                    JLabel info = (JLabel) super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, r, c); 
    	
                        try{
                                int spentValue = Integer.parseInt((String) model.getValueAt(r, c));
                                int stockValue = Integer.parseInt((String) model.getValueAt(r, c-1));    			

                                if(spentValue > stockValue)
                                        info.setForeground(Color.RED);
                                else
                                        info.setForeground(green);   

                        }catch(NumberFormatException nfe){}	
    			
                        setHorizontalAlignment(JLabel.RIGHT);
    		
                    return info;
                }       	
        }
        /*
        * Listens for closeoperation.
        */
        private class CloseOperationListener extends WindowAdapter{
    	
		public void windowClosing(WindowEvent e) {			
			btnQuit.doClick();			 
		}				
	}
}