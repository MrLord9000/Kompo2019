package view.gui;

import javax.swing.JDialog;

import view.INotifier;

/**
 * Match notifier implementation for GUI application.
 * 
 * @author Filip Mazurek
 * @author Adrian Zieliński
 *
 */
public class GuiNotifier implements INotifier {

	@Override
	public void notify(String message) {
		NotificationDialog dialog = new NotificationDialog(message);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

}
