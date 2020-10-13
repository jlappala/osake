
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 * Class for Other players view
 */
public class OtherPlayersView extends JFrame {

   public OtherPlayersView() {
    initComponents();
     
    }
   public void showGUI(){
       pack();
       setVisible(true);
       
   }    
  
     /*
     * This method is called from within the constructor to initialize the form.  
     */
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        playerList = new java.awt.List();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        listModel = new DefaultListModel();
        jPanel1 = new javax.swing.JPanel();
        lblPlayers = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        playerList1 = new javax.swing.JList();

       
        jScrollPane1.setViewportView(jList1);
        playerList.setEnabled(true);      
        jScrollPane2.setViewportView(jList2);
        setTitle("Other players stocks");      
        playerList1 = new javax.swing.JList(listModel);
        lblPlayers.setText("Players stock values");
        
        
        /**
         * Adds icons to components.
         */
        try {
            //Icon
            Image others = ImageIO.read(getClass().getResource("icons/others.png"));
            lblPlayers.setIcon(new ImageIcon(others));  
            
            Image stockexchanger = ImageIO.read(getClass().getResource("icons/stockexchanger.png"));
            setIconImage(stockexchanger);
        } catch (IOException ex) {     
        }
        
        playerList1.setEnabled(true);
        jScrollPane3.setViewportView(playerList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(lblPlayers))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPlayers)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }    
    
 	/**
	 * getMethods for the view.
	 */
    public JList getStatusArea(){
        return playerList1;
    }
  
   public DefaultListModel getJoinListModel1(){
        return listModel;
    }
  
    // Variables declaration
    private DefaultListModel listModel;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblPlayers;
    private java.awt.List playerList;
    private javax.swing.JList playerList1;
    // End of variables declaration
}
