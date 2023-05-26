package presentation;

import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

public class Validator {
	public static boolean isPresent(JTextComponent c, String title) {
		if(c.getText().length() == 0) {
			showMessage(c, title + " is a required field.\nPlease re-enter.");
			c.requestFocusInWindow();
			return false;
		}
		return true;
	}
	
	private static void showMessage(JTextComponent c, String message) {
		JOptionPane.showMessageDialog(c, message, "Invalid Entry", JOptionPane.ERROR_MESSAGE);
	}
}
