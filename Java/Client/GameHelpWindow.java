
import javax.swing.JFrame;
        
        /*
        * View for gamehelp in the game.
        */

    public class GameHelpWindow extends JFrame{
	
	public GameHelpWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("GAME HELP\n\nGAME'S STORY: The idea of the game is to be a traditional \nmarketing game with using stocks.You buy stocks and try \nto make profit with those by selling them with higher price. \nThe cheaper you buy and the higher price you sell is a key \nto victory. So find out who is the business king! \n\nNotice that the update of the stock value is done randomly \nso there are no differences between companies and the \nvalue can change either positive or negative way.\nThere are also risks that if you buy stocks which value is \nvery low. That's because if the stock value reaches the \nvalue 0 the company will go to bankruptcy and your stocks \nare worthless.\n\nIf you feel that you do not have enough money, you can \nalways take a loan from bank. But remember, all you loan \nhas to be paid back with interests! So be careful or debts \nwill become uncontrollable.\n\nGAME ENDING:\n\nThe game will end when one of these situations happen\n\n�\t All the stocks are sold\n\n�\t One player reaches the \"game ending value\" \n\t which was set at the start of the game\n\n�\t All players go to personal bankcruptcy.\n \n\nThe winner of the game is the player who has the biggest \nsum of money when all the money, stocks, deposits and \ndebts are calculated together. If the player has debts for \nbank the debts will be calculated to the final sum. \n\n\nVIEWS:\n\nThe game has one main window that stays on during the\ngame and when doing functions. \n\n\nThe most important thing is the Table that shows info \nabout stocks. \n\n�\t�No�: The number of stock\n\n�\t�Company�:  The name of the company\n\n�\t�Stocks�:  The amount of stocks on market\n\n�\t�Value�: The value of stocks. If the value \n\tgoes to 0 the company will go bankruptcy.\n\tThen all the stocks you own from that \n\tcompany are worthless\n\n�\t�Avg. spent�: The average amount per \n\tstocks you have spent\n\n�\t�Owned�: The amount of stocks you do have\n\n\n\n�\t �NASDAG Info�s�: Shows you all the messages \n\tthat server sends to players\n\n�\t�Broker chat�: Is a chat window that shows \n\twhat other players type you can write yourself \n\tto a space below it\n\nTable will update its stats depending the �Update interval� \nwhich was set in game lobby. \n\nGAME FUNCTIONALITY:\n\nBUY STOCK: For buying stocks, click the stock on the list \nyou would like to buy and then click the �Buy stock� button. \nAfter that put the amount of stocks you want to buy.\nThen the purchase will be confirmed. If you don�t have \nenough money for buying, the game will inform you by \nsaying �You don�t have enough money�.\nIf there are no stocks on market, the game will inform \nyou with �Not enough stocks�\n\nBUY WITH ALL MONEY: For buying stocks using all your \nmoney, click the stock on the list you would like to buy \nand then click the �Buy with all money� button.\nThen the purchase will be confirmed. If there are no \nstocks on market the game will inform you with \n�Not enough stocks�\n\nSELL STOCK: For selling stocks, click the stock on the \nlist you would like to sell and then click the �Sell stock� \nbutton. After that put the amount of stocks you want \nto sell. Then the selling will be confirmed. If you don�t \nhave enough stocks for selling, the game will inform you\nby saying �Not enough stocks�.\n\nSELL ALL STOCK: For selling all your stocks, click the \nstock on the list you would like to sell and then click the \n�Sell all stock� button. Then the selling will be confirmed. \nIf you don�t have stocks for selling, the game will inform \nyou by saying �Not enough stocks�.\n\nOTHER PLAYERS: For seeing what other players have. \nThe value is the amount of their stocks when calculated \nto money.\n\nBANK: You can take a loan from the bank.. see more \ninfo from bankhelp.\n\nQUIT GAME: For quitting the game click �Quit game� \nbutton. It will take you to game lobby.\n\n\nBANK HELP: \n\nGeneral rules: There are a couple of things you can \ndo in the bank.\n\n�\tDeposit Money: Deposit money to bank \n\tand it will start to make profit with 5% \n\tevery 12seconds \n\n�\tWithdraw Money: Withdraw x amount \n\tof money you have deposited to bank\n\n�\tWithdraw All Money: Withdraw all the \n\tmoney you have deposited to bank\n\n�\tTake Loan: Take a loan from bank for \n\tyour businesses. You can loan as much \n\tas you want, but remember all you loan \n\tmust be paid back with interests or you \n\tmay go to a \"personal bankruptcy\".\n\tLoan also raises every 12 seconds for 5%\n\n�\tPay Loan: Pay loan back to the bank. \n\tYou can pay as much as you want but \n\tremember that the debt interest will grow \n\tuntil you have paid all the debts.\n\nBank infoarea's do show following data:\n\nSavings\nInterest rate: shows the interest when you deposit money\nTotal savings: shows the money you have deposited\n\nLoans: \nInterest rate: shows the interest when you loan money\nTotal loans: shows the money you have loaned");
        jTextArea1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>
    
        /*
        * Actionlistener for the helpwindow
        */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.dispose();
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
}
