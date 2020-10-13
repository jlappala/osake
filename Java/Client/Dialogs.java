
import javax.swing.JFrame;
import javax.swing.JOptionPane;
    /**
	 * This class has all dialogs that are used in this game.
	 */
public class Dialogs {

	public Dialogs(){
		
	}
	public String showOptionDialog(String message, String title, int type){
		return (String) JOptionPane.showInputDialog(null, message, title, type, null, null, "");
	}
	
	public void showMessageDialog(String msg){
		JOptionPane.showMessageDialog(new JFrame(), msg, null, JOptionPane.ERROR_MESSAGE);
	}
	
	public int showOptionMessage(String msg, String title){
		return JOptionPane.showConfirmDialog(new JFrame(), msg, title, JOptionPane.OK_CANCEL_OPTION);
	}	
}
