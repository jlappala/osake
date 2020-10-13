


import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author Lasse Annola
 */
public class EndWindow extends javax.swing.JFrame {    

    /** Creates new form EndWindow */
    public EndWindow(){
        initComponents();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
    }
     
    public void showGUI(){
       pack();
       setVisible(true);       
   }
        /**
	 * Buttonlisteners for view.
	 */
   public void setButtonListener(ActionListener bl){
        this.buttonListener = bl;
        overallButton.addActionListener(buttonListener);
        savingsButton.addActionListener(buttonListener);
        stockValueButton.addActionListener(buttonListener);
        stocksButton.addActionListener(buttonListener);
        jButton5.addActionListener(buttonListener);
   }
    /* This method is called from within the constructor to initialize the form.
       * 
     */
    @SuppressWarnings("unchecked")
        /**
	 * Initializes the view.
	 */
    private void initComponents() {
        
        listModel1 = new DefaultListModel();
        jPanel1 = new javax.swing.JPanel();
        winnerName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        standingsList = new javax.swing.JList();
        finalStandings = new javax.swing.JLabel();
        overallButton = new javax.swing.JButton();
        stocksButton = new javax.swing.JButton();
        savingsButton = new javax.swing.JButton();
        stockValueButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        gameEnd = new javax.swing.JLabel();

        setTitle("Game has ended!");
        setName("gameEndFrame");

       

        standingsList = new javax.swing.JList(listModel1);
        jScrollPane1.setViewportView(standingsList);

        finalStandings.setText("Final standings:");

        overallButton.setText("Best overall");

        stocksButton.setText("Most loans");

        savingsButton.setText("Most Savings");

        stockValueButton.setText("Best stock value");

        jButton5.setText("Back to lobby");
        
        
        /**
	 * Adds icons for window.
	 */
        try{
        Image back = ImageIO.read(getClass().getResource("icons/action_stop.gif"));
        jButton5.setIcon(new ImageIcon(back));
        
        Image overall = ImageIO.read(getClass().getResource("icons/winner.png"));
        overallButton.setIcon(new ImageIcon(overall));
        
        Image loans = ImageIO.read(getClass().getResource("icons/loans.png"));
        stocksButton.setIcon(new ImageIcon(loans));
        
        Image savings = ImageIO.read(getClass().getResource("icons/cash.png"));
        savingsButton.setIcon(new ImageIcon(savings));
        
        Image stocksvalue = ImageIO.read(getClass().getResource("icons/stockoverall.png"));
        stockValueButton.setIcon(new ImageIcon(stocksvalue));
        
        Image standings = ImageIO.read(getClass().getResource("icons/standings.png"));
        finalStandings.setIcon(new ImageIcon(standings));
        
        Image stockexchanger = ImageIO.read(getClass().getResource("icons/stockexchanger.png"));
        setIconImage(stockexchanger);
        
            
        } catch (IOException ex) {     
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(winnerName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(finalStandings)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(overallButton)
                .addGap(6, 6, 6)
                .addComponent(stocksButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(savingsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stockValueButton)
                .addContainerGap(92, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(397, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(winnerName)
                .addGap(18, 18, 18)
                .addComponent(finalStandings)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(overallButton)
                    .addComponent(stocksButton)
                    .addComponent(savingsButton)
                    .addComponent(stockValueButton))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameEnd.setFont(new java.awt.Font("Tahoma", 1, 14));
        gameEnd.setText("Game has ended!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(gameEnd)
                .addContainerGap(187, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gameEnd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
   
        /**
	 * getMethods for the view.
	 */
    public JList getAllStocks() {
        return standingsList;
    }
    
    public JList getMostSavings() {
        return standingsList;
    }
   
    public JList getMostOverall() {
        return standingsList;
    }
    
    public JList getMostStockValue() {
        return standingsList;
    }
    
    public DefaultListModel getListModel(){
        return listModel1;
    }
    
    // Variables declaration
    private DefaultListModel listModel1;
    private ActionListener buttonListener;
    private javax.swing.JLabel finalStandings;
    private javax.swing.JLabel gameEnd;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton overallButton;
    private javax.swing.JButton savingsButton;
    private javax.swing.JList standingsList;
    private javax.swing.JButton stockValueButton;
    private javax.swing.JButton stocksButton;
    private javax.swing.JLabel winnerName;
    // End of variables declaration
    
        /**
	 * Listens for windowClosing at jButton5
	 */
    private class CloseOperationListener extends WindowAdapter{
    	
		public void windowClosing(WindowEvent e) {			
			jButton5.doClick();			 
		}				
	}
}
