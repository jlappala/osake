

import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class BankView extends javax.swing.JFrame {

    /**
     * Creates new form Bank
     */
    public BankView() {
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

        jPanel1 = new javax.swing.JPanel();
        bankLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBankMoney = new javax.swing.JTextField();
        txtBalance = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        interestField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        interestField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnBankMoney = new javax.swing.JButton();
        btnDrawMoney = new javax.swing.JButton();
        btnDrawAll = new javax.swing.JButton();
        btnTakeLoan = new javax.swing.JButton();
        btnPayLoan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("The Big Bank");
        
        bankLabel.setText("The Big Bank");

        jLabel2.setText("LOANS");

        jLabel3.setText("SAVINGS");

        txtBankMoney.setEditable(false);
        txtBankMoney.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        
        txtBalance.setEditable(false);
        txtBalance.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        
        jLabel4.setText("Interest rate");

        interestField.setEditable(false);
        interestField.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel5.setText("Interest rate");

        interestField1.setEditable(false);
        interestField1.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel6.setText("Total loans");

        jLabel7.setText("Total savings");

        btnBankMoney.setText("Deposit");

        btnDrawMoney.setText("Withdraw");

        btnDrawAll.setText("Withdraw all");

        btnTakeLoan.setText("Take loan");

        btnPayLoan.setText("Pay loan");
        
     /**
	 * Adds icons for various components.
	 */
        try {
           
        	Image deposits = ImageIO.read(getClass().getResource("icons/deposit.png"));
            btnBankMoney.setIcon(new ImageIcon(deposits));
            btnTakeLoan.setIcon(new ImageIcon(deposits));
            btnBankMoney.setHorizontalAlignment(SwingConstants.LEFT);
            btnTakeLoan.setHorizontalAlignment(SwingConstants.LEFT);
            
            Image withdraw = ImageIO.read(getClass().getResource("icons/draw.png"));
            btnDrawMoney.setIcon(new ImageIcon(withdraw));
            btnDrawAll.setIcon(new ImageIcon(withdraw));
            btnDrawMoney.setHorizontalAlignment(SwingConstants.LEFT);
            btnDrawAll.setHorizontalAlignment(SwingConstants.LEFT);
            
            Image payloan = ImageIO.read(getClass().getResource("icons/payloan.png"));
            btnPayLoan.setIcon(new ImageIcon(payloan));
            btnPayLoan.setHorizontalAlignment(SwingConstants.LEFT);
            
            Image stockexchanger = ImageIO.read(getClass().getResource("icons/stockexchanger.png"));
            setIconImage(stockexchanger);

            
        } catch (IOException ex) {     
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(interestField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBankMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(23, 23, 23)
                        .addComponent(bankLabel)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(interestField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnDrawMoney, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBankMoney, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDrawAll, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnPayLoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTakeLoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(interestField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(interestField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBankMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(btnBankMoney)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDrawMoney)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDrawAll))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnTakeLoan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPayLoan))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bankLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }
                                     
    
    // Variables declaration
    private javax.swing.JButton btnBankMoney;
    private javax.swing.JButton btnDrawAll;
    private javax.swing.JButton btnDrawMoney;
    private javax.swing.JButton btnPayLoan;
    private javax.swing.JButton btnTakeLoan;
    private javax.swing.JTextField interestField;
    private javax.swing.JTextField interestField1;
    private javax.swing.JLabel bankLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtBankMoney;
    private ActionListener buttonListener;
    // End of variables declaration
    
     /**
	 * Buttonlistener for the view.
	 */
     public void setButtonListener(ActionListener bl){
        this.buttonListener = bl;
        btnBankMoney.addActionListener(buttonListener);
        btnDrawAll.addActionListener(buttonListener);
        btnDrawMoney.addActionListener(buttonListener);
        btnPayLoan.addActionListener(buttonListener);
        btnTakeLoan.addActionListener(buttonListener);                
    }
     
     /**
	 * getMethods for the view.
	 */
    public JTextField getBalanceField(){
        return txtBankMoney;
    }   
  
    public JTextField getDepIntField(){
        return interestField1;
    }
    
    public JTextField getLoanIntField(){
        return interestField;
    }
    
    public JTextField getLoanArea(){
    	return txtBalance;
    }
    
}
