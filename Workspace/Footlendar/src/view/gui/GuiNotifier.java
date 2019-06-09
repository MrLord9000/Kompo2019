package view.gui;

import javax.swing.JDialog;

import view.INotifier;

public class GuiNotifier implements INotifier {

	@Override
	public void notify(String message) {
		NotificationDialog dialog = new NotificationDialog(message);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

}
